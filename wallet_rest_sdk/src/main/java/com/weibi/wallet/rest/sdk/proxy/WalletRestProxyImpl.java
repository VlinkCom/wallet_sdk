package com.weibi.wallet.rest.sdk.proxy;



import com.google.gson.Gson;
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

    public static void main(String[] args) {
        String pub = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh3Fpa6B/0kiZ/7LTt7tTUUwEO9ucKPCFPWQJpBqaSboc/IlLHibTN40uAY5j98cvad+df+/FwuaJKlfMBRB2tlFcU7sQ+NcOtqXtbUGVput+k1we6Szrb0bgGPFk5q/syeMZTL2USdpzP+5hd0DwgZRgkFzPChQXcvQlRF6lEtvgoWzHBqBCep+I0zj10uxbwI1bSsSgsu6MwvOvaAhmprXY3rUICQtcyW0y1aoLQFPbxruJVRcDvvL0M/4HO1D2gUOEuh2Bmgz3eGXIWwb+3Ej5AQwStDlUjJad8KJhlofRDRKBURxwLbOC9fPJlerIBIPJhIGikXZX/y6FzgY+PQIDAQAB";

        String pri = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCaJZiEINrdUUmbO2TXorGAVw3NK/SO33N8NZvRvN95XwxVGLK8ScHLLJEFay7ZX6VyusJ8znhT2b4V2puUEify2yFTaVE7hlucmbZMkcOWqTiF7PTFFyrg05PPjYiLeJk3vSfNUPoHZgF5GCedEVGccEFC5NBVRUpiagAmlsnvulftBZtQwXi+y5cfudj6ERgsv7iP6IwykxBlWRnfpPkjqiDx9PV+wj5GMGbmA+f93gApdaC1iXHaAN9OBGD64u5HxWIoQgzPVLB1sHC0RhLexyFtFhc3NQ0z/8SOXGM34byMYJUZVoH25n1o2dnrUX1cMY+Ug8Q4KMAUb0xrqauDAgMBAAECggEAZtWX9uWYPTeCfF3WO4kdE3qACZU+VKY7e+yjo/t1tpmzN33/l185OtrjcJL9sSR+0phHMU3otj+38Qpi6hhmsDyB8gx7ldNfgIyHMnO34OXJgIcBEA8RmuVrYXaRVkJBLQ8y42UXOCJT7z4vBzjLkirNTtwpJAnSwu7mZNgJpbRI5A4wW7ZN7CcTqFzbveA9rL5n7ma85UBjJA8ZXG4PdKySOav2nEM2H5qwQ+c6/C2QBT83m+Yb0GjQrzcv0utQwyqz6JqySMw3BEQJyZvdBvteuny+vIhG6qQQYk6IDQr1RROZB+QuEZlfF5xDE0py7xx3XWUaFdLB8QYcWmlv6QKBgQDKoOgNN6MdaWBvsaXJnoKEUd/J3aBCQEr8kQKRG4pymrK2ZHi3sJSjVFTwUSuWPDGlGJ9la/dT0fTep0AtkmSAsrbINvpC8hqxqWaxTk9FsMslMqjSGhwKMFey9U3ud66Z0H+XLuWb+6wCO+b3+Pl0eLKbez5ztzTlIEfgCDQ1jQKBgQDCv5rosqoSjKtVzq0d/1p9yVHubB7BYhHI4/zkkX5jdRloKYu4oYJPz3zal66SWBTNNFYQyHN9Z9GJZuo+Fm/NqMHm9iGE1UEpzWx2pex1EGCmxVRfNAtpwgTDld2mY4QYYCr9mkNVI82b/STZCZkde/6/q8wSVR1h7P4eKhP5TwKBgQDFKlDiG5MymUjnJjsltoFVc72IVYkNLjZJEGko/V14KqNe6uC0DLR1QSftN25sH2JHPXBMZgQhoZjd1mySa3Iz8LH+m9Bkt+PVn/p26LYmH+Nr7KFs308NcwDC+AK+2eIhdUnRuCZf40H6YoNaIv/In3Fb7U5Vlp13njOSASmcOQKBgQCbsim7vhMgT1wvdtOKtvr2s3wQPFp+StRjInqtlfBQvW65ivWMf3s6KWztKfVERK0kvaHNOySx7s1jp2bAipmJ91BGQSLdGs1FCVmcpdycXqKW0g45KtexDo9WertA6jDSeBgGtkz8hWo9Oq454mWKX/Zz8HpvcaLTBnDHVuavGwKBgQCayQds0BKGsC5wPVwmNjJj7P9SCwGnWht//P9HtOJj5wqxB0VULyw8kvwuhzyp/0yNu6IsdiaZ+69GsGkNyYGjmsGi7+20DlZLWtOy+i5YaIteL55iARsxQfWH38yRYHtK4ZRrFXWNpQlkT4/r6huDVIgo07bj30ETQimns4OxFQ==";

        WalletRestProxyImpl walletRestProxy = new WalletRestProxyImpl(
                "http://localhost:18081",
                pub, pri);

        WithdrawCreateParam build = WithdrawCreateParam.builder()
                .bizId("xxxxxxxxxxxxxxxxx")
                .txCoin("TRX")
                .chain("TRX")
                .userId("1234")
                .txFromWallet("TUh2HLpFPgGLtfnRPKUhwC4uBuZvzisYeN")
                .txToWallet("TT6CG45wSLo4qQahj6NeuRFvvksi2zvRgt")
                .txAmount(BigDecimal.TEN)
                .txFee(BigDecimal.ONE)
                .build();
        walletRestProxy.createWithdraw(build);
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
        String sign = null;
        try {
            sign = WalletRSAUtil.sign(str.getBytes(), privateKey);
        } catch (Exception e) {
            throw new RuntimeException("sign error");
        }

        headers.add(X_SIGNATURE,sign);
        headers.add(X_ACCESS_KEY,publicKey);
        return headers;
    }
}
