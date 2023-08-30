package com.youT.seenEar.domain.member.adapter.out.external;

import com.youT.seenEar.domain.member.adapter.in.response.LoginResponse;
import com.youT.seenEar.domain.member.adapter.out.external.request.KakaoLoginRequest;
import com.youT.seenEar.domain.member.application.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthLoginService oAuthLoginService;
    @Operation(description = "[청년] 카카오 로그인")
    @PostMapping("/login/youth")
    public ResponseEntity<LoginResponse> loginKakao(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        return ResponseEntity.ok(oAuthLoginService.kakaoLogin(kakaoLoginRequest.getAuthorizationCode()));
    }

}
