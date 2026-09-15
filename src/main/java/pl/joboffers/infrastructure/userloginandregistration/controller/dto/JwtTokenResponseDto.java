package pl.joboffers.infrastructure.userloginandregistration.controller.dto;

import lombok.Builder;

@Builder
public record JwtTokenResponseDto(String userName,
                                  String token) {
}
