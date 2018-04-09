package com.huaguoguo.taotao.service.impl;

import java.util.Date;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.ExceptionUtil;
import com.huaguoguo.taotao.dao.TbContentMapper;
import com.huaguoguo.taotao.po.TbContent;
import com.huaguoguo.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public ResultModel insertContent(TbContent content) {
		try {
			//补全字段
			content.setUpdated(new Date());
			content.setCreated(new Date());
			//插入数据
			contentMapper.insert(content);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultModel.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		return ResultModel.ok();
	}

}
