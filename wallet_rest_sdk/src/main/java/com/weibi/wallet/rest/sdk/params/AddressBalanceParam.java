package com.weibi.wallet.rest.sdk.params;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBalanceParam {
    private String address;
    private String id;
    private Integer limit;
}
