package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Core Module 애플리케이션의 메인 클래스
 * 
 * 주요 기능:
 * - Spring Boot 애플리케이션 시작점
 * - 컴포넌트 스캔 설정
 * - JPA 엔티티 및 리포지토리 스캔 설정
 */
@SpringBootApplication
@EntityScan(basePackages = "com.core")
@EnableJpaRepositories(basePackages = "com.core")
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }

}
