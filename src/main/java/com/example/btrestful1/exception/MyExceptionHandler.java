package com.example.btrestful1.exception;


import com.example.btrestful1.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@EnableWebMvc
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    //not found exception
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> HanderException(NotFoundException r) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("NOT_FOUND", r.getMessage()));
    }

    //not delete exception
    @ExceptionHandler({NotDeleteException.class})
    public ResponseEntity<ErrorResponse> HanderException(NotDeleteException r) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("CAN_NOT_DELETE", r.getMessage()));
    }


    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("INTERNAL_EX", ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}