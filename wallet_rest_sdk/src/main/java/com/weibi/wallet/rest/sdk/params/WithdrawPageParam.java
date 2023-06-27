package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawPageParam extends BaseParam{



    //(value = "币种类型")
    private String coinType;

    //(value = "币种chain")
    private String chain;

    
    //结束时间
    private Long start;
    
    // 开始时间
    private Long end;

    private Integer page;
    
    //("数量")
    private Integer size;
}
