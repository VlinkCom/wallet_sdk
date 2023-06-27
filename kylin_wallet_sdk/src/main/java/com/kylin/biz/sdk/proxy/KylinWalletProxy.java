package com.kylin.biz.sdk.proxy;

import com.kylin.biz.sdk.params.DepositBlockInfoParam;
import com.kylin.biz.sdk.params.WithdrawSucParam;
import com.kylin.biz.sdk.params.WithdrawTransferOutParam;
import com.kylin.biz.sdk.resp.CommonResponse;
import com.kylin.biz.sdk.params.DepositSucParam;

public interface KylinWalletProxy {

    CommonResponse<?> depositBlockInfoCallback(DepositBlockInfoParam dto);

    CommonResponse<?> depositSucCallback(DepositSucParam dto);

    CommonResponse<?> withdrawSucCallback(WithdrawSucParam dto);

    CommonResponse<?> withdrawTransferOutCallback(WithdrawTransferOutParam dto);
}
