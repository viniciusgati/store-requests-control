package live.gatisoft.storerequestscontrol.security.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class BCryptService {

    public static String encodeStringForPassword(String plainPassword) {
        BCryptPasswordEncoder encoder = getPasswordEncoder();
        return encoder.encode(plainPassword);
    }

    public static boolean passwordMatches(String rawPassword, String encryptedPassword) {
        return getPasswordEncoder().matches(rawPassword, encryptedPassword);
    }

    private static BCryptPasswordEncoder getPasswordEncoder() {
        int strength = 10;
        return new BCryptPasswordEncoder(strength, new SecureRandom());
    }

}
