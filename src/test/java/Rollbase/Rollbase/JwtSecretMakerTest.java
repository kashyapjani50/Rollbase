package Rollbase.Rollbase;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

public class JwtSecretMakerTest {

    @Test
    public void generateSecretKey() {
        // Generate a secret key for HS512 algorithm
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        
        System.out.println("Generated Secret Key: [%s]" + encodedKey);
    }
}
