package valens.qt.v1.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valens.qt.v1.models.Profile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findUserByEmail(String email);
    boolean existsByEmail(String email);
    Profile findByEmail(String email);

}
