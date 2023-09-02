package com.weibo.wallet.block.sdk.service;

public interface BlockClient {

    <T, B> T getPaymentsByTransactionHash(B b, Class<T> clazz);

    Integer getCode();
}
