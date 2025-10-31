package mindTrace.local.ExceptionsHandler;

import mindTrace.local.Dtos.ExceptionsResp.ExceptionsResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionsResp> handleException(Exception exp) {
        ExceptionsResp resp = new ExceptionsResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }
}
