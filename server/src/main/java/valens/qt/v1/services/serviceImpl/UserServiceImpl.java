package valens.qt.v1.services.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valens.qt.v1.dtos.requests.CreateUserDTO;
import valens.qt.v1.exceptions.BadRequestException;
import valens.qt.v1.exceptions.NotFoundException;
import valens.qt.v1.exceptions.UnauthorizedException;
import valens.qt.v1.models.Profile;
import valens.qt.v1.models.User;
import valens.qt.v1.repositories.IProfileRepository;
import valens.qt.v1.repositories.IUserRepository;
import valens.qt.v1.security.User.UserSecurityDetails;
import valens.qt.v1.services.IRoleService;
import valens.qt.v1.services.IUserService;
import valens.qt.v1.utils.ExceptionsUtils;
import valens.qt.v1.utils.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of IUserService providing user management functionality.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IProfileRepository profileRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;


    /**
     * Retrieves all users from the repository.
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return The User object if found, otherwise throws NotFoundException.
     */
    @Override
    public User getUserById(UUID id) {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The user with the provided id doesn't exist"));
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
    @Override
    public Profile getProfileById(UUID id) {
        try {
            return profileRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The user with the provided id doesn't exist"));
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }

    /**
     * Creates a new admin user based on the provided CreateUserDTO.
     * @param dto The CreateUserDTO containing admin user details.
     * @return The created User object if successful, otherwise handles exceptions and returns null.
     */
    @Override
    @Transactional
    public User createAdmin(CreateUserDTO dto) {
        try {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new BadRequestException("The user with the provided email already exists");
            }

            User user = Mapper.getUserFromDTO(dto);
            profile = new Profile(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), dto.getUserName());
            role = roleService.getByRoleName("ADMIN");
            profile.setRoles(new HashSet<>(List.of(role)));
            profile = profileRepository.save(profile);
            return userRepository.save(user);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
    /**
     * Creates a new admin user based on the provided CreateUserDTO.
     * @param dto The CreateUserDTO containing admin user details.
     * @return The created User object if successful, otherwise handles exceptions and returns null.
     */
    @Override
    @Transactional
    public User createUser(CreateUserDTO dto) {
        try {
            if (userRepository.existsByEmail(dto.getEmail())) throw new BadRequestException("The user with the provided email already exists");

            User user = Mapper.getUserFromDTO(dto);
            profile = new Profile(dto.getEmail(), passwordEncoder.encode(dto.getPassword()),dto.getUserName());
            role = roleService.getByRoleName("USER");
            profile.setRoles(new HashSet<>(List.of(role)));
            profile = profileRepository.save(profile);
//            user = userRepository.save(user);
            user.setProfile(profile);
            return userRepository.save(user);
        } catch (Exception exception) {
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
    /**
     * Updates an existing user by their ID.
     * @param id The ID of the user to update.
     * @param user The updated User object.
     * @return The updated User object if successful, otherwise null.
     */
    @Override
    public User updateUser(UUID id, CreateUserDTO user) {
        try {
            User currentUser = this.getById(id);
            currentUser.setEmail(user.getEmail());
            profile = profileRepository.findByEmail(currentUser.getEmail());
            profile.setEmail(currentUser.getEmail());
            profile = profileRepository.save(profile);
            currentUser.setProfile(profile);
            return this.userRepository.save(currentUser);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
    @Override
    public User getById(UUID id){
        try{
            return this.userRepository.findById(id).orElseThrow(()-> new NotFoundException("The user with provided id is not found"));
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
    /**
     * Checks if a user is currently logged in.
     * @return True if a user is logged in, false otherwise.
     */
    @Override
    public boolean isUserLoggedIn() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication != null && !authentication.getPrincipal().equals("anonymousUser");
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return false;
        }
    }

    /**
     * Retrieves the currently logged-in user.
     * @return The User object representing the logged-in user.
     * @throws UnauthorizedException If the user is not authenticated.
     */
    @Override
    public Profile getLoggedInUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !(authentication.getPrincipal() instanceof String)) {
                UserSecurityDetails userSecurityDetails = (UserSecurityDetails) authentication.getPrincipal();
                return profileRepository.findUserByEmail(userSecurityDetails.getUsername())
                        .orElseThrow(() -> new UnauthorizedException("You are not authorized! Please log in"));
            } else {
                throw new UnauthorizedException("You are not authorized! Please log in");
            }
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
    /**
     * Deletes a user by their ID.
     * @param id The ID of the user to delete.
     */
    @Override
    public void deleteUser(UUID id) {
        try{
            userRepository.deleteById(id);
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
        }
    }
}
