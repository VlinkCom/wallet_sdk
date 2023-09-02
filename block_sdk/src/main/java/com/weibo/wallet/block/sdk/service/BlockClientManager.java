package com.weibo.wallet.block.sdk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BlockClientManager {

    private final Map<Integer, BlockClient> blockClientMap = new ConcurrentHashMap<>();

    public BlockClientManager(@Autowired Map<String, BlockClient> clientMap) {
        clientMap.forEach((k,v) -> this.blockClientMap.put(v.getCode(), v));
    }

    public BlockClient getBlockClientByType(Integer type) {
        return this.blockClientMap.get(type);
    }
}
