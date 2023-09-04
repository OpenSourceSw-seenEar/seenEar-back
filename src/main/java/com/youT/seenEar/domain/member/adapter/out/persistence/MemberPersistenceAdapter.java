package com.youT.seenEar.domain.member.adapter.out.persistence;

import com.youT.seenEar.domain.member.application.port.out.LoadMemberPort;
import com.youT.seenEar.domain.member.application.port.out.SaveMemberPort;
import com.youT.seenEar.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, SaveMemberPort {

    private final MemberRepository memberRepository;
    @Override
    public Optional<Member> findByUuid(String uuid) {
        return memberRepository.findByUuid(uuid);
    }
}
