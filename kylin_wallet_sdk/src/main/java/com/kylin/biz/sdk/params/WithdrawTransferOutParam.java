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
public class WithdrawTransferOutParam {

    private String bizId;

    private Long     txId;

    private String     toAddress;

    private BigDecimal amount;

    private String txHash;

    private BigInteger nonce;
}
