package pl.joboffers.infrastructure.userloginandregistration.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequestDto(
        @NotBlank(message = "{username.notblank}")
        String username,
        @NotBlank(message = "{password.notblank}")
        String password
                              ) {
}
