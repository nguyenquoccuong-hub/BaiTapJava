package algorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESAlgorithm implements Encryptable {

    private static final String key = "1234567890123456"; // 16 bytes = 128-bit key

    @Override
    public String encrypt(String input) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Sử dụng ECB
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted); // Trả về chuỗi Base64
    }

    @Override
    public String decrypt(String input) throws Exception {
        try {
            // Loại bỏ khoảng trắng/thừa
            input = input.trim().replaceAll("\\s+", "");

            // Giải mã Base64
            byte[] decoded = Base64.getDecoder().decode(input);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (IllegalArgumentException | javax.crypto.BadPaddingException | javax.crypto.IllegalBlockSizeException ex) {
            throw new IllegalArgumentException("Chuỗi mã hoá không đúng hoặc sai khoá.");
        }
    }



}
