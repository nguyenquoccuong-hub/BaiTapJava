package algorithm;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSAAlgorithm implements Encryptable {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSAAlgorithm(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public RSAAlgorithm() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    @Override
    public String decrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(decrypted, "UTF-8");
    }
}
