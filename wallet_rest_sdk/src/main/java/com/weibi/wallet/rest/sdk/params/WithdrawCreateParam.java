package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawCreateParam {
    
    //(value = "业务id")
    private String bizId;

    //(value = "币种类型")
    private String txCoin;

    //(value = "币种chain")
    private String chain;

    //(value = "用户id")
    private String userId;

    
    //("钱包 from 地址")
    private String txFromWallet;
    
    //("钱包 to 地址")
    private String txToWallet;

    
    //("提现数量")
    private BigDecimal txAmount;

    
    //("提现手续费")
    private BigDecimal txFee;
}
