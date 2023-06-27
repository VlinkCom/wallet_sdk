package com.kylin.biz.sdk.proxy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kylin.biz.sdk.params.WithdrawSucParam;
import com.kylin.biz.sdk.resp.CommonResponse;
import com.kylin.biz.sdk.params.DepositSucParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class KylinWalletProxyImpl implements KylinWalletProxy{
    private final Gson gson = new Gson();

    @Value("${KYLIN_HOST_URL}")
    private String kylinHost;

    private static RestTemplate restTemplate;

    @PostConstruct
    public void init() {
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
    public CommonResponse<?> depositSucCallback(DepositSucParam dto) {

        String url = kylinHost +"/withdraw/record";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), new TypeToken<CommonResponse<String>>() {
        }.getType());
    }

    @Override
    public CommonResponse<?> withdrawSucCallback(WithdrawSucParam dto) {
        String url = kylinHost +"/withdraw/record";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), new TypeToken<CommonResponse<String>>() {
        }.getType());
    }

    @Override
    public CommonResponse<?> withdrawTransferOutCallback(WithdrawSucParam dto) {
        String url = kylinHost +"/withdraw/record";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(dto, headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url +"请求异常:" + response.getBody());
        }
        return gson.fromJson(response.getBody(), new TypeToken<CommonResponse<String>>() {
        }.getType());
    }
}
