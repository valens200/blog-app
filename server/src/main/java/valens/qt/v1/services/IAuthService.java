package valens.qt.v1.services;
import valens.qt.v1.dtos.requests.LoginDTO;
import valens.qt.v1.dtos.response.LoginResponseDTO;

public interface IAuthService {

    public LoginResponseDTO login(LoginDTO dto);

}
