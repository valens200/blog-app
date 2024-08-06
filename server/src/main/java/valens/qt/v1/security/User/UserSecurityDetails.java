package valens.qt.v1.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import valens.qt.v1.models.Profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserSecurityDetails implements UserDetails {
    public String username;
    public String password;
    public List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public UserSecurityDetails(Profile user){
        this.username = user.getEmail();
        this.password = user.getPassword();
        user.getRoles().forEach(role -> {
            UserAuthority userAuthority = new UserAuthority(user.getId() ,  role.getRoleName());
            grantedAuthorities.add(userAuthority);
        });
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
