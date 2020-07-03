package com.elasticsearch.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.apache.http.client.config.RequestConfig.Builder;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @program: elasticsearch
 * @description: 类介绍
 * @author: lh
 * @create: 2020-07-03 10:54
 **/
@Configuration
public class ElasticSearchConfig {


    @Value("${elastic.clusterAddress}")
    private String clusterAddress;

    @Value("${elastic.connection-timeout}")
    private Integer connectTimeOutMillis;

    @Value("${elastic.socket-timeout}")
    private Integer socketTimeoutMillis;

    @Value("${elastic.connection-request-timeout}")
    private Integer connectionRequestTimeoutMillis;

    @Value("${elastic.connection-common}")
    private Integer maxConnPerRoute;

    @Value("${elastic.connection-total}")
    private Integer maxConnTotal;

    @Value("${elastic.scheme}")
    private String scheme;

    static final String COLON = ":";
    static final String COMMA = ",";

    @Bean
    @Scope("prototype")
    public RestHighLevelClient getRestHighLevelClient() {
        String[] split = clusterAddress.split(COMMA);
        HttpHost[] httpHosts = new HttpHost[split.length];
        HttpHost[] collect =
                Arrays.stream(split)
                        .map(
                                s -> {
                                    String[] split1 = s.split(COLON);
                                    HttpHost httpHost = new HttpHost(split1[0], Integer.parseInt(split1[1]), scheme);
                                    return httpHost;
                                })
                        .collect(Collectors.toList())
                        .toArray(httpHosts);

        return new RestHighLevelClient(
                RestClient.builder(collect)
                        .setRequestConfigCallback(requestConfigCallback())
                        .setHttpClientConfigCallback(httpClientConfigCallback()));
    }

    private HttpClientConfigCallback httpClientConfigCallback() {
        return (HttpAsyncClientBuilder httpAsyncClientBuilder) -> {
            httpAsyncClientBuilder.setMaxConnTotal(maxConnTotal);
            httpAsyncClientBuilder.setMaxConnPerRoute(maxConnPerRoute);
            return httpAsyncClientBuilder;
        };
    }

    private RequestConfigCallback requestConfigCallback() {
        return (Builder builder) -> {
            builder
                    .setConnectTimeout(connectTimeOutMillis)
                    .setSocketTimeout(socketTimeoutMillis)
                    .setConnectionRequestTimeout(connectionRequestTimeoutMillis)
                    .setMaxRedirects(5 * 60 * 1000);
            return builder;
        };
    }


}
