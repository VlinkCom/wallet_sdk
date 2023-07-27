package com.weibi.wallet.rest.sdk.proxy;


import com.weibi.wallet.rest.sdk.params.*;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;
import com.weibi.wallet.rest.sdk.vo.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WalletRestProxy {
    /* 获取指定数量的地址 */
    CommonResponse<List<String>> getAddress(AddressParam param);
    CommonResponse<List<String>> getUnUsedAddress(AddressParam param);

    /* 查询已分配地址 */
    CommonResponse<List<String>> getAllocatedAddress(AddressParam param);

    /* 获取指定地址余额 */
    CommonResponse<List<AddressBalanceVo>> addressBalanceList(AddressBalanceParam param);

    CommonResponse<List<CoinConfigVo>> listCoinConfig(BaseParam param);

    CommonResponse<List<DepositVo>> flowDepositRecords(DepositFlowParam param);

    CommonResponse<PageInfo<DepositVo>> pageDepositRecords(DepositPageParam param);

    CommonResponse<TxEntityVo> createWithdraw(WithdrawCreateParam param);

    CommonResponse<?> cancelWithdraw(WithdrawCancelParam param);
    CommonResponse<?> getWithdrawRecord(WithdrawGetParam param);

    CommonResponse<List<DepositVo>> flowWithdrawRecords(WithdrawFlowParam param);
    CommonResponse<PageInfo<DepositVo>> pageWithdrawRecords(WithdrawPageParam param);
}
