package com.youT.seenEar.domain.member.application.port.out;

import com.youT.seenEar.domain.member.domain.Member;

import java.util.Optional;

public interface LoadMemberPort {

    Optional<Member> findByUuid(String uuid);
}
