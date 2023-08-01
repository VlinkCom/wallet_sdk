package com.kylin.biz.sdk.proxy;

import java.util.Objects;

public class KylinProxyManager {

    private static KylinWalletProxy kylinWalletProxy ;

    public static KylinWalletProxy getKylinWalletProxy(String publicKey, String privateKey) {
        if (Objects.nonNull(kylinWalletProxy)) {
            return kylinWalletProxy;
        }
        kylinWalletProxy = new KylinWalletProxyImpl(publicKey, privateKey);
        return kylinWalletProxy;
    }
}
