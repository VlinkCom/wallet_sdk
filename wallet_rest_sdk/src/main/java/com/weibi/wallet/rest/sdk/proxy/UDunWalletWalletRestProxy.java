package com.weibi.wallet.rest.sdk.proxy;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.uduncloud.sdk.client.UdunClient;
import com.uduncloud.sdk.domain.Address;
import com.uduncloud.sdk.domain.Coin;
import com.uduncloud.sdk.domain.ResultMsg;
import com.weibi.wallet.rest.sdk.params.*;
import com.weibi.wallet.rest.sdk.params.udun.UDunAddressParam;
import com.weibi.wallet.rest.sdk.params.udun.UDunAddressVerifyParam;
import com.weibi.wallet.rest.sdk.params.udun.UDunWithdrawCreateParam;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;
import com.weibi.wallet.rest.sdk.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@ConditionalOnProperty(prefix = "wallet", name = "support", havingValue = "udun")
@Component
public class UDunWalletWalletRestProxy implements WalletWalletRestProxy {

    private final UdunClient udunClient;
    private Map<String, Coin> coinMap;

    public UDunWalletWalletRestProxy() {
        String udunKey = System.getenv("UDUN_KEY");
        String gateway = System.getenv("UDUN_GATEWAY");
        String merchantId = System.getenv("UDUN_MERCHANTID");
        String callback = System.getenv("UDUN_CALLBACK");
        if (StringUtils.isEmpty(udunKey) || StringUtils.isEmpty(gateway)) {
            throw new RuntimeException("没有配置udun钱包的相关配置");
        }
        udunClient = new UdunClient(gateway, merchantId, udunKey, callback);
        coinMap = udunClient.listSupportCoin(false).stream().collect(Collectors.toMap(Coin::getName, Function.identity()));
    }

    private Coin getCoinByName(String name) {
        // 从数据库中获取 udun钱包映射
        if (CollectionUtils.isEmpty(coinMap)) {
            coinMap = udunClient.listSupportCoin(false).stream().collect(Collectors.toMap(Coin::getName, Function.identity()));
        }
        Coin coin = coinMap.get(name);
        if (Objects.isNull(coin)) {
            coinMap = udunClient.listSupportCoin(false).stream().collect(Collectors.toMap(Coin::getName, Function.identity()));
        }
        coin = coinMap.get(name);
        if (Objects.isNull(coin)) {
            throw new RuntimeException("udun wallet do not have the coin:" + coin);
        }
        return coin;
    }

    @Override
    public CommonResponse<List<String>> getUnUsedAddress(AddressParam addressParam) {
        Coin coin = getCoinByName(addressParam.getCoin());
        UDunAddressParam param = new UDunAddressParam();
        Address address = udunClient.createAddress(param.getMainCoinType(), "", "", param.getCallUrl());
        return CommonResponse.successOf(Collections.singletonList(address.getAddress()));
    }

    // sign=md5(body + key + nonce + timestamp).toLowerCase()
    @Override
    public CommonResponse<TxEntityVo> createWithdraw(WithdrawCreateParam withdrawCreateParam) {
        Coin coin = getCoinByName(withdrawCreateParam.getTxCoin());
        UDunWithdrawCreateParam param = new UDunWithdrawCreateParam();
        ResultMsg withdraw = udunClient.withdraw(param.getAddress(), param.getAmount(), param.getMainCoinType(), param.getCoinType(), param.getBizId(), param.getMemo(), param.getCallUrl());
        if (withdraw.getCode().equals(200)) {
            return CommonResponse.successOf(new TxEntityVo());
        }
        throw new RuntimeException("提现失败:" + withdraw.getMessage());
    }

    @Override
    public CommonResponse<?> cancelWithdraw(WithdrawCancelParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<?> getWithdrawRecord(WithdrawGetParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<List<WalletTransactionVo>> flowWithdrawRecords(WithdrawFlowParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<PageInfo<WalletTransactionVo>> pageWithdrawRecords(WithdrawPageParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<String> getWalletPublicKey() {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<?> updSidPublicKey(UpdPublicKeyParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<List<CoinConfigVo>> listCoinConfig() {
        List<Coin> coins = udunClient.listSupportCoin(false);
        coinMap = coins.stream().collect(Collectors.toMap(Coin::getName, Function.identity()));
        List<CoinConfigVo> collect = coins.stream().map(coin -> {
            CoinConfigVo coinConfigVo = new CoinConfigVo();
            coinConfigVo.setCode(coin.getSymbol());
            coinConfigVo.setMainCoinType(coin.getMainSymbol());
            coinConfigVo.setCoinFullName(coin.getName());
            coinConfigVo.setMaxPrecision(Integer.parseInt(coin.getDecimals()));
            boolean serious = coin.getTokenStatus().equals(1);
            coinConfigVo.setSerious(serious);
            return coinConfigVo;
        }).collect(Collectors.toList());
        return CommonResponse.successOf(collect);
    }

    @Override
    public CommonResponse<List<WalletTransactionVo>> flowTransactionRecords(TransactionFlowParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<PageInfo<WalletTransactionVo>> pageTransactionRecords(TransactionPageParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<WalletTransactionVo> findByHash(TransactionHashParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<Boolean> addressVerify(AddressVerifyParam verifyParam) {
        Coin coin = getCoinByName(verifyParam.getCoinType());
        UDunAddressVerifyParam param = new UDunAddressVerifyParam();
        boolean b = udunClient.checkAddress(param.getMainCoinType(), param.getAddress());
        return CommonResponse.successOf(b);
    }

    @Override
    public CommonResponse<List<String>> getAllocatedAddress(AddressParam param) {
        throw new RuntimeException("udun not support");
    }

    @Override
    public CommonResponse<List<AddressBalanceVo>> addressBalanceList(AddressBalanceParam param) {
        throw new RuntimeException("udun not support");
    }
}
