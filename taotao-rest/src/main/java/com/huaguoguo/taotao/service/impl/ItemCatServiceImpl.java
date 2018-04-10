package com.huaguoguo.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;


import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.JsonUtils;
import com.huaguoguo.taotao.component.JedisClient;
import com.huaguoguo.taotao.dao.TbItemCatMapper;
import com.huaguoguo.taotao.pojo.CatNode;
import com.huaguoguo.taotao.pojo.ItemCatResult;
import com.huaguoguo.taotao.pojo.TbItemCat;
import com.huaguoguo.taotao.pojo.TbItemCatExample;
import com.huaguoguo.taotao.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


/**   
 * @Title: ItemCatServiceImpl.java 
 * @Package com.taotao.rest.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月20日 上午7:25:31 
 * @version V1.0   
 */

@Service
@PropertySource(value = {"classpath:resource.properties"})
public class ItemCatServiceImpl implements ItemCatService {
	
	private String REDIS_ITEMCAT_KEY;
	
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Autowired
	JedisClient jedisClient;

	@Override
	public ItemCatResult getItemCatList() {
		//先从缓存中查数据
		try {
			String json = jedisClient.get(REDIS_ITEMCAT_KEY);
			if(!StringUtils.isBlank(json)) {
				ItemCatResult result = JsonUtils.jsonToPojo(json, ItemCatResult.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List catList = getItemCatList(0l);
		ItemCatResult result = new ItemCatResult();
		result.setData(catList);
		//返回结果前先将数据添加到缓存
		try {
			jedisClient.set(REDIS_ITEMCAT_KEY, JsonUtils.objectToJson(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private List getItemCatList(Long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList();
		int index = 0;
		for (TbItemCat tbItemCat : list) {
			if (index >= 14) {
				break;
			}
			//如果是父节点
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl("/products/"+tbItemCat.getId()+".html");
				//如果当前节点为第一级节点
				if (tbItemCat.getParentId() == 0) {
					index++;
					node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					node.setName(tbItemCat.getName());
				}
				//递归调用
				node.setItems(getItemCatList(tbItemCat.getId()));
				//把node添加到列表
				resultList.add(node);
			} else {
				//如果是叶子节点
				String item = "/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
				resultList.add(item);
			}
		}
		
		return resultList;	
	}

	@Override
	public ResultModel syncItemCat() {
		jedisClient.del(REDIS_ITEMCAT_KEY);
		return ResultModel.ok();
	}

}
