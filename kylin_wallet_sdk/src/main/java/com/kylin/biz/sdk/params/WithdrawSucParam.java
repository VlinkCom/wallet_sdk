package com.kylin.biz.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawSucParam {

    private String bizId;

    private Integer sId;

    private String coin;

    private String hash;
    private String outputHash;


    private String blockHash;

    /** 时间的表示， 单位是毫秒 */
    private BigInteger blockTime;

    private BigDecimal amount;

    private BigDecimal fee;

    private String fromAccountOrAddress;

    private String toAddress;

    /** 来源于 PaymentCategory */
    private String paymentCategory;

    /** 来源于 PaymentStatusEnum，这个值的计算逻辑应该来源于 blockNumber或者confirmations */
    private String status;

    /** ==========  block 高度 *,可能为空  ==========  */
    private BigInteger blockNumber;

    /**========== confirmations，可能为空 =========== */
    private Integer confirmations = -1;

    /**========== memo，可能为空 =========== */
    private String memo ="";

    private String blockInfoId;

    private BigInteger gasPrice;

    private String coboId;

    private String chain;

    private int outIndex;

    private String inputTx;

    private boolean spent;
}
