package com.project.store.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath; // 244p 1.applicaton.properties에 설정한 "uploadPath" 값을 읽어옵니다.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") //2. 웹브라우저에 입력하는 url에 /images로 시작하는 경우
                // uploadPath에 설정한 폴더를 기준으로 파일을 읽어 오도록 설정.
                .addResourceLocations(uploadPath); //3.로컬 컴퓨터에 저장된 파일을 읽어올 root 경로를 설정합니다.
    }
}
