package com.kylin.biz.sdk.proxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class KylinProxyManager {

    private static Map<String, KylinWalletProxy> kylinWalletProxyMap = new ConcurrentHashMap<>();

    public static KylinWalletProxy getKylinWalletProxy(String publicKey, String privateKey) {
        if (!kylinWalletProxyMap.containsKey(publicKey)) {
            KylinWalletProxy kylinWalletProxy = new KylinWalletProxyImpl(publicKey, privateKey);
            kylinWalletProxyMap.put(publicKey, kylinWalletProxy);
        }
        return kylinWalletProxyMap.get(publicKey);
    }
}
