package valens.qt.v1.services;
import org.springframework.http.ResponseEntity;
import valens.qt.v1.dtos.requests.LoginDTO;
import valens.qt.v1.dtos.requests.ResetPasswordDTO;
import valens.qt.v1.dtos.response.LoginResponseDTO;
import valens.qt.v1.payload.ApiResponse;

public interface AuthenticationService {
    public LoginResponseDTO login(LoginDTO dto);
    ResponseEntity<ApiResponse> verifyAccount(String email, String code);

    ResponseEntity<ApiResponse> verifyResetCode(String email, String code);
    ResponseEntity<ApiResponse> resendVerificationCode(String email);
    ResponseEntity<ApiResponse> resetPassword(ResetPasswordDTO dto);
    ResponseEntity<ApiResponse> initiatePasswordReset(String email);
}
