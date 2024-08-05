package valens.qt.v1.services;
import org.springframework.transaction.annotation.Transactional;
import valens.qt.v1.dtos.requests.CreateUserDTO;
import valens.qt.v1.models.Profile;
import valens.qt.v1.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(UUID id);

    Profile getProfileById(UUID id);

    User createAdmin(CreateUserDTO user);

    @Transactional
    User createUser(CreateUserDTO dto);

    User updateUser(UUID id, CreateUserDTO user);
    void deleteUser(UUID id);

    User getById(UUID id);

    public  boolean isUserLoggedIn();
    public Profile getLoggedInUser();
}
