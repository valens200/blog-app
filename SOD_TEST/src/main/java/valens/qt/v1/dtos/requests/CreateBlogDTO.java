package valens.qt.v1.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class CreateBlogDTO {
    @NotNull(message = "title is required")
    private String title;
    @NotNull(message = "The content is required")
    private String content;

}
