package com.weibi.wallet.rest.sdk.params;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBalanceParam {
    private String address;
    private String id;
    private Integer limit;
}
