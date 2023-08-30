package com.youT.seenEar.domain.member.adapter.in.response.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
// 카카오 서버에서 받아오는 info
public class KakaoInfoResponse {

    @JsonProperty("kakao_account")
    public KakaoAccount kakaoAccount;

}