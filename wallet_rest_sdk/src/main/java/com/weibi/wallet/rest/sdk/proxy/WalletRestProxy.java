package com.weibi.wallet.rest.sdk.proxy;


import com.weibi.wallet.rest.sdk.params.*;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;
import com.weibi.wallet.rest.sdk.vo.*;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WalletRestProxy {
    /* 获取指定数量的地址 */
    CommonResponse<List<String>> getAddress(AddressParam param);
    CommonResponse<List<String>> getUnUsedAddress(AddressParam param);

    CommonResponse<Boolean> addressVerify(AddressVerifyParam param);

    /* 查询已分配地址 */
    CommonResponse<List<String>> getAllocatedAddress(AddressParam param);

    /* 获取指定地址余额 */
    CommonResponse<List<AddressBalanceVo>> addressBalanceList(AddressBalanceParam param);

    CommonResponse<List<CoinConfigVo>> listCoinConfig();

    CommonResponse<List<WalletTransactionVo>> flowTransactionRecords(TransactionFlowParam param);

    CommonResponse<PageInfo<WalletTransactionVo>> pageTransactionRecords(TransactionPageParam param);

    CommonResponse<List<WalletTransactionVo>> flowDepositRecords(TransactionFlowParam param);

    CommonResponse<PageInfo<WalletTransactionVo>> pageDepositRecords(TransactionPageParam param);

    CommonResponse<WalletTransactionVo> findByHash(TransactionHashParam param);

    CommonResponse<TxEntityVo> createWithdraw(WithdrawCreateParam param);

    CommonResponse<?> cancelWithdraw(WithdrawCancelParam param);
    CommonResponse<?> getWithdrawRecord(WithdrawGetParam param);

    CommonResponse<List<WalletTransactionVo>> flowWithdrawRecords(WithdrawFlowParam param);
    CommonResponse<PageInfo<WalletTransactionVo>> pageWithdrawRecords(WithdrawPageParam param);


    CommonResponse<String> getWalletPublicKey();

    CommonResponse<?> updSidPublicKey(UpdPublicKeyParam param);


}
