package com.huaguoguo.taotao.feign;


import com.huaguoguo.taotao.common.pojo.ResultModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "taotao-rest")
public interface ContentFeignClient {

    @RequestMapping(value = "/content/{cid}",method = RequestMethod.GET)
    ResultModel getContentList(@PathVariable("cid") Long cid);
}
