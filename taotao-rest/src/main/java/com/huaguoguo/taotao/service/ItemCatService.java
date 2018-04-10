package com.huaguoguo.taotao.service;


import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.pojo.ItemCatResult;

/**
 * @Title: ItemCatService.java 
 * @Package com.taotao.rest.service 
 * @Description: TODO(前台商品类目展示) 
 * @author zk 
 * @date 2018年1月20日 上午7:23:38 
 * @version V1.0   
 */
public interface ItemCatService {

	/**
	 * 
	 * @return 商品类目json数据
	 */
	ItemCatResult getItemCatList();
	/**
	 * 缓存同步
	 * @return
	 */
	ResultModel syncItemCat();
}
