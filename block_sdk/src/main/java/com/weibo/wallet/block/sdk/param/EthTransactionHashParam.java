package com.weibo.wallet.block.sdk.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EthTransactionHashParam {
    // hash
    private String jsonrpc = "2.0";

    private String method = "eth_getTransactionByHash";

    private List<String> params = new ArrayList<>();

    private Long id = 1L;

}
