import org.example.Task2.TripleDES;
import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;

public class TripleDESTest {

    @Test
    public void testEncryptionDecryption() throws Exception {
        String plaintext = "Hello, world!";
        SecretKey secretKey = TripleDES.generateKey();

        byte[] encrypted = TripleDES.encrypt(plaintext, secretKey);
        String decrypted = TripleDES.decrypt(encrypted, secretKey);

        assertEquals(plaintext, decrypted);
    }

    @Test
    public void testEncryptionDecryptionWithEmptyString() throws Exception {
        String plaintext = "";
        SecretKey secretKey = TripleDES.generateKey();

        byte[] encrypted = TripleDES.encrypt(plaintext, secretKey);
        String decrypted = TripleDES.decrypt(encrypted, secretKey);

        assertEquals(plaintext, decrypted);
    }

    @Test
    public void testEncryptionDecryptionWithNullKey() {
        String plaintext = "Hello, world!";

        assertThrows(InvalidKeyException.class, () -> {
            TripleDES.encrypt(plaintext, null);
        });

        assertThrows(InvalidKeyException.class, () -> {
            TripleDES.decrypt(new byte[16], null);
        });
    }
}
