package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawFlowParam extends BaseParam{

    //(value = "业务id")
    private Long id;

    //(value = "币种类型")
    private String coinType;

    //(value = "币种chain")
    private String chain;

    
    // 开始时间
    private Long start;
    
    // 结束时间
    private Long end;

    
    // 数量限制
    private Integer size;
}
