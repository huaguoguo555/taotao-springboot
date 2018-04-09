package com.huaguoguo.taotao.controller;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 规格参数模板Controller
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public ResultModel checkItemParam(@PathVariable Long cid) {
		ResultModel result = itemParamService.checkParam(cid);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public ResultModel addItemParam(@PathVariable Long cid, String paramData) {
		ResultModel result = itemParamService.addItemParam(cid, paramData);
		return result;
	}
	
	@RequestMapping("/cid/{cid}")
	@ResponseBody
	public ResultModel getItemParamByCid(@PathVariable Long cid) {
		ResultModel result = itemParamService.getItemParemByCid(cid);
		return result;
	}
	
}
