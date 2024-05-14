package com.microservices.loans.exception;

import com.microservices.loans.model.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class handleGlobalExceptions extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request)
    {
        Map<String , String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String filedName = ((FieldError)error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(filedName , validationMsg);
        });
        return new ResponseEntity<>(validationErrors , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponseDto> handleGlobalExceptions(
            Exception exception,
            WebRequest webRequest
    ){
        ErrorResponseDto responseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
    @ExceptionHandler(LoansAlreadyExistsException.class)
    ResponseEntity<ErrorResponseDto> handleLoansAlreadyExistsException(
            LoansAlreadyExistsException exception,
            WebRequest webRequest
    ){
        ErrorResponseDto responseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(ResourcesNotFoundException.class)
    ResponseEntity<ErrorResponseDto> handleResourcesNotFoundException(
            ResourcesNotFoundException exception,
            WebRequest webRequest
    ){
        ErrorResponseDto responseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }



}
