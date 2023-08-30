package com.youT.seenEar.domain.member.adapter.in.response;


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
public class MemberController {

    @Operation(description = "[노인]로그인")
    @PostMapping("/login/elder")
    public ResponseEntity<LoginResponse> login(@RequestBody KakaoLoginRequest kakaoLoginRequest) {
        return null;
    }
}
