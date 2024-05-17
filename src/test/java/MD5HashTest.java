import org.example.Task1.MD5Hash;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MD5HashTest {

    @Test
    public void testCalculateMD5() throws NoSuchAlgorithmException {
        assertEquals("1f3870be274f6c49b3e31a0c6728957f", MD5Hash.calculateMD5("apple"));
        assertEquals("72b302bf297a228a75730123efef7c41", MD5Hash.calculateMD5("banana"));

    }

}
