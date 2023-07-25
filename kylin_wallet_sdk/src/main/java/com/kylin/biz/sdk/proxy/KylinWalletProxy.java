package com.kylin.biz.sdk.proxy;

import com.kylin.biz.sdk.params.*;
import com.kylin.biz.sdk.resp.CommonResponse;

public interface KylinWalletProxy {

    CommonResponse<?> depositBlockInfoCallback(DepositBlockInfoParam dto);

    CommonResponse<?> depositSucCallback(DepositSucParam dto);

    CommonResponse<?> withdrawSucCallback(WithdrawSucParam dto);

    CommonResponse<?> withdrawTransferOutCallback(WithdrawTransferOutParam dto);

    CommonResponse<?> withdrawTransferOutBatchCallback(WithdrawTransferOutBatchParam dto);
}
