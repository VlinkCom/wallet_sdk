package com.weibo.wallet.block.sdk.service;

import com.google.gson.Gson;
import com.weibo.wallet.block.sdk.enums.NodeTypeApiEnum;
import com.weibo.wallet.block.sdk.param.EthTransactionHashParam;
import com.weibo.wallet.block.sdk.vo.EthTransactionsVo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EthBlockClient implements BlockClient{

    @Value("${NOW_NODES_API_KEY:ca47c572-7878-4c42-96c2-dfac3b578be9}")
    private String apiKey;
    private static Gson gson = new Gson();

    @Override
    public <T, B> T getPaymentsByTransactionHash(B b, Class<T> clazz){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, gson.toJson(b));
            Request request = new Request.Builder()
                    .url("https://eth.nownodes.io/" + apiKey)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody respBody = response.body();
            if (!response.isSuccessful()) {
                throw new RuntimeException("eth getPaymentsByTransactionHash response fail " + respBody.string());
            }
            return gson.fromJson(respBody.string(), clazz);
        } catch (Exception ex) {
            log.error("eth getPaymentsByTransactionHash err:{}", ex);
            throw new RuntimeException("eth getPaymentsByTransactionHash err");
        }

    }

    @Override
    public Integer getCode() {
        return NodeTypeApiEnum.ETH_LIKE_RPC.getCode();
    }

    public static void main(String[] args) {
        EthBlockClient trxBlockClient = new EthBlockClient();
        EthTransactionHashParam param = new EthTransactionHashParam();
        param.getParams().add("0x88df016429689c079f3b2f6ad39fa052532c56795b733da78a91ebe6a713944b");
        EthTransactionsVo trxTransactionsVo = trxBlockClient.getPaymentsByTransactionHash(param, EthTransactionsVo.class);
        System.out.println(trxTransactionsVo);
    }
}
