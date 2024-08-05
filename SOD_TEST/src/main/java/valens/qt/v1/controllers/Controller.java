package valens.qt.v1.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import valens.qt.v1.exceptions.BadRequestException;
import valens.qt.v1.payload.ApiResponse;

import java.util.HashSet;
import java.util.Set;

/**
 * Global controller advice for handling exceptions across all controllers.
 */
@Component
public class Controller {
    protected Pageable pageable;

    /**
     * Exception handler for BadRequestException.
     * @param badRequestException The BadRequestException thrown.
     * @return ResponseEntity with a bad request status and error message.
     */
    @ExceptionHandler
    public ResponseEntity<ApiResponse> handleBadRequestException(BadRequestException badRequestException) {
        return ResponseEntity.badRequest().body(ApiResponse.fail(badRequestException.getMessage()));
    }

    /**
     * Exception handler for MethodArgumentNotValidException.
     * @param exception The MethodArgumentNotValidException thrown.
     * @return ResponseEntity with a bad request status and validation error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Set<String> errorMessages = new HashSet<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            errorMessages.add(error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, errorMessages));
    }
}
