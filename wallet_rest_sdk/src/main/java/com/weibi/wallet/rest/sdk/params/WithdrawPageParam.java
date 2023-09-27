package com.weibi.wallet.rest.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawPageParam{



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
