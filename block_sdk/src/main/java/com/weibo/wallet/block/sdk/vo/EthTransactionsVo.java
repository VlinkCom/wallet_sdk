package com.weibo.wallet.block.sdk.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class EthTransactionsVo {

    private String jsonrpc;
    private Long id;
    private EthTransaction result;

    @Data
    static class EthTransaction {
        private String hash;
        private String nonce;
        private String blockHash;
        private String blockNumber;
        private String transactionIndex;
        private String from;
        private String to;
        private String value;
        private String gasPrice;
        private String gas;
        private String input;
        private String type;
        private String r;
        private String s;
        private String v;
    }
}
