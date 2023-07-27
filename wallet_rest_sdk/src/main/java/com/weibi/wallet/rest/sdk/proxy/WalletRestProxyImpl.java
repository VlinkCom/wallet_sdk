package com.weibi.wallet.rest.sdk.proxy;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weibi.wallet.rest.sdk.params.*;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;
import com.weibi.wallet.rest.sdk.util.MapUtil;
import com.weibi.wallet.rest.sdk.util.ParamsSingUtil;
import com.weibi.wallet.rest.sdk.vo.*;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WalletRestProxyImpl implements WalletRestProxy {

    private final String walletRestHost;

    private final String publicKey;
    private final String privateKey;

    private final RestTemplate restTemplate;

    private final String X_SIGNATURE = "X_SIGNATURE";
    private final String X_ACCESS_KEY = "X_ACCESS_KEY";

    private final Gson gson = new Gson();

    public WalletRestProxyImpl(String walletRestHost, String publicKey, String privateKey) {
        this.walletRestHost = walletRestHost;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
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
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<String>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<String>> getUnUsedAddress(AddressParam param) {
        String url = walletRestHost +"/address/unused?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<String>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<String>> getAllocatedAddress(AddressParam param) {
        String url = walletRestHost +"/address/allocated?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<String>>>() {
        }.getType());
    }



    @Override
    public CommonResponse<List<AddressBalanceVo>> addressBalanceList(AddressBalanceParam param) {
        String url = walletRestHost +"/address/balance?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<AddressBalanceVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<CoinConfigVo>> listCoinConfig() {
        String url = walletRestHost +"/coin/list?";
        String body = doGet(url, new HashMap<>());
        return gson.fromJson(body, new TypeToken<CommonResponse<List<CoinConfigVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<WalletTransactionVo>> flowDepositRecords(TransactionFlowParam param) {
        String url = walletRestHost +"/deposit/low?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<WalletTransactionVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<PageInfo<WalletTransactionVo>> pageDepositRecords(TransactionPageParam param) {
        String url = walletRestHost +"/deposit/page?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<PageInfo<WalletTransactionVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<WalletTransactionVo>> flowTransactionRecords(TransactionFlowParam param) {
        String url = walletRestHost +"/transaction/low?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<WalletTransactionVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<PageInfo<WalletTransactionVo>> pageTransactionRecords(TransactionPageParam param) {
        String url = walletRestHost +"/transaction/page?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<PageInfo<WalletTransactionVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<WalletTransactionVo> findByHash(TransactionHashParam param) {
        String url = walletRestHost +"/transaction/record/hash?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<WalletTransactionVo>>() {
        }.getType());
    }

    @Override
    public CommonResponse<TxEntityVo> createWithdraw(WithdrawCreateParam param) {
        String url = walletRestHost +"/withdraw/create?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<TxEntityVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<?> cancelWithdraw(WithdrawCancelParam param) {
        String url = walletRestHost +"/withdraw/cancel?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, CommonResponse.class);
    }

    @Override
    public CommonResponse<TxEntityVo> getWithdrawRecord(WithdrawGetParam param) {
        String url = walletRestHost +"/withdraw/record?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<TxEntityVo>>() {
        }.getType());
    }

    @Override
    public CommonResponse<List<WalletTransactionVo>> flowWithdrawRecords(WithdrawFlowParam param) {
        String url = walletRestHost +"/withdraw/low?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<List<TxEntityVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<PageInfo<WalletTransactionVo>> pageWithdrawRecords(WithdrawPageParam param) {
        String url = walletRestHost +"/withdraw/page?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<PageInfo<TxEntityVo>>>() {
        }.getType());
    }

    @Override
    public CommonResponse<String> getWalletPublicKey() {
        String url = walletRestHost +"/service/config/wallet/publicKey";
        String body = doGet(url, new HashMap<>());
        return gson.fromJson(body, new TypeToken<CommonResponse<String>>() {
        }.getType());
    }

    @Override
    public CommonResponse<?> updSidPublicKey(UpdPublicKeyParam param) {
        String url = walletRestHost +"/service/config/upd/publicKey";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doPost(url, params, null);
        return gson.fromJson(body, new TypeToken<CommonResponse<String>>() {
        }.getType());
    }

    // Map<String, Object> map = MapUtil.beanToMap(param);
    private String doDelete(String url, Map<String, Object> params) {
        String str = ParamsSingUtil.signToString(params);
        HttpHeaders headers = buildHeader(str);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange( url + str,HttpMethod.DELETE, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url + str +"请求异常:" + response.getBody());
        }
        return response.getBody();
    }

    private String doPost(String url, Map<String, Object> params, Map<String, Object> body) {
        String str = ParamsSingUtil.signToString(params);
        HttpHeaders headers = buildHeader(str);
        HttpEntity entity = new HttpEntity(body, headers);
        ResponseEntity<String> response = restTemplate.exchange( url + str,HttpMethod.POST, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url + str +"请求异常:" + response.getBody());
        }
        return response.getBody();
    }


    private String doGet(String url, Map<String, Object> params) {
        String str = ParamsSingUtil.signToString(params);
        HttpHeaders headers = buildHeader(str);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange( url + str,HttpMethod.GET, entity,String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("url:"+ url + str +"请求异常:" + response.getBody());
        }
        return response.getBody();
    }

    private HttpHeaders buildHeader(String str) {
        if (StringUtils.isEmpty(publicKey) || StringUtils.isEmpty(privateKey)) {
            throw new RuntimeException("不安全，并不合法的请求");
        }
        HttpHeaders headers = new HttpHeaders();
        String sign = HmacUtils.hmacSha256Hex(privateKey, str);
        headers.add(X_SIGNATURE,sign);
        headers.add(X_ACCESS_KEY,publicKey);
        return headers;
    }
}
