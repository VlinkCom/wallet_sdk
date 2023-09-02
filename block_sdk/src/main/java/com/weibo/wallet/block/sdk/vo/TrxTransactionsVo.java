package com.weibo.wallet.block.sdk.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class TrxTransactionsVo {
    private List<TrxRet> ret;

    private List<String> signature;

    private String txID;

    private TrxTransRawData raw_data;

    private String raw_data_hex;

    @Data
    static class TrxRet {
        private String contractRet;
    }
    @Data
    static class TrxTransRawData {
        private List<TrxContract> contract;

        private String ref_block_bytes;

        private String ref_block_hash;

        private BigInteger expiration;

        private BigInteger fee_limit;

        private BigInteger timestamp;
    }

    @Data
    static class TrxContract {
        private TrxContractParameter parameter;

        private String type;
    }

    @Data
    static class TrxContractParameter {

        private  TrxContractParamValue value;

        private String type_url;
    }

    @Data
    static class TrxContractParamValue {

        private BigInteger amount;

        private String to_address;

        private String owner_address;

        /** TRC10 通证ID */
        private String asset_name;

        /** TRC20 合约地址*/
        private String contract_address;

        /** TRC20 data*/
        private String data;

    }
}
