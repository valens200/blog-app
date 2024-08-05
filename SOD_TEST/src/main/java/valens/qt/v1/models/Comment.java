package valens.qt.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import valens.qt.v1.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Comment extends InitiatorAudit  implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Profile author;
    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public Comment(){}
    public Comment(String title, String content){
        this.title = title;
        this.content = content;
    }
    @Override
    public int compareTo(Object o) {
        Comment comment = (Comment) o;
//        if(this.getDateTime().compareTo(comment.getDateTime()) != 0){
            return -this.getDateTime().compareTo(comment.getDateTime());
//        }
//        return this.getTitle().compareTo(comment.getTitle());
    }
}
