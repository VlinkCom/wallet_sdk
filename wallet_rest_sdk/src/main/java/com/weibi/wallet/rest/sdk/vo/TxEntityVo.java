package com.weibi.wallet.rest.sdk.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TxEntityVo {
    private Long id;

    private Integer sId;


    private String bizId;

//    private String userId;

    private String txCoin;

    private Integer txType;

    private String txFromWallet;

    private String txToWallet;

    private BigDecimal txAmount;

    private BigDecimal txFee;

    private Integer txStatus;

    private String txStatusInner;

    private Integer txDepositStatus;

    private String txDepositDesc;

    private String txNetworkId;

    private Long heightInit;

    private Date txNetworkTime;

    private BigDecimal txNetworkFee;

    private Long updateTime;

    private Long createdTime;

    private String extraInfo;

    private String remark;

    private String nonce;

    private String chain;
}
