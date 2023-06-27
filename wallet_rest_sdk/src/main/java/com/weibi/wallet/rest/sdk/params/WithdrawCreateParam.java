package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawCreateParam extends BaseParam{
    
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

    @Override
    public void validParam() {
        super.validParam();
        if (StringUtils.isEmpty(bizId)
                || StringUtils.isEmpty(chain)
                || StringUtils.isEmpty(txFromWallet)
                || StringUtils.isEmpty(txToWallet)
                || Objects.isNull(txAmount)
                || Objects.isNull(txFee)
        ) {
            log.error("bizId:{},chain:{},txFromWallet:{},txToWallet:{},txAmount:{},txFee:{}不能为空", bizId, chain,txFromWallet, txToWallet, txAmount, txFee);
            throw new RuntimeException("提现参数异常");
        }
    }
}
