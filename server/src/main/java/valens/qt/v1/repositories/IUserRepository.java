package valens.qt.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valens.qt.v1.models.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    public boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
