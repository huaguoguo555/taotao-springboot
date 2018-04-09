package com.huaguoguo.taotao.service;

import com.huaguoguo.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传图片处理
 */
public interface PictureService {

	PictureResult uploadPicture(MultipartFile uploadFile);
}
