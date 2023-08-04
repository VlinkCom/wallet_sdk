package com.weibi.wallet.rest.sdk.util;

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
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxaBlputGXja33arkkHS9tpLtfOHxukbMK/WEI9Kiku5VjQRhaG86Td4y1XWoiRCowXjbTu3zMjzR7anu7wMfGk4e1JN1mDLr87VrCDvzxvuYa6MNcOVQSQ45gBNXBD5maZlo1YiwO+bmWLruOX4VlsUL5sGcrYDlVhY6JfuVUkwIDAQAB";

        String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALFoGWm60ZeNrfdquSQdL22ku184fG6Rswr9YQj0qKS7lWNBGFobzpN3jLVdaiJEKjBeNtO7fMyPNHtqe7vAx8aTh7Uk3WYMuvztWsIO/PG+5hrow1w5VBJDjmAE1cEPmZpmWjViLA75uZYuu45fhWWxQvmwZytgOVWFjol+5VSTAgMBAAECgYEAmkwYDkVWFaI8NHy2GhroLUyhSuguEVzXhC9sPgXMx7n+7BypuXWF7eyEjRl6PeNbbkYDGZRvrtq+oriydVHubehMNZ4rnRKT6A4oSrEr+yiUkSu/7ZVtDJLp49h4bFZt3y939ThC/H1gkMbd2xrk2uaYukdEc4nLDa+81N3xqoECQQD6WoxeP0XtHW69wyL9cU4Sk5lVgTiKAJUC1fSTTm4YK8Gyh3ghL3jCZF2SGhv/Q5SpKtwWybLxWpgGP/Dssy0pAkEAtWhhiMbkKiym7qpfd1cywtn9WiAABLgxC7sVhlAUDv03muRJs9p30WCVUDbMV0ZahQGDB6yDLxnY3QAtKhPvWwJAbkvANbF4lCt4Y3/6BWCKveJrFmGU0C/LdnFejBtso5d7gbTvNuecM0BWfQylswNKFnF8f0mjXXPFMFOxSAb0aQJAUkHbgB7XlPwcUbp1gXLLtKkOBZDfEUTIEI6rivTCs61ESnrbpK8ah5lo+y9t5uEi6I6v8IncDj6FJGkREbRqYwJBAJbPwdNOmd2jYKc2/96uCwdbQLL4iZQBdDhVkQdUqlk6X3EK8lpgt7dZfDi0ASZbt3q1TYtVGj+s/YPnETloNXs=";

        String str1 = "汉兵已略地";
        String sign = WalletRSAUtil.sign(str1.getBytes(), pri);
        System.out.println("数字签名：\n" + sign);
        boolean vflag1 = WalletRSAUtil.verify(str1.getBytes(), Base64.decodeBase64(sign), pub);
        System.out.println("数字签名验证结果1：\n" + vflag1);
    }
}
