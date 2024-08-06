package valens.qt.v1.controllers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import valens.qt.v1.dtos.requests.CreateUserDTO;
import valens.qt.v1.payload.ApiResponse;
import valens.qt.v1.services.IUserService;
import valens.qt.v1.utils.ExceptionsUtils;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Controller class for handling user-related operations.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController extends Controller {

    private final IUserService userService;

    /**
     * Endpoint for creating an admin user.
     * @param userDTO The CreateUserDTO object containing admin user details.
     * @return ResponseEntity containing ApiResponse indicating success or failure of admin creation.
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody CreateUserDTO userDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "User created successfully", userService.createUser(userDTO)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception); // Handle exceptions using utility class
        }
    }
    /**
     * Endpoint for creating an admin user.
     * @param userDTO The CreateUserDTO object containing admin user details.
     * @return ResponseEntity containing ApiResponse indicating success or failure of admin creation.
     */
    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponse> createAdmin(@Valid @RequestBody CreateUserDTO userDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Admin created successfully", userService.createAdmin(userDTO)));
        } catch (Exception exception) {
            return ExceptionsUtils.handleControllerExceptions(exception); // Handle exceptions using utility class
        }
    }
    /**
     * Retrieves all users from the repository.
     */
    @GetMapping("all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try{
            return ResponseEntity.ok(new ApiResponse(true,"All users were retrieved successfully"));
        }catch (Exception exception){
            return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }

    /**
     * Retrieves a user by their ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(new ApiResponse(true,"A user was retrieved successfully", this.userService.getUserById(id)));
        } catch (Exception exception) {
           return ExceptionsUtils.handleControllerExceptions(exception);
        }
    }
    /**
     * Updates an existing user by their ID.
     */
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid CreateUserDTO user) {
        try {
            return ResponseEntity.ok(new ApiResponse(true,"The user updated successfully", userService.updateUser(id,user)));
        }catch (Exception exception){
            ExceptionsUtils.handleServiceExceptions(exception);
            return null;
        }
    }
}
