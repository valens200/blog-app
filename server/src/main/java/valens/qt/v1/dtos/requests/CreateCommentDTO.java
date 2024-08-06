package valens.qt.v1.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class CreateCommentDTO extends CreateBlogDTO{
    @NotNull(message = "The post Id should not be null")
    private UUID postId;
}
