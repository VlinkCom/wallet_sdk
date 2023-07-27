package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionFlowParam {
    private String id;
    private String coinType;
    private String innerStatus;
    private String chain;
    private Long start;
    private Long end;
    private Long size;

}
