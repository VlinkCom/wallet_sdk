package com.weibo.wallet.block.sdk.service;

public interface BlockClient {

    // T 返回类型
    // B 参数
    // 根据hash 获取 trans 信息
    <T, B> T getPaymentsByTransactionHash(B b, Class<T> clazz);

    Integer getCode();
}
