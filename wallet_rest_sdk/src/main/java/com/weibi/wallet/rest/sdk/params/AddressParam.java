package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressParam extends BaseParam {
    private String coin;
    private Integer size;
}
