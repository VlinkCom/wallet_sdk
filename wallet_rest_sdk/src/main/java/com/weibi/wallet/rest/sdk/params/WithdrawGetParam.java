package com.weibi.wallet.rest.sdk.params;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawGetParam extends BaseParam{

    //(value = "业务id")
    private String bizId;

    @Override
    public void validParam() {
        super.validParam();
        if (StringUtils.isEmpty(bizId)) {
            log.error("bizId不能为空");
            throw new RuntimeException("提现参数异常");
        }
    }
}
