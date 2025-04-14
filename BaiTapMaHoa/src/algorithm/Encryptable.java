package algorithm;

public interface Encryptable {
    String encrypt(String input) throws Exception;
    String decrypt(String input) throws Exception;
}
