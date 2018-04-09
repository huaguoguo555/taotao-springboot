package com.huaguoguo.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.common.utils.ExceptionUtil;
import com.huaguoguo.taotao.dao.TbItemParamMapper;
import com.huaguoguo.taotao.po.TbItemParam;
import com.huaguoguo.taotao.po.TbItemParamExample;
import com.huaguoguo.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商品规格参数模板service
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Override
	public ResultModel checkParam(long cid) {
		try {
			TbItemParamExample example = new TbItemParamExample();
			TbItemParamExample.Criteria criteria = example.createCriteria();
			criteria.andItemCatIdEqualTo(cid);
			List<TbItemParam> list = itemParamMapper.selectByExample(example);
			// 判断是否查询到结果
			if (null == list || list.isEmpty()) {
				return ResultModel.ok();
			}
			return ResultModel.ok(list.get(0));
		} catch (Exception e) {
			return ResultModel.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@Override
	public ResultModel addItemParam(long cid, String template) {
		//创建规格模板pojo
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(template);
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		//插入到数据库
		try {
			itemParamMapper.insert(tbItemParam);
		} catch(Exception e) {
			return ResultModel.build(500, ExceptionUtil.getStackTrace(e));
		}
		return ResultModel.ok();
	}

	@Override
	public ResultModel getItemParemByCid(long cid) {
		
		//创建查询条件
		TbItemParamExample example = new TbItemParamExample();
		TbItemParamExample.Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if (null != list && !list.isEmpty()) {
			return ResultModel.ok(list.get(0));
		}
		
		return ResultModel.build(400, "此分类未定义规格模板");
	}

}
