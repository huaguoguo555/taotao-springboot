package com.huaguoguo.taotao.service.impl;

import java.util.List;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.JsonUtils;
import com.huaguoguo.taotao.component.JedisClient;
import com.huaguoguo.taotao.dao.TbContentMapper;
import com.huaguoguo.taotao.pojo.TbContent;
import com.huaguoguo.taotao.pojo.TbContentExample;
import com.huaguoguo.taotao.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**   
 * @Title: ContentServiceImpl.java 
 * @Package com.taotao.rest.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月23日 上午10:25:31 
 * @version V1.0   
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper contentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	
	
	@Override
	public List<TbContent> getContentList(Long cid) {
		//添加缓存
		//查询数据库前先查询缓存，如果有直接返回、
		try {
			//从redis中取缓存数据
			String json = jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
			//判断是否非空 
			if (!StringUtils.isBlank(json)) {
				//把json转换成List
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据cid查询列表 设置查询条件
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//返回结果之前， 先将数据添加到缓存中
		try {
			//为了规范key可以使用hash
			//定义一个保存内容的key，hash中每个项就是cid
			//value就是list，需要把list转换成json数据
			jedisClient.hset(REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public ResultModel syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid + "");
		return ResultModel.ok();
	}

}
