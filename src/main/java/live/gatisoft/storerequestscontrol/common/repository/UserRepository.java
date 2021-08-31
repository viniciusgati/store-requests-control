package live.gatisoft.storerequestscontrol.common.repository;

import live.gatisoft.storerequestscontrol.common.schema.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
