package com.kylin.biz.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositSucParam {

    private Integer id;

    // to
    private String addressTo;

    // 币种
    private String coin;

    // 充值数量
    private String amount;

    // txId
    private String txId;

    private String confirmations;

    private String depositTime;

    private String chain;

}
