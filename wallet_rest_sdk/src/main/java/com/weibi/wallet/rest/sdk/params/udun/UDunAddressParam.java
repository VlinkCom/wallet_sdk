package com.weibi.wallet.rest.sdk.params.udun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UDunAddressParam {
    private String merchantId;
    private String mainCoinType;
    private String callUrl;
}
