package com.huaguoguo.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.JsonUtils;
import com.huaguoguo.taotao.feign.ContentFeignClient;
import com.huaguoguo.taotao.po.AdNode;
import com.huaguoguo.taotao.po.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.portal.service.ContentService;

/**   
 * @Title: ContentServiceImpl.java 
 * @Package com.taotao.portal.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月23日 上午10:37:29 
 * @version V1.0   
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentFeignClient contentFeignClient;

	@Override
	public String getAd1List() {
		//调用服务获得数据
		ResultModel resultModel = contentFeignClient.getContentList(89L);
		//取data属性，内容列表
		Object data = resultModel.getData();
		List<TbContent> contentList = ((List<TbContent>) data);
		//把内容列表转换成AdNode列表
		List<AdNode> resultList = new ArrayList<>();
		for (TbContent tbContent : contentList) {
			AdNode node = new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(tbContent.getPic());
			
			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());
			
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			
			resultList.add(node);
		}
		//需要把resultList转换成json数据
		String resultJson = JsonUtils.objectToJson(resultList);
		return resultJson;
	}

}
