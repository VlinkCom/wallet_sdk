package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressParam {
    private String coin;
    private Integer size;
}
