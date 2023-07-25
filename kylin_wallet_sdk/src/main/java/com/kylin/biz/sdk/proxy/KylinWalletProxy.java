package com.kylin.biz.sdk.proxy;

import com.kylin.biz.sdk.params.*;
import com.kylin.biz.sdk.resp.CommonResponse;

public interface KylinWalletProxy {

    CommonResponse<?> depositBlockInfoCallback(String urlPre, DepositBlockInfoParam dto);

    CommonResponse<?> depositSucCallback(String urlPre, DepositSucParam dto);

    CommonResponse<?> withdrawSucCallback(String urlPre, WithdrawSucParam dto);

    CommonResponse<?> withdrawTransferOutCallback(String urlPre, WithdrawTransferOutParam dto);

    CommonResponse<?> withdrawTransferOutBatchCallback(String urlPre, WithdrawTransferOutBatchParam dto);
}
