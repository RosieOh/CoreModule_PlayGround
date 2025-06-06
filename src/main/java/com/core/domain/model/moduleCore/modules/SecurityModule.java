package com.core.domain.model.moduleCore.modules;

import java.util.Arrays;

import com.core.domain.model.moduleCore.ModuleInfo;

public class SecurityModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Security",
            Arrays.asList(
                "JWT 기반 인증/인가",
                "비밀번호 암호화",
                "보안 설정 관리",
                "CORS 설정",
                "CSRF 보호",
                "OAuth2 인증",
                "세션 관리"
            ),
            """
            // 1. JWT 토큰 생성 및 검증
            @Autowired
            private JwtTokenProvider jwtTokenProvider;
            
            // 토큰 생성
            String token = jwtTokenProvider.generateToken(username);
            
            // 토큰 검증
            if (jwtTokenProvider.validateToken(token)) {
                String username = jwtTokenProvider.getUsernameFromToken(token);
            }
            
            // 2. 비밀번호 암호화
            @Autowired
            private PasswordEncoder passwordEncoder;
            
            String encodedPassword = passwordEncoder.encode(rawPassword);
            boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
            
            // 3. Spring Security 설정
            @Configuration
            @EnableWebSecurity
            public class SecurityConfig {
                @Bean
                public SecurityFilterChain filterChain(HttpSecurity http) {
                    http
                        .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/public/**").permitAll()
                            .anyRequest().authenticated()
                        )
                        .csrf(csrf -> csrf.disable());
                    return http.build();
                }
            }
            
            // 4. OAuth2 설정
            @Configuration
            @EnableOAuth2Client
            public class OAuth2Config {
                @Bean
                public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext) {
                    return new OAuth2RestTemplate(googleClient(), oauth2ClientContext);
                }
            }
            
            // 5. 세션 관리
            @Configuration
            public class SessionConfig {
                @Bean
                public HttpSessionEventPublisher httpSessionEventPublisher() {
                    return new HttpSessionEventPublisher();
                }
            }
            """,
            """
            # Security 설정
            security.jwt.secret=your-secret-key
            security.jwt.expiration=86400000
            security.cors.allowed-origins=http://localhost:3000
            security.cors.allowed-methods=GET,POST,PUT,DELETE
            security.cors.allowed-headers=*
            security.oauth2.client.registration.google.client-id=your-client-id
            security.oauth2.client.registration.google.client-secret=your-client-secret
            security.session.timeout=1800
            security.session.maximum-sessions=1
            """,
            Arrays.asList("validation", "audit"),
            Arrays.asList(
                "JWT 시크릿 키는 안전하게 관리해야 합니다.",
                "토큰 만료 시간은 적절히 설정해야 합니다.",
                "CORS 설정은 필요한 도메인만 허용해야 합니다.",
                "비밀번호는 반드시 암호화하여 저장해야 합니다.",
                "보안 관련 설정은 프로덕션 환경에서 별도로 관리해야 합니다.",
                "OAuth2 인증 시 리다이렉트 URI를 정확히 설정해야 합니다.",
                "세션 타임아웃은 사용자 경험을 고려하여 설정해야 합니다."
            )
        );
    }
} 