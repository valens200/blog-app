package valens.qt.v1.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import valens.qt.v1.models.enums.ResponseType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
    private ResponseType type;
    private T payload;
}
