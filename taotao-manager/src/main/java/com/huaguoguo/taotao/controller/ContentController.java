package com.huaguoguo.taotao.controller;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.po.TbContent;
import com.huaguoguo.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 内容管理
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	private ResultModel saveContent(TbContent content) {
		ResultModel result = contentService.insertContent(content);
		return result;
	}
}
