package com.youT.seenEar.domain.member.application.service;


import com.youT.seenEar.domain.member.adapter.in.response.LoginResponse;
import com.youT.seenEar.domain.member.adapter.in.response.kakao.KakaoInfoResponse;
import com.youT.seenEar.domain.member.adapter.out.persistence.MemberRepository;
import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.member.domain.MemberType;
import com.youT.seenEar.domain.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthLoginService {

    private final MemberRepository memberRepository;
    private final OAuthService oAuthService;
    private final JwtService jwtService;

    @Transactional
    public LoginResponse kakaoLogin(String authorizationCode) {

        String accessToken = oAuthService.requestAccessToken(authorizationCode);
        KakaoInfoResponse kakaoInfoResponse =oAuthService.requestOauthInfo(accessToken);
        String email=kakaoInfoResponse.getKakaoAccount().getEmail();
        Member member=memberRepository.findByEmail(email).orElseGet(
                ()->saveMemberByKakaoLogin(kakaoInfoResponse)
        );
        String serviceAccessToken= jwtService.createAccessToken(email);
        String serviceRefreshToken= jwtService.createRefreshToken(email);

        member.updateRefreshToken(serviceRefreshToken);
        return LoginResponse.builder()
                .uuid(member.getUuid())
                .accessToken(serviceAccessToken)
                .refreshToken(serviceRefreshToken)
                .build();
    }

    @Transactional
    public Member saveMemberByKakaoLogin(KakaoInfoResponse kakaoInfoResponse) {

        String uuid = UUID.randomUUID().toString();
        System.err.println(kakaoInfoResponse.getKakaoAccount().getEmail());
        Member member = Member.builder()
                .email(kakaoInfoResponse.getKakaoAccount().getEmail())
                .role(Role.USER)
                .memberType(MemberType.YOUTH)
                .uuid(uuid)
                .name(kakaoInfoResponse.getKakaoAccount().getProfile().getNickname())
                .email(kakaoInfoResponse.getKakaoAccount().getEmail())
                .build();
        memberRepository.saveAndFlush(member);
        return  member;

    }

}