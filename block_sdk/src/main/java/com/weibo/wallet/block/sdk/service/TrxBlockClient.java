package com.weibo.wallet.block.sdk.service;

import com.google.gson.Gson;
import com.weibo.wallet.block.sdk.enums.NodeTypeApiEnum;
import com.weibo.wallet.block.sdk.param.TrxTransactionHashParam;
import com.weibo.wallet.block.sdk.vo.TrxTransactionsVo;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TrxBlockClient implements BlockClient{

    @Value("${NOW_NODES_API_KEY:ca47c572-7878-4c42-96c2-dfac3b578be9}")
    private String apiKey;

    private static Gson gson = new Gson();

    @Override
    public <T, B> T getPaymentsByTransactionHash(B b, Class<T> clazz) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            String json = gson.toJson(b);
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("https://trx.nownodes.io/wallet/gettransactionbyid")
                    .method("POST", body)
                    .addHeader("api-key", apiKey)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody respBody = response.body();
            if (!response.isSuccessful()) {
                throw new RuntimeException("trx getPaymentsByTransactionHash response fail " + respBody.string());
            }
            return gson.fromJson(respBody.string(), clazz);
        } catch (Exception ex) {
            log.error("trx getPaymentsByTransactionHash err:{}", ex);
            throw new RuntimeException("trx getPaymentsByTransactionHash err");
        }
    }

    @Override
    public Integer getCode() {
        return NodeTypeApiEnum.TRX_LIKE_RPC.getCode();
    }

    public static void main(String[] args) throws IOException {
        TrxBlockClient trxBlockClient = new TrxBlockClient();
        TrxTransactionHashParam param = new TrxTransactionHashParam();
        param.setValue("413004d110f4a15584517e54781855a26eef700c85b88efb89bd9c7a76f8931b");
        TrxTransactionsVo trxTransactionsVo = trxBlockClient.getPaymentsByTransactionHash(param, TrxTransactionsVo.class);
        System.out.println(trxTransactionsVo);
    }
}
