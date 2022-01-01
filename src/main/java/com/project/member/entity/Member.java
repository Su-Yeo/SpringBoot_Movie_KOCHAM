package com.project.member.entity;

import com.project.member.constant.Role;
import com.project.member.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member {
    @Id@Column(name="member_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    @Column(name="member_point", columnDefinition = "integer default 0")
    private Integer point; //적립금

    @Enumerated(EnumType.STRING)
    private Role role;
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setPoint(memberFormDto.getPoint());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;

    }

    public void updateMember(MemberFormDto memberFormDto){
        this.id = memberFormDto.getId();
        this.name = memberFormDto.getName();
        this.email = memberFormDto.getEmail();
        this.password = memberFormDto.getPassword();
        this.address = memberFormDto.getAddress();
        this.point = memberFormDto.getPoint();
    }

}