package com.weibo.wallet.block.sdk.enums;

import lombok.Getter;

@Getter
public enum NodeTypeApiEnum {
    BTC_LIKE_RPC(0,"btc like rpc"),

    BTC_LIKE_BLOCKBOOK(1,"btc like blockbook"),

    BTC_LIKE_WS(2,"btc like websocket"),

    ETH_LIKE_RPC(3,"eth like rpc"),
    TRX_LIKE_RPC(4,"trx like rpc"),

    ETH_LIKE_BACKUP_RPC(5,"eth like backup rpc"),
    TRX_LIKE_BACKUP_RPC(6,"trx like backup rpc"),
    BTC_LIKE_BACKUP_RPC(7,"btc like backup rpc");

    private int code;

    private String desc;

    NodeTypeApiEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
