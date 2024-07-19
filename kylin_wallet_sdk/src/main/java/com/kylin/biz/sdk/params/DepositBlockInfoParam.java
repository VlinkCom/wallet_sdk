package com.kylin.biz.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositBlockInfoParam {

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

    //当前确认数
    private Long confirmations;

    //最大确认数
    private Long confirmedCount;
    
    private Date updateTime;

    private Date createTime;

    private Boolean isFinished;

    private String disposeStatusInner;

    private Integer auditStatus;

    private String source;

    private String chain;

    private String val;

}
