package com.weibi.wallet.rest.sdk.params;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionHashParam extends BaseParam {
    private String hash;

}
