package valens.qt.v1.services.serviceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import valens.qt.v1.dtos.response.LoginResponseDTO;
import valens.qt.v1.security.User.UserSecurityDetails;
import valens.qt.v1.models.Comment;
import valens.qt.v1.models.Post;
import valens.qt.v1.models.Profile;
import valens.qt.v1.models.Role;

import java.util.List;
import java.util.Set;


@Service
public class ServiceImpl {

    public Profile user;
    public UserSecurityDetails userSecurityDetails;

    List<GrantedAuthority> authorities;

    LoginResponseDTO loginResponseDTO;

    public Role role;
    public Set<Role> roles;
    public Profile profile;
    public Post post;
    public List<Post> posts;
    Sort sort = Sort.by(Sort.Direction.ASC, "dateTime");
    protected Comment comment;
    protected List<Comment> comments;

}
