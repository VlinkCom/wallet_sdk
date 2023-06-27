package com.weibi.wallet.rest.sdk.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DepositVo {
    private String id;

    // 服务id, 交易所id
    private Integer sId;

    private String coin;

    private String hash;

    private String blockHash;

    private Date blockTime;

    private BigDecimal amount;

    private BigDecimal fee;

    private String fromAccountOrAddress;

    private String toAddress;

    private String paymentCategory;

    private String status;

    private Long blockNumberOrPosition;

    private String memo;

    private Long confirmations;

    private Date updateTime;

    private Date createTime;

    private Boolean isFinished;

    private String disposeStatusInner;

    private Integer auditStatus;

    private String source;

    private String chain;
}
