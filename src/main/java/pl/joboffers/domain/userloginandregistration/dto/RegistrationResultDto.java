package pl.joboffers.domain.userloginandregistration.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(String userId, String userName) {
}
