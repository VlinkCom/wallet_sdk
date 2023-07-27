package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionPageParam {
    private String coinType;
    private String innerStatus;
    private String chain;
    private Long start;
    private Long end;
    private Integer size;
    private Integer page;

}
