package live.gatisoft.storerequestscontrol.common.repository;

import live.gatisoft.storerequestscontrol.common.schema.User;
import live.gatisoft.storerequestscontrol.security.services.BCryptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Test if a new user is persisted successfully")
    public void testUserPersistence() {
        User user = new User();
        userRepository.save(user);
        assertTrue(user.getId() > 0);
    }

    @Test
    @DisplayName("Test that a user is deleted correctly")
    public void testUserPersistenceAndDelete() {
        User user = new User();
        userRepository.save(user);
        assertTrue(user.getId() > 0);
        userRepository.delete(user);
        assertEquals(0, userRepository.count());
    }

    @Test
    @DisplayName("Test if a new user is persisted successfully and attributes to")
    public void testUserPersistenceOfAttributes() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setDocument("11144477735");
        user.setPasswordResetToken("resetToken");
        user.setPassword("1234");
        userRepository.save(user);

        User persisted = userRepository.findById(user.getId()).get();
        assertEquals(user.getDocument(), persisted.getDocument());
        assertEquals(user.getEmail(), persisted.getEmail());
        assertEquals(user.getPasswordResetToken(), persisted.getPasswordResetToken());
        assertTrue(BCryptService.passwordMatches("1234", user.getEncryptedPassword()));
    }

}