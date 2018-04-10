package com.huaguoguo.taotao.controller;

import javax.print.attribute.standard.Media;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.pojo.ItemCatResult;
import com.huaguoguo.taotao.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**   
 * @Title: ItemCatController.java 
 * @Package com.taotao.rest.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月20日 上午7:37:15 
 * @version V1.0   
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
/*	@RequestMapping(value="/list", produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		ItemCatResult result = itemCatService.getItemCatList();
		if (StringUtils.isBlank(callback)) {
			//需要把result转换成字符串
			String json = JsonUtils.objectToJson(result);
			return json;
		}
		//如果字符串不为空，需要支持jsonp调用
		//需要把result转换成字符串
		String json = JsonUtils.objectToJson(result);
		return callback + "(" + json + ");";

	}
	*/
	//第二种方法 springMVC 4.1及以上版本
	@RequestMapping(value="/list",method = RequestMethod.GET)
	@ResponseBody
	public Object getItemCatList(String callback) {
		ItemCatResult result = itemCatService.getItemCatList();
		if (StringUtils.isBlank(callback)) {
			//需要把result转换成字符串
			return result;
		}
		//如果字符串不为空，需要支持jsonp调用
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	@RequestMapping(value = "/sync",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultModel syncItemCat() {
		return itemCatService.syncItemCat();
	}

}
