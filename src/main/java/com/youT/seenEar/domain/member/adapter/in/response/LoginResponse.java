package com.youT.seenEar.domain.member.adapter.in.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
  
    private String accessToken;
    private String refreshToken;
    private String uuid;
}
