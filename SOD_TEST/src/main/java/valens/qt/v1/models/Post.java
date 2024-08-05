package valens.qt.v1.models;

import com.mysql.cj.protocol.ColumnDefinition;
import lombok.Getter;
import lombok.Setter;
import valens.qt.v1.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post extends InitiatorAudit  implements Comparable{
    @Id
    @JoinColumn
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Profile author;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Override
    public int compareTo(Object o) {
        Post post = (Post) o;
        if(this.getDateTime().compareTo(post.getDateTime()) != 0){
            return this.getDateTime().compareTo(post.getDateTime());
        }
        return this.getTitle().compareTo(post.getTitle());
    }
}
