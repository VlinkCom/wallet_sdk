package com.kylin.biz.sdk.util;

import org.apache.commons.codec.binary.Base64;
import com.google.common.base.Joiner;
import org.apache.commons.codec.binary.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class PUtils {
    private static final String STR_BEFORE_ENCRYPT = "2";
    private static final char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public PUtils() {
    }

    public static String encrypt(String source) throws UnsupportedEncodingException {
        return encrypt(source, 1);
    }

    public static String encrypt(String source, int prefixLength) throws UnsupportedEncodingException {
        String seed = source.substring(0, source.length() - 1) + "2" + source.substring(source.length() - 1, source.length());
        Base64 encoder = new Base64();
        String afterEncryptChar = getRandomChars(prefixLength);
        return afterEncryptChar + encoder.encodeToString(seed.getBytes("UTF-8"));
    }

    public static String decrypt(String source) throws UnsupportedEncodingException {
        return decrypt(source, 1);
    }

    public static String decrypt(String source, int prefixLength) throws UnsupportedEncodingException {
        Base64 encoder = new Base64();
        String a = source.substring(prefixLength, source.length());
        String decoded = new String(encoder.decode(a), "UTF-8");
        return decoded.substring(0, decoded.length() - 2) + decoded.substring(decoded.length() - 1, decoded.length());
    }

    public static String encryptMulti(String... strings) throws UnsupportedEncodingException {
        String[] var1 = strings;
        int var2 = strings.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String src = var1[var3];
            if (src.contains("|")) {
                throw new IllegalArgumentException("do not support | character encrpyting");
            }
        }

        String src = Joiner.on("|").join(strings);
        return encrypt(src);
    }

    public static boolean encryptedMatched(String src, String target) {
        return StringUtils.equals(src.substring(1), target.substring(1));
    }

    private static String getRandomChars(int cnt) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < cnt; ++i) {
            sb.append(chars[random.nextInt(chars.length)]);
        }

        return sb.toString();
    }
}
