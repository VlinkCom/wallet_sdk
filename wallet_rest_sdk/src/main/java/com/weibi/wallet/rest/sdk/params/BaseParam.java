package com.weibi.wallet.rest.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseParam {
    private String privateKey;
    private String publicKey;

    public void validParam() {
        if (StringUtils.isEmpty(privateKey) || StringUtils.isEmpty(publicKey)) {
            log.error("privateKey | publicKey 不能为空");
            throw new RuntimeException("不合法的参数");
        }
    }
}
