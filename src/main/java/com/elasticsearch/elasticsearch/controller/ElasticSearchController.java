package com.elasticsearch.elasticsearch.controller;

import com.elasticsearch.elasticsearch.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: elasticsearch
 * @description: 类介绍
 * @author: lh
 * @create: 2020-07-03 11:28
 **/
@RestController
@RequestMapping("/search")
public class ElasticSearchController {


    @Autowired
    private ElasticSearchService searchService;




    @GetMapping(value = "/key")
    public String search(@RequestParam String key){

        return key;
    }

}
