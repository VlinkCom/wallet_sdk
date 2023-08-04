package com.weibi.wallet.rest.sdk.proxy;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.weibi.wallet.rest.sdk.params.*;
import com.weibi.wallet.rest.sdk.resp.CommonResponse;
import com.weibi.wallet.rest.sdk.util.MapUtil;
import com.weibi.wallet.rest.sdk.util.ParamsSingUtil;
import com.weibi.wallet.rest.sdk.util.WalletRSAUtil;
import com.weibi.wallet.rest.sdk.vo.*;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;


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
    public CommonResponse<Boolean> addressVerify(AddressVerifyParam param) {
        String url = walletRestHost +"/address/verify?";
        Map<String, Object> params = MapUtil.beanToMap(param);
        String body = doGet(url, params);
        return gson.fromJson(body, new TypeToken<CommonResponse<Boolean>>() {
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

    public static void main(String[] args) throws Exception {
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI6+Q+M3FrqKFiHQu5M56KZ2j0QvNd3iephm6rg3Ib+UIcu2f5wTFZnF7k8qWbgnWcX0bRS0ozgwGYPeKFCpdCjqQ0XRLsnhP87KZ6eARU0Xj7lEZbr0mryZsIPv8iVH/GwNhe/vqG/3Y8QbocBN0f/CM6F0JNg72oyZtQsHli5wIDAQAB";

        String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIjr5D4zcWuooWIdC7kznopnaPRC813eJ6mGbquDchv5Qhy7Z/nBMVmcXuTypZuCdZxfRtFLSjODAZg94oUKl0KOpDRdEuyeE/zspnp4BFTRePuURluvSavJmwg+/yJUf8bA2F7++ob/djxBuhwE3R/8IzoXQk2DvajJm1CweWLnAgMBAAECgYAs+6rJ0KbTotwaWsaOOuo8OEsf7Jr93M2VAWh8irvDevmAbQV05UasVRZMC3fjBlJTZG3ktuKS19h/Rt2Tre4RYK2n48SwgGEtU9hkJ+DlJ+g2cMQTURfIJy860lSnaJc+jibh/PIpWVlJLyzqeNLlvxudz4zBdGDkoGGIGWHBMQJBANV0x8Mw4xDp4MA7Q4cIEn1fnmwnycl3GZQAr4ieCefVGobqHXyVTOlGDjdLPVly9D54Sd9sT1ZKCfi96umVeDkCQQCkNhBaz/kzu6Uk5VjS/Jp+dUq9V7MXl5tFFDSSksY0EDXPjBFntn2LJBTgWpDv3DAFayZ+Q7lelpOQmTEYFnQfAkEAoCHMnrz1C0I5Ll0HSqyemll6Uq8CrVXg5WwiQz40NixjiyTk3ApxOWspzQdvzcP0QU0iNi9d0WEX2/g12+ga2QJAdD2xJhfCmFRketG/JtuZoZr15UKHjFPNngDHllo/4+r1rI3CZGBSToSkIoz5vFFpzOwku4zFU6fTnbBTiHGckQJBAJrdKbj7M5u1xstkcYt3LZne9eOiBUywXKNwqJE4Z29QUpLrXAT6w14ewmQVXSYInv97GE6rcDmgSK+ikNUM8x4=";


        String walletPub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxaBlputGXja33arkkHS9tpLtfOHxukbMK/WEI9Kiku5VjQRhaG86Td4y1XWoiRCowXjbTu3zMjzR7anu7wMfGk4e1JN1mDLr87VrCDvzxvuYa6MNcOVQSQ45gBNXBD5maZlo1YiwO+bmWLruOX4VlsUL5sGcrYDlVhY6JfuVUkwIDAQAB";


        String walletPri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALFoGWm60ZeNrfdquSQdL22ku184fG6Rswr9YQj0qKS7lWNBGFobzpN3jLVdaiJEKjBeNtO7fMyPNHtqe7vAx8aTh7Uk3WYMuvztWsIO/PG+5hrow1w5VBJDjmAE1cEPmZpmWjViLA75uZYuu45fhWWxQvmwZytgOVWFjol+5VSTAgMBAAECgYEAmkwYDkVWFaI8NHy2GhroLUyhSuguEVzXhC9sPgXMx7n+7BypuXWF7eyEjRl6PeNbbkYDGZRvrtq+oriydVHubehMNZ4rnRKT6A4oSrEr+yiUkSu/7ZVtDJLp49h4bFZt3y939ThC/H1gkMbd2xrk2uaYukdEc4nLDa+81N3xqoECQQD6WoxeP0XtHW69wyL9cU4Sk5lVgTiKAJUC1fSTTm4YK8Gyh3ghL3jCZF2SGhv/Q5SpKtwWybLxWpgGP/Dssy0pAkEAtWhhiMbkKiym7qpfd1cywtn9WiAABLgxC7sVhlAUDv03muRJs9p30WCVUDbMV0ZahQGDB6yDLxnY3QAtKhPvWwJAbkvANbF4lCt4Y3/6BWCKveJrFmGU0C/LdnFejBtso5d7gbTvNuecM0BWfQylswNKFnF8f0mjXXPFMFOxSAb0aQJAUkHbgB7XlPwcUbp1gXLLtKkOBZDfEUTIEI6rivTCs61ESnrbpK8ah5lo+y9t5uEi6I6v8IncDj6FJGkREbRqYwJBAJbPwdNOmd2jYKc2/96uCwdbQLL4iZQBdDhVkQdUqlk6X3EK8lpgt7dZfDi0ASZbt3q1TYtVGj+s/YPnETloNXs=";



        WalletRestProxyImpl walletRestProxy = new WalletRestProxyImpl(
                "http://localhost:18081",
                pub, pri);


        // http://localhost:18081
        // http://wallet.fkw2.xyz/rest
        // https://wallet.druex.net/rest

//        AddressParam build = AddressParam.builder()
//                .coin("TRX")
//                .size(1)
//                .build();
//        CommonResponse<List<String>> unUsedAddress = walletRestProxy.getUnUsedAddress(build);
//        System.out.println(new Gson().toJson(unUsedAddress));


//        WithdrawCreateParam build1 = WithdrawCreateParam.builder()
//                .bizId("1024")
//                .userId("1234")
//                .txCoin("TRX")
//                .chain("TRX")
//                .txToWallet("xsddf")
//                .txFromWallet("xxx")
//                .txFee(BigDecimal.ONE)
//                .txAmount(BigDecimal.TEN)
//                .build();
//        CommonResponse<TxEntityVo> withdraw = walletRestProxy.createWithdraw(build1);
//        System.out.println("xxxx");
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
        HashMap<String, Object> headerMap = new HashMap<>();
        String body = doPost(url, headerMap, param);
        return gson.fromJson(body, new TypeToken<CommonResponse<TxEntityVo>>() {
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

    private String doPost(String url, Map<String, Object> params, Object body) {
        String str = ParamsSingUtil.signToString(params);
        HttpHeaders headers = buildHeader(str);
        headers.add("Content-Type", "application/json;charset:utf-8;");
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
        String sign = null;
        try {
            sign = WalletRSAUtil.sign(str.getBytes(), privateKey);
        } catch (Exception e) {
            throw new RuntimeException("sign error");
        }
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        headers.add(X_SIGNATURE,sign);
        headers.add(X_ACCESS_KEY,publicKey);
        return headers;
    }
}
