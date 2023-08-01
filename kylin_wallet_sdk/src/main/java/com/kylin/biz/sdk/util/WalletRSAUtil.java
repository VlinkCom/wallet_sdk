package com.kylin.biz.sdk.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class WalletRSAUtil {
    private static final String RSA_KEY_ALGORITHM = "RSA";

    // 数字签名签名/验证算法
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    // RSA密钥长度
    private static final int KEY_SIZE = 1024;

    /**
     * 初始化RSA密钥对
     * @return RSA密钥对
     * @throws Exception 抛出异常
     */
//    private static Map<String, String> initKey() throws Exception {
//        KeyPairGenerator keygen = KeyPairGenerator
//                .getInstance(RSA_KEY_ALGORITHM);
//        SecureRandom secrand = new SecureRandom();
//        secrand.setSeed("hahaha".getBytes());// 初始化随机产生器
//        keygen.initialize(KEY_SIZE, secrand); // 初始化密钥生成器
//        KeyPair keys = keygen.genKeyPair();
//        String pub_key = Base64.encodeBase64String(keys.getPublic().getEncoded());
//        String pri_key = Base64.encodeBase64String(keys.getPrivate().getEncoded());
//        Map<String, String> keyMap = new HashMap<String, String>();
//        keyMap.put(PUBLIC_KEY, pub_key);
//        keyMap.put(PRIVATE_KEY, pri_key);
//        System.out.println("公钥：" + pub_key);
//        System.out.println("私钥：" + pri_key);
//        return keyMap;
//    }

    /**
     * 数字签名
     * @param data 待签名数据
     * @param pri_key 私钥
     * @return 签名
     * @throws Exception 抛出异常
     */
    public static String sign(byte[] data, String pri_key) throws Exception {
        // 取得私钥
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key_bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 生成私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initSign(priKey);
        // 更新
        signature.update(data);

        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * RSA校验数字签名
     * @param data 数据
     * @param sign 签名
     * @param pub_key 公钥
     * @return 校验结果，成功为true，失败为false
     * @throws Exception 抛出异常
     */
    public static boolean verify(byte[] data, byte[] sign, String pub_key) throws Exception {
        // 转换公钥材料
        // 实例化密钥工厂
        byte[] pub_key_bytes = Base64.decodeBase64(pub_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 初始化公钥
        // 密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key_bytes);
        // 产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initVerify(pubKey);
        // 更新
        signature.update(data);
        // 验证
        return signature.verify(sign);
    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @param pub_key 公钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    private static byte[] encryptByPubKey(byte[] data, byte[] pub_key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @param pub_key 公钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    public static String encryptByPubKey(String data, String pub_key) throws Exception {
        // 私匙加密
        byte[] pub_key_bytes = Base64.decodeBase64(pub_key);
        byte[] enSign = encryptByPubKey(data.getBytes(), pub_key_bytes);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 私钥加密
     * @param data 待加密数据
     * @param pri_key 私钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    private static byte[] encryptByPriKey(byte[] data, byte[] pri_key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     * @param data 待加密数据
     * @param pri_key 私钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    public static String encryptByPriKey(String data, String pri_key) throws Exception {
        // 私匙加密
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        byte[] enSign = encryptByPriKey(data.getBytes(), pri_key_bytes);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 公钥解密
     * @param data 待解密数据
     * @param pub_key 公钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    private static byte[] decryptByPubKey(byte[] data, byte[] pub_key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     * @param data 待解密数据
     * @param pub_key 公钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    public static String decryptByPubKey(String data, String pub_key) throws Exception {
        // 公匙解密
        byte[] pub_key_bytes = Base64.decodeBase64(pub_key);
        byte[] design = decryptByPubKey(Base64.decodeBase64(data), pub_key_bytes);
        return new String(design);
    }

    /**
     * 私钥解密
     * @param data 待解密数据
     * @param pri_key 私钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    private static byte[] decryptByPriKey(byte[] data, byte[] pri_key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * @param data 待解密数据
     * @param pri_key 私钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    public static String decryptByPriKey(String data, String pri_key) throws Exception {
        // 私匙解密
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        byte[] design = decryptByPriKey(Base64.decodeBase64(data), pri_key_bytes);
        return new String(design);
    }

    /**
     * @param args
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        String pub = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh3Fpa6B/0kiZ/7LTt7tTUUwEO9ucKPCFPWQJpBqaSboc/IlLHibTN40uAY5j98cvad+df+/FwuaJKlfMBRB2tlFcU7sQ+NcOtqXtbUGVput+k1we6Szrb0bgGPFk5q/syeMZTL2USdpzP+5hd0DwgZRgkFzPChQXcvQlRF6lEtvgoWzHBqBCep+I0zj10uxbwI1bSsSgsu6MwvOvaAhmprXY3rUICQtcyW0y1aoLQFPbxruJVRcDvvL0M/4HO1D2gUOEuh2Bmgz3eGXIWwb+3Ej5AQwStDlUjJad8KJhlofRDRKBURxwLbOC9fPJlerIBIPJhIGikXZX/y6FzgY+PQIDAQAB";

        String pri = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCHcWlroH/SSJn/stO3u1NRTAQ725wo8IU9ZAmkGppJuhz8iUseJtM3jS4BjmP3xy9p351/78XC5okqV8wFEHa2UVxTuxD41w62pe1tQZWm636TXB7pLOtvRuAY8WTmr+zJ4xlMvZRJ2nM/7mF3QPCBlGCQXM8KFBdy9CVEXqUS2+ChbMcGoEJ6n4jTOPXS7FvAjVtKxKCy7ozC869oCGamtdjetQgJC1zJbTLVqgtAU9vGu4lVFwO+8vQz/gc7UPaBQ4S6HYGaDPd4ZchbBv7cSPkBDBK0OVSMlp3womGWh9ENEoFRHHAts4L188mV6sgEg8mEgaKRdlf/LoXOBj49AgMBAAECggEAMEEqsuqiv5OYMi5PGdWGkJ9Jnt3WHAvdHIsomCvWLlA9hYeQmLRyagdFjc5iNWPks460uWD+jzq9QbW3/VHpSXlhMH+kQ1OzbUVeep90Jt0d4nheluqnEqUosBpIvZuN/DuJxvp/+3P1vaA7X1ZmmXP4l1ZJeSjBVfY/1MT3gOkKQVxWAa90ibwNyAIF5z7xrxSJdALo4H5xh3cyjY6GTFs7GcN2ml5Iewhqm1Cbx7Hvd4JFMRuTu18aO/S6xUmyzR8c2RTMSLtr8m8xN+fS6T2S3TDz5oBvo20gaN3F5CmoR/hESrJGUE+mETBe2xtFjumAXUTKfwvShaKHh9z8QQKBgQDLd6fGDIskHPs/FSh8q2byxs0uwy7VIf/dAyjTQc4iNAyaeUbUi7uV7ZEX2kb3rZdHKuHlNpFpqkhDIAvDnNoU6x8aLzZWXRXLcp9vn3D1HizHRAwxEjZXdW0wVPZ4VzkLbJ5TuGO//oMgkpZqs7urUf2uMR4xWy6yrSUh3wy28QKBgQCqaaD1/ZucxLgr1PACdyTUD7WTqZ/k8YtRpUX5qtOAtSyfAvpZzL9e57Duz4oTGTEufA6n18X2dBxcSsn8Mumm7kLrJiLacCjzzOcUAkgsWwAodEx/TVfs/Ft1gJaZgZ+SgWL4/cr/g47THIsDeIs5kx9CwUphhjRU8uah6Lg0DQKBgDGmKRJyzSEq0UQ7bX2+dYiYakkW576qZT0RIE0sqZg/CzRGk7oZvtR+/ADOCrM1cMy08hp0jlcybh+zGivJgo3IMfa5vpCkdkUG21Dpny6xBkw5lqACya/dv58jnGERJ6JaBuh2/iBDgsvX+UxgP1M21qhC5BM3JNYE2iLBwuCBAoGAY4j02hl6NdP1GqzyTw5WNYLDdO8jlG69CEA3of90L/4ihPIRVjgwIvfDMYmJRWR2hIczbDesyinBkuqgFa2LKdSKw5xvyRSv5s7svZQjoby5V+et8sEzNRmlNfmYfSroEIOAUpyyYBHwb0CBtdJHx6Y2ISCTGGlx0Zhau4EZblUCgYAq/JPbZ7pGjf/4DFYM5al8g7lUK6yHadtYkzR8OclWTZNDdOD836EljusjKoeIzfC8BCefNzelLLIQiWfr76Fy18bTJI2Vbqz/i3hzL8woS0+NTjivsSw9Be8rBkrUzF1y+xcM8FKFrxSsbXBtu2te/Rkk7o95WiNDmLeTlyZ/dg==";

        String str1 = "汉兵已略地";
        String sign = WalletRSAUtil.sign(str1.getBytes(), pri);
        System.out.println("数字签名：\n" + sign);
        boolean vflag1 = WalletRSAUtil.verify(str1.getBytes(), Base64.decodeBase64(sign), pub);
        System.out.println("数字签名验证结果1：\n" + vflag1);
    }
}
