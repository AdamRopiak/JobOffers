package pl.joboffers.infrastructure.joboffers.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.joboffers.domain.joboffers.JobOfferNotFoundException;
import pl.joboffers.infrastructure.joboffers.controller.error.dto.JobOfferExceptionResponseDto;
import pl.joboffers.infrastructure.joboffers.controller.error.dto.JobOfferPostExceptionResponseDto;

import java.util.Collections;

@ControllerAdvice
@Log4j2
public class JobOfferNotFoundExceptionHandler {

    @ExceptionHandler(JobOfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JobOfferExceptionResponseDto jobOfferNotFoundExceptionHandler(JobOfferNotFoundException exception){
        String exceptionMessage = exception.getMessage();
        log.error(exceptionMessage);
        return JobOfferExceptionResponseDto.builder()
                .message(exceptionMessage)
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public JobOfferPostExceptionResponseDto jobOfferAlreadyExistsExceptionHandler(DuplicateKeyException exception){
        String exceptionMessage = String.format("Offer with this url already exists in database.");
        String exceptionLoger = String.format(exception.getMessage());
        log.error(exceptionLoger);
        return JobOfferPostExceptionResponseDto.builder()
                .messages(Collections.singletonList(exceptionMessage))
                .status(HttpStatus.CONFLICT)
                .build();
    }

}
