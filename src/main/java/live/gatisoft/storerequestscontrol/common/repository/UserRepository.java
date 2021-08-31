package live.gatisoft.storerequestscontrol.common.repository;

import live.gatisoft.storerequestscontrol.common.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);
}
