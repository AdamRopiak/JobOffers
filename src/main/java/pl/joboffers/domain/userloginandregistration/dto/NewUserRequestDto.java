package pl.joboffers.domain.userloginandregistration.dto;

import jakarta.validation.constraints.NotBlank;

public record NewUserRequestDto(
        @NotBlank(message = "${username.notblank}")
        String userName,
        @NotBlank(message = "${password.notblank}")
        String password) {
}
