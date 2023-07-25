package com.kylin.biz.sdk.proxy;

public class KylinProxyManager {

    private static KylinWalletProxy kylinWalletProxy = new KylinWalletProxyImpl();

    public static KylinWalletProxy getKylinWalletProxy() {
        return kylinWalletProxy;
    }
}
