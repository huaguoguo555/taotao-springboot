package com.huaguoguo.taotao.controller;

import com.huaguoguo.taotao.common.pojo.EasyUIDataGridResult;
import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.po.TbItem;
import com.huaguoguo.taotao.po.TbItemDesc;
import com.huaguoguo.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable(value="itemId") Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	/**
	 * 查询商品列表
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(@RequestParam(defaultValue="1") Integer page,
											@RequestParam(defaultValue="30") Integer rows) {
		//调用service查询商品列表
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		//返回结果
		return result;
		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	//添加一个itemParams参数接收规格参数的数据。
	public ResultModel addItem(TbItem item, String desc, String itemParams) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		ResultModel result = itemService.addItem(item, itemDesc, itemParams);
		return result;
	}
	
}