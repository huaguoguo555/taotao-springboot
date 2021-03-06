package com.huaguoguo.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaguoguo.taotao.common.pojo.EasyUIDataGridResult;
import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.ExceptionUtil;
import com.huaguoguo.taotao.common.utils.IDUtils;
import com.huaguoguo.taotao.dao.TbItemDescMapper;
import com.huaguoguo.taotao.dao.TbItemMapper;
import com.huaguoguo.taotao.dao.TbItemParamItemMapper;
import com.huaguoguo.taotao.po.TbItem;
import com.huaguoguo.taotao.po.TbItemDesc;
import com.huaguoguo.taotao.po.TbItemExample;
import com.huaguoguo.taotao.po.TbItemParamItem;
import com.huaguoguo.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	TbItemMapper itemMapper;
	
	@Autowired
	TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		//添加条件
		//Criteria criteria = example.createCriteria();
		//criteria.andIdEqualTo(123l);
		List<TbItem> list = itemMapper.selectByExample(example);
		//取total
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		
		//创建返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult(total, list);
		
		return result;
	}

	@Override
	public ResultModel addItem(TbItem item, TbItemDesc itemDesc, String itemParams) {
		try {
			//生成商品id
			//可以使用redis的自增长key，在没有redis之前使用时间+随机数策略生成
			Long itemId = IDUtils.genItemId();
			//补全不完整的字段
			item.setId(itemId);
			item.setStatus((byte) 1);
			Date date = new Date();
			item.setCreated(date);
			item.setUpdated(date);
			//把数据插入到商品表
			itemMapper.insert(item);
			//添加商品描述
			itemDesc.setItemId(itemId);
			itemDesc.setCreated(date);
			itemDesc.setUpdated(date);
			//把数据插入到商品描述表
			itemDescMapper.insert(itemDesc);
			
			//把商品的规格参数插入到tb_item_param_item中
			TbItemParamItem itemParamItem = new TbItemParamItem();
			itemParamItem.setItemId(itemId);
			itemParamItem.setParamData(itemParams);
			itemParamItem.setCreated(date);
			itemParamItem.setUpdated(date);
			itemParamItemMapper.insert(itemParamItem);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultModel.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return ResultModel.ok();
	}

}
