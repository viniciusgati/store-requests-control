package live.gatisoft.storerequestscontrol.common.schema;

import live.gatisoft.storerequestscontrol.security.services.BCryptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new User();

    @Test
    @DisplayName("User should set the id")
    public void getAndSetId() {
        Long id = 1234L;
        user.setId(id);
        assertEquals(user.getId(), id);
    }

    @Test
    @DisplayName("User should set the email")
    public void getAndSetEmail() {
        String email = "email@test.com";
        user.setEmail(email);
        assertEquals(user.getEmail(), email);
    }

    @Test
    @DisplayName("User should set the document")
    public void getAndSetDocument() {
        String sample = "11144477735";
        user.setDocument(sample);
        assertEquals(user.getDocument(), sample);
    }

    @Test
    @DisplayName("User should set the passwordResetToken")
    public void getAndSetPasswordResetToken() {
        String sample = "1823809127839021";
        user.setPasswordResetToken(sample);
        assertEquals(user.getPasswordResetToken(), sample);
    }

    @Test
    @DisplayName("User should set the encryptedPassword")
    public void getAndSetEncryptedPassword() {
        String sample = "8asd89a7d978asd789a6s7";
        user.setPassword(sample);
        assertNotEquals(user.getEncryptedPassword(), sample);
        assertFalse(user.getEncryptedPassword().isBlank());
        assertTrue(BCryptService.passwordMatches(sample, user.getEncryptedPassword()));
    }

}