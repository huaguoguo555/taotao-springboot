package com.huaguoguo.taotao.service;


import com.huaguoguo.taotao.common.pojo.EasyUIDataGridResult;
import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.po.TbItem;
import com.huaguoguo.taotao.po.TbItemDesc;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	ResultModel addItem(TbItem item, TbItemDesc itemDesc, String itemParams);
}
