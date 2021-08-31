package live.gatisoft.storerequestscontrol.common.repository;

import live.gatisoft.storerequestscontrol.common.schema.User;
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
    @DisplayName("Test if a new user is persisted successfully")
    public void testUserPersistenceAndDelte() {
        User user = new User();
        userRepository.save(user);
        assertTrue(user.getId() > 0);
        userRepository.delete(user);
        assertTrue(userRepository.count() > 0);
    }



}