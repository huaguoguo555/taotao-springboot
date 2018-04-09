package com.huaguoguo.taotao.service;

import com.huaguoguo.taotao.common.pojo.TreeNode;

import java.util.List;



public interface ItemCatService {

	List<TreeNode> getItemCatList(long parentId);
}
