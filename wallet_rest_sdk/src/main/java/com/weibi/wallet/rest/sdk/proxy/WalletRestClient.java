//package com.weibi.wallet.rest.sdk.proxy;
//
//import org.springframework.beans.factory.annotation.Value;
//
//import java.lang.reflect.Proxy;
//
//public interface WalletRestClient {
//
//    @Value("${WALLET_REST_HOST}")
//    private String walletRestHost;
//
//    public CoinWalletProxy getCoinWallet() {
//        return (CoinWalletProxy) Proxy.newProxyInstance(WalletProxy.class.getClassLoader(), new Class[]{CoinWalletProxy.class}, new WalletProxyInvokerHandler(walletHost));
//    }
//}
