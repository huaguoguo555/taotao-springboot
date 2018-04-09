package com.huaguoguo.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.pojo.TreeNode;
import com.huaguoguo.taotao.dao.TbContentCategoryMapper;
import com.huaguoguo.taotao.po.TbContentCategory;
import com.huaguoguo.taotao.po.TbContentCategoryExample;
import com.huaguoguo.taotao.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContentCatServiceImpl implements ContentCatService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<TreeNode> getContentCatList(long parentId) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<TreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			TreeNode node = new TreeNode(tbContentCategory.getId(), tbContentCategory.getName(),
					tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		
		return resultList;
	}
	
	@Override
	public ResultModel createNode(long parentId, String name) {
		//创建一内容分类pojo
		TbContentCategory node = new TbContentCategory();
		node.setName(name);
		node.setIsParent(false);
		node.setParentId(parentId);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		node.setSortOrder(1);
		//状态。可选值:1(正常),2(删除)
		node.setStatus(1);
		node.setUpdated(new Date());
		node.setCreated(new Date());
		//插入节点数据
		contentCategoryMapper.insert(node);
		//更新父节点
		//取父节点
		/*TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(parentId);*/
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			//更新父节点isParent列
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//返回结果
		return ResultModel.ok(node);
	}

}
