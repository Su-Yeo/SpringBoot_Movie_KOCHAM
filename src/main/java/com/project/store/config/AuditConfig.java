package com.project.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // 223p 1. JPA의 Auditing 기능을 활성화시킨다.
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){ // 223p 2. 등록자와 수정자를 처리해주는 AuditorAware을 빈으로 등록한다.
        return new AuditorAwarelmpl();
    }
}
