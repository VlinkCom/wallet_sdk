package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawGetParam {

    //(value = "业务id")
    private String bizId;

}
