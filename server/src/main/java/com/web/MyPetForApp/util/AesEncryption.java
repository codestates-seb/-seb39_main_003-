package com.web.MyPetForApp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

@Component
public class AesEncryption {

//    @Value("${encrypt.key}")
//    private String encryptionKey;
    private String encryptionKey = "4203923023920348" ;

    public String doEncrypt(String info) throws Exception {
        System.out.println("encryptionKey = " + encryptionKey);
        SecretKey keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");

        // iv 16비트로 랜덤 세팅
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        // 암호화 사전 작업
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        int blockSize = 128;
        byte[] dataBytes = info.getBytes(StandardCharsets.UTF_8);
        // 패딩 작업
        int infoTextLength = dataBytes.length;
        int fillChar = ((blockSize - (infoTextLength % blockSize)));
        infoTextLength += fillChar;

        byte[] bytesInfo = new byte[infoTextLength];
        Arrays.fill(bytesInfo, (byte) fillChar);
        System.arraycopy(dataBytes, 0, bytesInfo, 0, dataBytes.length);
        // 암호화
        byte[] cipherBytes = cipher.doFinal(bytesInfo);
        // iv를 앞쪽에 채우기
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(iv);
        outputStream.write(cipherBytes);
        // base64로 인코딩
        byte[] encryptedIvText = outputStream.toByteArray();
        return new String(Base64.getEncoder().encode(encryptedIvText), StandardCharsets.UTF_8);
    }

    public String doDecrypt(String encryptedIvText) throws Exception {
        // base64로 디코딩
        byte[] encryptedIvTextBytes = Base64.getDecoder().decode(encryptedIvText);
        // iv 추출
        int ivSize = 16;
        byte[] iv = new byte[ivSize];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
        // 인코딩된 곳 추출
        int encryptedSize = encryptedIvTextBytes.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);
        // 복호화
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKey keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] aesDecode = cipher.doFinal(encryptedBytes);
        // 언패딩
        byte[] origin = new byte[aesDecode.length - (aesDecode[aesDecode.length - 1])];
        System.arraycopy(aesDecode, 0, origin, 0, origin.length);

        return new String(origin, StandardCharsets.UTF_8);
    }
}
