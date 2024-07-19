package com.kylin.biz.sdk.params;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawSucParam {

//    @ApiModelProperty(value = "业务id")
    private String bizId;
//    @ApiModelProperty(value = "提现地址")
    private String addressTo;
//    @ApiModelProperty(value = "hash地址")
    private String txId;
//    @ApiModelProperty(value = "确认数")
    private String confirmations;
//    @ApiModelProperty(value = "提现状态 -1->充提失败 0-> 充提确认  1->提现已提交  2->提现取消  3->充值确认中 4->提现已审核 5->等待确认中 6->系统错误  7->管理员回退")
    private String status;
    
    private String paymentCategory;
}
