package com.weibi.wallet.rest.sdk.params.udun;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UDunAddressVerifyParam {
    private String address;

    private String mainCoinType;
}
