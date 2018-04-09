package com.huaguoguo.taotao.service;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.pojo.TreeNode;

import java.util.List;


public interface ContentCatService {

	List<TreeNode> getContentCatList(long parentId);
	ResultModel createNode(long parentId, String name);
}
