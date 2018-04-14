package com.huaguoguo.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**   
 * @Title: IndexController.java 
 * @Package com.taotao.portal.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月17日 上午10:58:31 
 * @version V1.0   
 */

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;

	public IndexController(){
		System.out.println("IndexController loaded.");
	}

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String ShowIndex(Model model) {
		//取最大广告位
		String json = contentService.getAd1List();
		//传递给页面
		model.addAttribute("ad1", json);
		return "index";
	}
}
