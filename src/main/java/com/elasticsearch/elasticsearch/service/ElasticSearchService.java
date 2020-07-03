package com.elasticsearch.elasticsearch.service;

import org.elasticsearch.action.search.SearchResponse;

/**
 * @description: 接口介绍
 * @author: lh
 * @create: 2020-07-03 11:09
 **/
public interface ElasticSearchService {

    /**
     * 创建索引createHistoryIndex
     *
     * @param historyName 索引名称
     * @param historyType 索引类型
     * @return
     */
    boolean createHistoryIndex(String historyName, String historyType);

    /**
     * 删除索引
     *
     * @param historyName
     * @return
     */
    boolean deleteHistoryIndex(String historyName);

    /**
     * 创建文档
     *
     * @param historyInfo 历史记录
     * @return boolean
     */
    boolean createDocument(String historyInfo);



}
