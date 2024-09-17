package com.service.elasticsearch.config.elastic;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.service.elasticsearch.repository")
public class ElasticsearchConfig {

//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestClientBuilder builder = RestClient.builder(
//                new HttpHost("localhost", 9200, "http") // Adjust host and port as needed
//        );
//        return new RestHighLevelClient(builder);
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchOperations(RestHighLevelClient client) {
//        return new ElasticsearchRestTemplate(client);
//    }

}
