package valens.qt.v1.models;
import lombok.Getter;
import lombok.Setter;
import valens.qt.v1.models.enums.EAccountStatus;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String email;

    private String password;
    private String userName;

    private EAccountStatus status = EAccountStatus.ACTIVE;

    public Profile(){}

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Profile(String email, String password, String userName){
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

}
