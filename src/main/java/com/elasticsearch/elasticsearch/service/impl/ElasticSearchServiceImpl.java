package com.elasticsearch.elasticsearch.service.impl;

import com.elasticsearch.elasticsearch.service.ElasticSearchService;
import com.elasticsearch.elasticsearch.utis.ReadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @program: elasticsearch
 * @description: 类介绍
 * @author: lh
 * @create: 2020-07-03 11:11
 **/
@Slf4j
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {



    @Autowired
    private RestHighLevelClient client;

    @Override
    public boolean createHistoryIndex(String historyName, String historyType) {
        String indexName = historyName+"_"+System.currentTimeMillis();

        String historyMapping = ReadFileUtil.readResourcesJson("history_mapping.json");
        String historySetting = ReadFileUtil.readResourcesJson("history_setting.json");
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        createIndexRequest.settings(historySetting, XContentType.JSON);
        createIndexRequest.alias(new Alias(historyType)).mapping("struct", historyMapping, XContentType.JSON);
        createIndexRequest.alias(new Alias(historyType));
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(indexName + "-----------索引建立失败--------------->" + createIndexResponse.index());
            return false;
        }
        log.info(indexName + "-----------索引建立成功了--------------->" + createIndexResponse.index());
        boolean acknowledged = false;
        boolean shardsAcknowledged = false;
        if (createIndexResponse != null) {
            acknowledged = createIndexResponse.isAcknowledged();
            shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
        }
        log.info("create acknowledged: [{}], shardsAcknowledged [{}]", acknowledged, shardsAcknowledged);
        return true;

    }

    @Override
    public boolean deleteHistoryIndex(String historyName) {
        return false;
    }

    @Override
    public boolean createDocument(String historyInfo) {
        return false;
    }
}
