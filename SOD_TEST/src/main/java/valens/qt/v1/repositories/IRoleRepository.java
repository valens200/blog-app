package valens.qt.v1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valens.qt.v1.models.Role;

import java.util.Optional;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String  name);
    boolean existsByRoleName(String name);
}
