package com.huaguoguo.taotao.controller;

import java.util.List;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.ExceptionUtil;
import com.huaguoguo.taotao.pojo.TbContent;
import com.huaguoguo.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**   
 * @Title: ContenController.java 
 * @Package com.taotao.rest.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月23日 上午10:29:41 
 * @version V1.0   
 */
@Controller
public class ContenController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "/content/{cid}",method = RequestMethod.GET)
	@ResponseBody
	public ResultModel getContentList(@PathVariable Long cid) {
		ResultModel<List<TbContent>> resultModel = new ResultModel();
		try {
			List<TbContent> list = contentService.getContentList(cid);
			resultModel.setData(list);
			return resultModel;
		} catch (Exception e) {
			e.printStackTrace();

			resultModel.setStatus(500L);
			resultModel.setMsg(ExceptionUtil.getStackTrace(e));
			return resultModel;
		}
	}
	@RequestMapping(value = "/sync/content/{cid}",method = RequestMethod.PUT)
	@ResponseBody
	public ResultModel syncContent(@PathVariable Long cid) {
		try {
			ResultModel result = contentService.syncContent(cid);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus(500L);
			resultModel.setMsg(ExceptionUtil.getStackTrace(e));
			return resultModel;
		}
	}
	
	
}
