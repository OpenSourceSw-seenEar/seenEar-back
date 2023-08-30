package com.youT.seenEar.domain.member.adapter.in.response.kakao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoAccount {
    @JsonProperty("profile")
    public KakaoProfile profile;

    @JsonProperty("email")
    public String email;

    @JsonProperty("name")
    public String name;

}
