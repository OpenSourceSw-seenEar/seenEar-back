package com.youT.seenEar.domain.member.adapter.out.external.request;

import lombok.Data;


@Data
//카카오 서버에 로그인 요청
public class KakaoLoginRequest {
    private String authorizationCode;


}
