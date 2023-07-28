package com.weibi.wallet.rest.sdk.proxy;

import com.weibi.wallet.rest.sdk.params.AddressParam;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;

import java.util.List;

public class WalletProxyManager {

    public static WalletRestProxyImpl newWalletRestProxy(String walletRestHost, String publicKey, String privateKey) {
        return new WalletRestProxyImpl(walletRestHost, publicKey, privateKey);
    }

    public static void main(String[] args) {
        WalletRestProxyImpl walletRestProxy = WalletProxyManager.newWalletRestProxy("http://localhost:18081", "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");


        AddressParam param = new AddressParam();
        param.setCoin("TRX");
        param.setSize(1);
        CommonResponse<List<String>> unUsedAddress = walletRestProxy.getUnUsedAddress(param);
        System.out.println(unUsedAddress);
    }
}
