package com.weibi.wallet.rest.sdk.params.udun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UDunWithdrawCreateParam {
    
    //(value = "业务id")
    private String bizId;

    //(value = "币种chain")
    private String address;

    //(value = "用户id")
    private BigDecimal amount;

    //(value = "币种类型")
    private String mainCoinType;
    
    //("钱包 from 地址")
    private String coinType;
    
    //("钱包 to 地址")
    private String memo;
    private String  callUrl;
}
