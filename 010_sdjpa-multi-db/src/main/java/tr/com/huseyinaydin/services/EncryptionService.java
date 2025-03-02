package tr.com.huseyinaydin.services;

public interface EncryptionService {
    String encrypt(String freeText);
    String decrypt(String encryptedText);
}