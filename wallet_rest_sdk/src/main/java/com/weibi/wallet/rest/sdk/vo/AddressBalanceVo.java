package com.weibi.wallet.rest.sdk.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBalanceVo {
    // 序列号
    private String id;

    // 服务id
    private Integer sId;

    //("地址资金")
    private BigDecimal balance;

    //("地址")
    private String address;

    //("币种")
    private String coinType;

    //("链")
    private String chain;

    //("更新时间")
    private Date updateTime;
}