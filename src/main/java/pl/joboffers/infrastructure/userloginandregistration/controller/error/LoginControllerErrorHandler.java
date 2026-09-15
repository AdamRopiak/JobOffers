package pl.joboffers.infrastructure.userloginandregistration.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.joboffers.infrastructure.userloginandregistration.controller.TokenController;

@ControllerAdvice(basePackageClasses = TokenController.class)
public class LoginControllerErrorHandler {

    private static String BAD_CREDENTIALS = "Bad Credentials";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public LoginErrorResponse badCredentialsHandler(){
        return LoginErrorResponse.builder()
                .message(BAD_CREDENTIALS)
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }
}
