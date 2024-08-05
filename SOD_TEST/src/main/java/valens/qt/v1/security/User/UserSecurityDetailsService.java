package valens.qt.v1.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import valens.qt.v1.models.Profile;
import valens.qt.v1.repositories.IProfileRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserSecurityDetailsService  implements UserDetailsService {
    private final IProfileRepository userRepository;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Profile> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent()){
            return new UserSecurityDetails(userOptional.get());
        }else{
            throw new UsernameNotFoundException("" + email + " was not found");
        }
    }

}
