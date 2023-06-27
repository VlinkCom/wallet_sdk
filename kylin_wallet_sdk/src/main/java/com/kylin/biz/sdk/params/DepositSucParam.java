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

    // from
    private String fromAddress;

    // to
    private String toAddress;

    // 币种
    private String coinType;

    // 充值数量
    private BigDecimal amount;

    // 手续费
    private BigDecimal fee;

    // txId
    private String txId;

    // DEPOSIT("DEPOSIT", "充值"),
    // WITHDRAW("WITHDRAW", "提现"),
    private String type;

    // 描述
    private String remark;

    // 业务id 充值为空
    private String bizId;

}
