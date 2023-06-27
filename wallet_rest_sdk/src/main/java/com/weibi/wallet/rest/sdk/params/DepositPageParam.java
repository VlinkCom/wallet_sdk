package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositPageParam extends BaseParam {
    private String coinType;
    private String innerStatus;
    private String chain;
    private Long start;
    private Long end;
    private Integer size;
    private Integer page;

}
