package com.hackathon.mind_mentor.components;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
public class EncryptionUtil {

    @Value("#{encryptionKey}") //calls the encryption key method in ApplicationConfig
    private String key;

    @Value("#{initVectorKey}") //calls the init Vector key method in ApplicationConfig
    private String initVector;
    private String algo = "AES/CBC/PKCS5PADDING";

    public String encrypt(String message){ //encryption method
        try{
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8)); //creates an IvParameterSpec object using the bytes in initVector as the Intialisation Vector

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"); //constructs a secret key from the given byte array key

            Cipher cipher = Cipher.getInstance(algo); //gets the cipher method
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); //initiates cipher with key and init vector

            byte[] encrypted = cipher.doFinal(message.getBytes()); //this method returns a byte array containing the encrypted or decrypted message
            return Base64.encodeBase64String(encrypted);

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
