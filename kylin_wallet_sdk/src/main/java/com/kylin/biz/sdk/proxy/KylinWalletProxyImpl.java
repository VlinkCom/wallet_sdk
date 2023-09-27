package com.kylin.biz.sdk.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kylin.biz.sdk.params.*;
import com.kylin.biz.sdk.resp.CommonResponse;
import com.kylin.biz.sdk.util.EnvUtil;
import com.kylin.biz.sdk.util.PUtils;
import com.kylin.biz.sdk.util.WalletRSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Slf4j
public class KylinWalletProxyImpl implements KylinWalletProxy{
    private final Gson gson = new Gson();

    private final String X_SIGNATURE = "X_SIGNATURE";
    private final String X_ACCESS_KEY = "X_ACCESS_KEY";


    public final String publicKey;
    public String privateKey;
    private final RestTemplate restTemplate;
    public KylinWalletProxyImpl(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(8000);
        restTemplate = new RestTemplate(httpRequestFactory);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        this.privateKey = privateKey;
        if(!EnvUtil.isTestOrQa()) {
            try {
                this.privateKey =PUtils.decrypt(privateKey);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public CommonResponse<?> depositBlockInfoCallback(String urlPre, DepositBlockInfoParam dto) {
//        String url = urlPre +"/deposit/notify";
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity entity = new HttpEntity(dto, headers);
//        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
////        if (response.getStatusCode() != HttpStatus.OK) {
////            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
////        }
//        return gson.fromJson(response.getBody(), CommonResponse.class);
        return CommonResponse.success();
    }

    @Override
    public CommonResponse<?> depositSucCallback(String urlPre, DepositSucParam dto) {
        String url = urlPre +"/depositCallback";
        log.info("请求地址，url:{}",url);
        HttpHeaders headers = buildHeader("");

        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        CommonResponse<?> commonResponse = gson.fromJson(response.getBody(), CommonResponse.class);
        return commonResponse;
    }

    @Override
    public CommonResponse<?> withdrawSucCallback(String urlPre, WithdrawSucParam dto) {
        String url = urlPre +"/withdrawCallback";
        HttpHeaders headers = buildHeader("");
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        return gson.fromJson(response.getBody(), CommonResponse.class);
    }

    @Override
    public CommonResponse<?> withdrawTransferOutCallback(String urlPre, WithdrawTransferOutParam dto) {
        String url = urlPre +"/checkSum";
        HttpHeaders headers = buildHeader("");
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        return gson.fromJson(response.getBody(), CommonResponse.class);
    }

    @Override
    public CommonResponse<?> withdrawTransferOutBatchCallback(String urlPre, WithdrawTransferOutBatchParam dto) {
//        String url = kylinHost +"/withdraw/batch/notify";
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity entity = new HttpEntity(dto, headers);
//        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
//        return gson.fromJson(response.getBody(), CommonResponse.class);
        return CommonResponse.success();
    }

    private HttpHeaders buildHeader(String str) {
        if (StringUtils.isEmpty(publicKey) || StringUtils.isEmpty(privateKey)) {
            throw new RuntimeException("不安全，并不合法的请求");
        }
        HttpHeaders headers = new HttpHeaders();
        String sign = null;
        try {
            sign = WalletRSAUtil.sign(str.getBytes(), privateKey);
        } catch (Exception e) {
            throw new RuntimeException("sign error");
        }
        headers.add(X_SIGNATURE,sign);
        log.info("构建请求公钥:{}", publicKey);
        headers.add(X_ACCESS_KEY,publicKey);
        return headers;
    }

    public static void main(String[] args) {
//        KylinWalletProxyImpl proxy = new KylinWalletProxyImpl();
//        KylinWalletProxyImpl proxy = new KylinWalletProxyImpl();
//        CommonResponse<?> c1 = proxy.depositSucCallback(new DepositSucParam());
//        CommonResponse<?> c2 = proxy.depositBlockInfoCallback(new DepositBlockInfoParam());
//        CommonResponse<?> c3 = proxy.withdrawSucCallback(new WithdrawSucParam());
//        CommonResponse<?> c4 = proxy.withdrawTransferOutCallback(new WithdrawTransferOutParam());
//        System.out.println("xxxx");
    }
}
