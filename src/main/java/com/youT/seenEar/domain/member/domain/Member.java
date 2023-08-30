package com.youT.seenEar.domain.member.domain;

import com.youT.seenEar.domain.adviceCard.domain.AdviceCard;
import com.youT.seenEar.domain.adviceCard.domain.YouthAdviceMapping;
import com.youT.seenEar.domain.thanksCard.domain.ThanksCard;
import com.youT.seenEar.global.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "Member")
public class Member extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "memberType")
    private MemberType memberType;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "refreshToken")
    private String refreshToken;
    // [연관관계] 청년 (1) : 감사카드 (N) : 노인(1)
    @OneToMany(mappedBy = "thanksYouth")
    List<ThanksCard> youthSendThanksCards=new ArrayList<>();
    // [연관관계] 청년 (1) : 감사카드 (N) : 노인(1)
    @OneToMany(mappedBy = "thanksElder")
    List<ThanksCard> elderReceiveThanksCards=new ArrayList<>();
    // [연관관계] 청년 (1) : 조언 (N) : 노인(1)
    @OneToMany(mappedBy = "advisor")
    List<AdviceCard> elderSendAdviceCards=new ArrayList<>();
    // [연관관계] 청년 (N) : 조언 (M)
    @OneToMany(mappedBy = "adviceRecipient")
    List<YouthAdviceMapping> youthAdviceMappingList=new ArrayList<>();

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
    @Override
    public ArrayList<GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(Role.USER.toString()));
        return auth;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
