package com.weibi.wallet.rest.sdk.proxy;

public class WalletProxyManager {

    public static WalletRestProxyImpl newWalletRestProxy(String walletRestHost, String publicKey, String privateKey) {
        return new WalletRestProxyImpl(walletRestHost, publicKey, privateKey);
    }
}
