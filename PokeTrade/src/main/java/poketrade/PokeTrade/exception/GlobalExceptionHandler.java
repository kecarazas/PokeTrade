package poketrade.PokeTrade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundException ex){
        ErrorResponse error = new ErrorResponse();
        error.setMensaje(ex.getMessage());
        error.setError("Not Found");
        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exception(MethodArgumentNotValidException ex){
        ErrorResponse error = new ErrorResponse();
        String mensaje = ex.getBindingResult().getFieldErrors().stream().filter(e->e.getCode().equals("NotBlank")).findFirst().orElse(ex.getBindingResult().getFieldError()).getDefaultMessage();
        error.setMensaje(mensaje);
        error.setError("Bad Request");
        error.setStatus(HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex){
        ErrorResponse error = new ErrorResponse();
        error.setMensaje("Error interno del servidor");
        error.setError("internal server error");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
