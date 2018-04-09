package com.huaguoguo.taotao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huaguoguo.taotao.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 展示首页controller
 */
@Controller
public class IndexController {
	
//	@Autowired
//	private ContentService contentService;

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String showIndex(Model model) {
		//首页大广告为categoryId固定为89。
//		List<TbContent> list = contentService.getContentList(89);
//		List<Map> resultList = new ArrayList<>();
//		for (TbContent tbContent : list) {
//			Map item = new HashMap<>();
//			item.put("src", tbContent.getPic());
//			item.put("width", 670);
//			item.put("height", 240);
//			item.put("srcB", tbContent.getPic2());
//			item.put("widthB", 550);
//			item.put("heightB", 240);
//			item.put("href", tbContent.getUrl());
//			item.put("alt", tbContent.getTitle());
//			resultList.add(item);
//		}
//		String json = JsonUtils.objectToJson(resultList);
//		model.addAttribute("ad1", json);
		return "index";
	}
}
