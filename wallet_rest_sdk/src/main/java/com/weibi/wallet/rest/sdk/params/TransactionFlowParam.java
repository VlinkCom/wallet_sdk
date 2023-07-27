package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionFlowParam extends BaseParam {
    private String id;
    private String coinType;
    private String innerStatus;
    private String chain;
    private Long start;
    private Long end;
    private Long size;

}
