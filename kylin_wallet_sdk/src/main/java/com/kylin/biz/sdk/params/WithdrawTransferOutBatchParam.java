package com.kylin.biz.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawTransferOutBatchParam {

    private List<WithdrawTransferOutParam> txParamList;
}
