package pl.joboffers.infrastructure.apivalidation;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
@Log4j2
public class ApiValidationError {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiValidtationErrorDto handleValidationException(MethodArgumentNotValidException exception){
        final List<String> errors = getErrorsFromExcetion(exception);
        log.warn("Validation errors: {}", errors);
        return ApiValidtationErrorDto.builder()
                .messages(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build();

    }

    private List<String> getErrorsFromExcetion(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }
}
