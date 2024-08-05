package valens.qt.v1.exceptions;
import valens.qt.v1.dtos.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import valens.qt.v1.dtos.response.Response;
import valens.qt.v1.models.enums.ResponseType;
import valens.qt.v1.payload.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message){
        super(message);
    }

    public ResponseEntity<ApiResponse> getResponse(){
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setType(ResponseType.EXCEPTION);
        response.setPayload(errorResponse);
        return  ResponseEntity.ok(ApiResponse.fail(this.getMessage()));
    }
}
