package com.huaguoguo.taotao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @RequestMapping(value = "/test-put",method = RequestMethod.PUT)
    public String testPut(){
        return "put成功！";
    }

    @RequestMapping(value = "/test-delete",method = RequestMethod.DELETE)
    public String testDelete(){
        return "DELETE成功！";
    }
}
