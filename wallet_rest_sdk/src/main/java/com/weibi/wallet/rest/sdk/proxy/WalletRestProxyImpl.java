package com.weibi.wallet.rest.sdk.proxy;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weibi.wallet.rest.sdk.anno.WalletRestRequest;
import com.weibi.wallet.rest.sdk.params.*;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;
import com.weibi.wallet.rest.sdk.util.MapUtil;
import com.weibi.wallet.rest.sdk.util.ParamsSingUtil;
import com.weibi.wallet.rest.sdk.vo.*;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WalletRestProxyImpl implements WalletRestProxy {

    @Value("${WALLET_REST_HOST}")
    private String walletRestHost;

    private static RestTemplate restTemplate;

    private final String X_SIGNATURE = "X_SIGNATURE";
    private final String X_ACCESS_KEY = "X_ACCESS_KEY";

    private final Gson gson = new Gson();

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
    public CommonResponse<List<String>> getAddress(AddressParam param) {
        String url = walletRestHost +"/address/un_used?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<String>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<String>> getAllocatedAddress(AddressParam param) {
        String url = walletRestHost +"/address/allocated?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<String>>>() {
        }.getType());
    }



    @Override
    public CommonResponse<List<AddressBalanceVo>> addressBalanceList(AddressBalanceParam param) {
        String url = walletRestHost +"/address/balance?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<AddressBalanceVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<CoinConfigVo>> listCoinConfig(BaseParam param) {
        String url = walletRestHost +"/coin/list?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<CoinConfigVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<DepositVo>> flowDepositRecords(DepositFlowParam param) {
        String url = walletRestHost +"/deposit/low?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<DepositVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<PageInfo<DepositVo>> pageDepositRecords(DepositPageParam param) {
        String url = walletRestHost +"/deposit/page?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<PageInfo<DepositVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<TxEntityVo> createWithdraw(WithdrawCreateParam param) {
        String url = walletRestHost +"/withdraw/create?";
        String body = doPost(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<TxEntityVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<?> cancelWithdraw(WithdrawCancelParam param) {
        String url = walletRestHost +"/withdraw/cancel?";
        String body = doDelete(url, param);
        return gson.fromJson(body, CommonResponse.class);
    }

    @Override
    public CommonResponse<TxEntityVo> getWithdrawRecord(WithdrawGetParam param) {
        String url = walletRestHost +"/withdraw/record?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<TxEntityVo>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<DepositVo>> flowWithdrawRecords(WithdrawFlowParam param) {
        String url = walletRestHost +"/withdraw/low?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<TxEntityVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<PageInfo<DepositVo>> pageWithdrawRecords(WithdrawPageParam param) {
        String url = walletRestHost +"/withdraw/page?";
        String body = doGet(url, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<PageInfo<TxEntityVo>>>() {
        }.getType());
    }

    private String doDelete(String url, BaseParam param) {
        Map<String, Object> map = MapUtil.beanToMap(param);
        String str = ParamsSingUtil.signToString(map);
        HttpHeaders headers = buildHeader(str, param);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange( url + str,HttpMethod.DELETE, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url + str +"请求异常:" + response.getBody());
        }
        return response.getBody();
    }

    private String doPost(String url, BaseParam param) {
        Map<String, Object> map = MapUtil.beanToMap(param);
        String str = ParamsSingUtil.signToString(map);
        HttpHeaders headers = buildHeader(str, param);
        HttpEntity entity = new HttpEntity(param, headers);
        ResponseEntity<String> response = restTemplate.exchange( url + str,HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url + str +"请求异常:" + response.getBody());
        }
        return response.getBody();
    }


    private String doGet(String url, BaseParam param) {
        Map<String, Object> map = MapUtil.beanToMap(param);
        String str = ParamsSingUtil.signToString(map);
        HttpHeaders headers = buildHeader(str, param);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange( url + str,HttpMethod.GET, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url + str +"请求异常:" + response.getBody());
        }
        return response.getBody();
    }

    private HttpHeaders buildHeader(String str, BaseParam param) {
        if (StringUtils.isEmpty(param.getPrivateKey()) || StringUtils.isEmpty(param.getPublicKey())) {
            throw new RuntimeException("不安全，并不合法的请求");
        }
        HttpHeaders headers = new HttpHeaders();
        String sign = HmacUtils.hmacSha256Hex(param.getPrivateKey(), str);
        headers.add(X_SIGNATURE,sign);
        headers.add(X_ACCESS_KEY,param.getPublicKey());
        return headers;
    }
}
