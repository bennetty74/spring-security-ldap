package com.bennetty74.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */
public class JasyptUtil {

    /**
     * Encrypt a row string into a encrypted str
     * This method usually used to encrypt username and password in MySQL connection properties
     * @param raw a row string
     * @return an encrypted str
     */
    public static String encrypt(String raw) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("JKL");
        return encryptor.encrypt(raw);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("bennetty74"));
        System.out.println(encrypt("Bennetty74@gmail.com"));
    }
}
