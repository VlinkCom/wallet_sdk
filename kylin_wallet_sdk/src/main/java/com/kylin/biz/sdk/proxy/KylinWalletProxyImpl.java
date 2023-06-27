package com.kylin.biz.sdk.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kylin.biz.sdk.params.DepositBlockInfoParam;
import com.kylin.biz.sdk.params.WithdrawSucParam;
import com.kylin.biz.sdk.params.WithdrawTransferOutParam;
import com.kylin.biz.sdk.resp.CommonResponse;
import com.kylin.biz.sdk.params.DepositSucParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class KylinWalletProxyImpl implements KylinWalletProxy{
    private final Gson gson = new Gson();

    private String kylinHost;

    private static RestTemplate restTemplate;
    public KylinWalletProxyImpl() {
        String kylinHostUrl = System.getenv("KYLIN_HOST_URL");
        kylinHost = StringUtils.hasLength(kylinHostUrl) ? kylinHostUrl : "https://hipiex.net/pro";
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(8000);
        restTemplate = new RestTemplate(httpRequestFactory);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
    }


    @Override
    public CommonResponse<?> depositBlockInfoCallback(DepositBlockInfoParam dto) {
        String url = kylinHost +"/deposit/notify";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), CommonResponse.class);
    }

    @Override
    public CommonResponse<?> depositSucCallback(DepositSucParam dto) {

        String url = kylinHost +"/deposit/confirm";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), CommonResponse.class);
    }

    @Override
    public CommonResponse<?> withdrawSucCallback(WithdrawSucParam dto) {
        String url = kylinHost +"/withdraw/confirm";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), CommonResponse.class);
    }

    @Override
    public CommonResponse<?> withdrawTransferOutCallback(WithdrawTransferOutParam dto) {
        String url = kylinHost +"/withdraw/notify";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), CommonResponse.class);
    }

    public static void main(String[] args) {
        KylinWalletProxyImpl proxy = new KylinWalletProxyImpl();
//        KylinWalletProxyImpl proxy = new KylinWalletProxyImpl();
        CommonResponse<?> c1 = proxy.depositSucCallback(new DepositSucParam());
        CommonResponse<?> c2 = proxy.depositBlockInfoCallback(new DepositBlockInfoParam());
        CommonResponse<?> c3 = proxy.withdrawSucCallback(new WithdrawSucParam());
        CommonResponse<?> c4 = proxy.withdrawTransferOutCallback(new WithdrawTransferOutParam());
        System.out.println("xxxx");
    }
}
