package com.project.member.service;

import com.project.member.dto.MemberFormDto;
import com.project.member.entity.Member;
import com.project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private  final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    //회원정보 가져오기
    public Member getMember(String email) {
        Member findMember = memberRepository.findByEmail(email);
        return findMember;
    }

    //포인트 적립
    public void setPlusPoint(long id, int point){
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberFormDto memberFormDto = MemberFormDto.of(member);
        memberFormDto.setPoint(memberFormDto.getPoint()+point);
        member.updateMember(memberFormDto);
    }

    //포인트 차감
    public void setMinusPoint(long id, int point){
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MemberFormDto memberFormDto = MemberFormDto.of(member);
        memberFormDto.setPoint(memberFormDto.getPoint()-point);
        member.updateMember(memberFormDto);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
