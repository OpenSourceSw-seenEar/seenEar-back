package com.youT.seenEar.domain.member.application.service;


import com.youT.seenEar.domain.member.adapter.in.response.LoginRequest;
import com.youT.seenEar.domain.member.adapter.in.response.LoginResponse;
import com.youT.seenEar.domain.member.adapter.in.response.kakao.KakaoInfoResponse;
import com.youT.seenEar.domain.member.adapter.out.persistence.MemberRepository;
import com.youT.seenEar.domain.member.application.port.in.LoginUseCase;
import com.youT.seenEar.domain.member.domain.Member;
import com.youT.seenEar.domain.member.domain.MemberType;
import com.youT.seenEar.domain.member.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LoginService implements LoginUseCase {

    private final MemberRepository memberRepository;
    private final OAuthService oAuthService;
    private final JwtService jwtService;

    @Override
    @Transactional
    public LoginResponse youthLogin(String authorizationCode) {

        String accessToken = oAuthService.requestAccessToken(authorizationCode);
        KakaoInfoResponse kakaoInfoResponse =oAuthService.requestOauthInfo(accessToken);
        String email=kakaoInfoResponse.getKakaoAccount().getEmail();
        Member member=memberRepository.findByEmail(email).orElseGet(
                ()->saveYouth(kakaoInfoResponse)
        );
        String serviceAccessToken= jwtService.createAccessToken(member.getUuid());
        String serviceRefreshToken= jwtService.createRefreshToken(member.getUuid());

        member.updateRefreshToken(serviceRefreshToken);
        return LoginResponse.builder()
                .uuid(member.getUuid())
                .accessToken(serviceAccessToken)
                .refreshToken(serviceRefreshToken)
                .build();
    }

    @Override
    @Transactional
    public Member saveYouth(KakaoInfoResponse kakaoInfoResponse) {

        String uuid = UUID.randomUUID().toString();
        System.err.println(kakaoInfoResponse.getKakaoAccount().getEmail());
        Member member = Member.builder()
                .email(kakaoInfoResponse.getKakaoAccount().getEmail())
                .role(Role.USER)
                .memberType(MemberType.YOUTH)
                .uuid(uuid)
                .name(kakaoInfoResponse.getKakaoAccount().getProfile().getNickname())
                .build();
        memberRepository.saveAndFlush(member);
        return  member;

    }

    @Override
    public LoginResponse elderLogin(LoginRequest loginRequest) {
        Member member=memberRepository.findByUuid(loginRequest.getUuid()).orElseGet(
                ()->saveElder(loginRequest)
        );
        String serviceAccessToken= jwtService.createAccessToken(member.getUuid());
        String serviceRefreshToken= jwtService.createRefreshToken(member.getUuid());

        member.updateRefreshToken(serviceRefreshToken);
        return LoginResponse.builder()
                .uuid(member.getUuid())
                .accessToken(serviceAccessToken)
                .refreshToken(serviceRefreshToken)
                .build();
    }

    @Override
    public Member saveElder(LoginRequest loginRequest) {

        String uuid = UUID.randomUUID().toString();
        Member member = Member.builder()
                .role(Role.USER)
                .memberType(loginRequest.getMemberType())
                .uuid(uuid)
                .name(loginRequest.getName())
                .build();
        memberRepository.saveAndFlush(member);
        return  member;

    }

}