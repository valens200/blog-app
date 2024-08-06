package valens.qt.v1.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Person{
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
