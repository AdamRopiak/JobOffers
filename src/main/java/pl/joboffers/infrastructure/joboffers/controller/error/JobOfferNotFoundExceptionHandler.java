package pl.joboffers.infrastructure.joboffers.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.joboffers.domain.joboffers.JobOfferNotFoundException;
import pl.joboffers.infrastructure.joboffers.controller.dto.JobOfferNotFoundExceptionResponseDto;

@ControllerAdvice
@Log4j2
public class JobOfferNotFoundExceptionHandler {

    @ExceptionHandler(JobOfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JobOfferNotFoundExceptionResponseDto jobOfferNotFoundExceptionHandler(JobOfferNotFoundException exception){
        String exceptionMessage = exception.getMessage();
        log.error(exceptionMessage);
        return JobOfferNotFoundExceptionResponseDto.builder()
                .message(exceptionMessage)
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

}
