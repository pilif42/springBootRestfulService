package com.example.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private Client client;
    private ObjectMapper mapper;

    @Value("${elasticsearch.server.name}")
    private String elasticsearchServerName;

    @Value("${elasticsearch.server.port}")
    private int elasticsearchServerPort;

    @PostConstruct
    public void init() throws UnknownHostException {
        log.debug("elasticsearchServerName = {}", elasticsearchServerName);
        log.debug("elasticsearchServerPort = {}", elasticsearchServerPort);

        client = TransportClient.builder().build()
            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticsearchServerName), elasticsearchServerPort));

        mapper = new ObjectMapper();
    }

    @Override
    public void storeSomeJsonDocument(String userId, String jsonDocument) {
        byte[] json = jsonDocument.getBytes();
        IndexResponse response = client.prepareIndex("osmaps", "user", userId).setSource(json).get();
        // TODO analyse the response and maybe modify the return type of this method
        log.debug("document has been indexed = {}", response.isCreated());
    }

    @PreDestroy
    public void cleanUp() {
        client.close();
    }
}

