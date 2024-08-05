package valens.qt.v1.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import valens.qt.v1.models.Profile;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {

    private String token;
    private Profile user;
}
