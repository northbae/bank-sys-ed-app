package kz.osu.cinimex.exception;

import kz.osu.cinimex.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntryExistsException.class)
    public ResponseEntity<ErrorMessageDto> handleEntryExistsException(EntryExistsException entryExistsException) {
        return getResponse(entryExistsException, HttpStatus.BAD_REQUEST, entryExistsException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleNotFoundException(NotFoundException notFoundException) {
        return getResponse(notFoundException, HttpStatus.BAD_REQUEST, notFoundException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException
                                                                                             methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        String message = fieldErrors.get(0).getField() + " является обязательным полем";
        return getResponse(methodArgumentNotValidException, HttpStatus.BAD_REQUEST, message);
    }

    private ResponseEntity<ErrorMessageDto> getResponse(Exception exception, HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageDto()
                        .setName(exception.getClass().getName())
                        .setDescription(message)
                        .setTimestamp(String.valueOf(ZonedDateTime.now(ZoneOffset.UTC)))
                );
    }
}
