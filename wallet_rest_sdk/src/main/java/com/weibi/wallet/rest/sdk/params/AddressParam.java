package com.weibi.wallet.rest.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressParam {
    private String coin;
    private Integer size;
}
