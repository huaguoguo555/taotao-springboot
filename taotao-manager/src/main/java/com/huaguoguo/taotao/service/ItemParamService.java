package com.huaguoguo.taotao.service;


import com.huaguoguo.taotao.common.pojo.ResultModel;

public interface ItemParamService {

	ResultModel checkParam(long cid);
	ResultModel addItemParam(long cid, String template);
	ResultModel getItemParemByCid(long cid);
}
