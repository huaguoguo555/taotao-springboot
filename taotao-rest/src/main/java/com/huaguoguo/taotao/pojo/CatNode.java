package com.huaguoguo.taotao.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**   
 * @Title: CatNode.java 
 * @Package com.taotao.rest.pojo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author zk 
 * @date 2018年1月20日 上午6:47:22 
 * @version V1.0   
 */
public class CatNode {

	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List items;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	
}
