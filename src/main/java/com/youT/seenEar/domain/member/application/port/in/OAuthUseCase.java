package com.youT.seenEar.domain.member.application.port.in;


import com.youT.seenEar.domain.member.adapter.in.response.kakao.KakaoInfoResponse;

public interface OAuthUseCase {
    String requestAccessToken(String authorizationCode);
    KakaoInfoResponse requestOauthInfo(String accessToken);
}
