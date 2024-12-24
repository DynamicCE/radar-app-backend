package io.radar.radar_user_auth.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("Kullanıcı bulunamadı hatası: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Kullanıcı Bulunamadı",
                ex.getMessage(),
                LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("Kullanıcı zaten mevcut hatası: {}", ex.getMessage());
        return new ResponseEntity<>(new ApiError(
                HttpStatus.CONFLICT.value(),
                "Kullanıcı Zaten Mevcut",
                ex.getMessage(),
                LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("Validasyon hatası: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleAllUncaughtException(Exception ex) {
        log.error("Beklenmeyen bir hata oluştu", ex);
        return new ResponseEntity<>(new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Sistem Hatası",
                "Beklenmeyen bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.",
                LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    record ApiError(int status, String title, String message, LocalDateTime timestamp) {
    }
}