package com.weibi.wallet.rest.sdk.params;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBalanceParam extends BaseParam {
    private String address;
    private String id;
    private Integer limit;
}
