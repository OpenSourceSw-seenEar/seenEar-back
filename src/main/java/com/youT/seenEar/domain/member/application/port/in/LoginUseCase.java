package com.youT.seenEar.domain.member.application.port.in;

import com.youT.seenEar.domain.member.adapter.in.response.LoginRequest;
import com.youT.seenEar.domain.member.adapter.in.response.LoginResponse;
import com.youT.seenEar.domain.member.adapter.in.response.kakao.KakaoInfoResponse;
import com.youT.seenEar.domain.member.domain.Member;

public interface LoginUseCase {
    LoginResponse elderLogin(LoginRequest loginRequest);

    Member saveElder(LoginRequest loginRequest);

    LoginResponse youthLogin(String authorizationCode);

    Member saveYouth(KakaoInfoResponse kakaoInfoResponse);
}
