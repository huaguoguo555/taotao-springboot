package com.huaguoguo.taotao.service;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.pojo.TbContent;

import java.util.List;


/**   
 * @Title: ContentService.java 
 * @Package com.taotao.rest.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月23日 上午10:23:55 
 * @version V1.0   
 */
public interface ContentService {
	/**
	 * 根据id查询广告
	 * @param cid
	 * @return
	 */
	 List<TbContent> getContentList(Long cid);
	 /**
	  * 缓存同步
	  * @param cid
	  * @return
	  */
	 ResultModel syncContent(Long cid);
}
