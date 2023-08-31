package com.youT.seenEar.domain.member.adapter.in.response;

import com.youT.seenEar.domain.member.domain.MemberType;
import lombok.Getter;

@Getter
public class LoginRequest {

    private String name;

    // GRANDMA,GRANDPA만 가능
    private MemberType memberType;

    // 신규 유저일경우 빈 문자열로 보낼 것
    private String uuid;

}
