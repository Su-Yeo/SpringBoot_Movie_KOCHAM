package com.project.member.controller;

import com.project.member.dto.MemberFormDto;
import com.project.member.entity.Member;
import com.project.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;



    @PostMapping(value = "/new1")
    public String memberForm(MemberFormDto memberFormDto){

        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);

        return "redirect:/";
    }


//임시 추가

    @GetMapping(value = "/signup")
    public String memberForm(Model model){

        return "member/memberSignUp";
    }






}
