package restaurant.com.hostService.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import restaurant.com.hostService.exceptions.ExceptionResponse;

import java.util.Date;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private ResponseEntity<ExceptionResponse> responseEntity;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request != null ? request.getDescription(false) : null);

        logger.debug("Handling " + ex.getMessage());

        logger.error("Exception: " + ex);
        responseEntity = new ResponseEntity<>(exceptionResponse, status);
        return responseEntity;
    }

    public ResponseEntity<ExceptionResponse> getResponseEntity() {
        return responseEntity;
    }

}