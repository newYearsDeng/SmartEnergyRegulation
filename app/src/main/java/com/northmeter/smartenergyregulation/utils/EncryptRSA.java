package com.northmeter.smartenergyregulation.utils;

import android.util.Base64;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by dyd on 2019/5/14.
 */

public class EncryptRSA {
    private static final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4mD/tRfwopGlrOf+ujbyyvyboIL5XSsJjdSyIO9wbOkkfE2xuBPxfPG88fgJWCmZ18690BiNnWioojD7YRCQGmKRxwzuROzW8dfA6Nhx5md/fS6kjf69akF9SjiqEkxsgJhEu9JuvMy+qoW7hKyhcID8+3CmsiG9drX8o4HMURnJKVJm6W2GnR4Tz0BrofXx9KVuFzzW3Fs7NQzXm9JuY+ns2uBThULoS31HjdwbkCqPFDDjkjxBhNuD04dvwLZVN3loDXPt2KQN5W4DTE9P3I5WYCnAU8AK28CTkYSZSoEHMaaGFOyEc/6Sg/a1cbyy9zcTHfD0qQt6zhbQAQ0BAwIDAQAB";

    public static String encrypt(String encryptMessage){
        String byte2Base64="";
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            //生成RSA公钥和私钥，并Base64编码
//            KeyPair keyPair = EncryptRSA.getKeyPair();
//            String publicKeyStr = EncryptRSA.getPublicKey(keyPair);
//            String privateKeyStr = EncryptRSA.getPrivateKey(keyPair);
//            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
//            System.out.println("RSA私钥Base64编码:" + privateKeyStr);

            //=================客户端=================
            //将Base64编码后的公钥转换成PublicKey对象
            PublicKey publicKey = EncryptRSA.string2PublicKey(publicKeyString);
            //用公钥加密
            byte[] publicEncrypt = EncryptRSA.publicEncrypt(encryptMessage.getBytes(), publicKey);
            //加密后的内容Base64编码
            byte2Base64 = EncryptRSA.byte2Base64(publicEncrypt);
            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);


            //##############	网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容     #################

            //===================服务端================
//            //将Base64编码后的私钥转换成PrivateKey对象
//            PrivateKey privateKey = EncryptRAS.string2PrivateKey(privateKeyStr);
//            //加密后的内容Base64解码
//            byte[] base642Byte = EncryptRAS.base642Byte(byte2Base64);
//            //用私钥解密
//            byte[] privateDecrypt = EncryptRAS.privateDecrypt(base642Byte, privateKey);
//            //解密后的明文
//            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byte2Base64;
    }


    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    //获取私钥(Base64编码)
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    //将Base64编码后的公钥转换成PublicKey对象
    public static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

//     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//        keyFactory = KeyFactory.getInstance("RSA");     //适配Android P及以后版本，否则报错NoSuchAlgorithmException
//    } else {
//        keyFactory = KeyFactory.getInstance("RSA", "BC");
//    }

    //将Base64编码后的私钥转换成PrivateKey对象
    public static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //公钥加密
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//RSA
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //私钥解密
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException {
        return Base64.decode(base64Key,Base64.DEFAULT);
    }

}
