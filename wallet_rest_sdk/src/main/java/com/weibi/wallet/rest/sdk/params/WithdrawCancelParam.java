package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawCancelParam {

    //(value = "业务id")
    private String bizId;
}
