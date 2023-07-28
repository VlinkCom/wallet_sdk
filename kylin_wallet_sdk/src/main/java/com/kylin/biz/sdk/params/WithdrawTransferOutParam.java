package com.kylin.biz.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawTransferOutParam {

    private String bizId;

    private String addressTo;

    private String amount;

    private String coin;

    private String chain;
}
