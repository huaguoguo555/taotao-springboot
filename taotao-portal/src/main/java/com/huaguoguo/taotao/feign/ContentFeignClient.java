package com.huaguoguo.taotao.feign;


import com.huaguoguo.taotao.common.pojo.ResultModel;
import com.huaguoguo.taotao.po.TbContent;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "taotao-rest")
public interface ContentFeignClient {

    @RequestMapping(value = "/content/{cid}",method = RequestMethod.GET)
    ResultModel<List<TbContent>> getContentList(@PathVariable("cid") Long cid);
}
