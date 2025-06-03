# Security Module

이 모듈은 애플리케이션의 보안 관련 기능을 제공합니다.

## 주요 기능

### 1. JWT 토큰 처리 (JwtTokenProvider)
- JWT 토큰 생성
- 토큰 검증
- 토큰에서 사용자 정보 추출

### 2. Spring Security 설정 (SecurityConfig)
- 보안 설정
- 인증/인가 규칙 정의
- CSRF 보호
- 세션 관리

### 3. 암호화 유틸리티 (EncryptionUtil)
- AES 암호화/복호화
- 비밀번호 해싱 (BCrypt)
- 비밀번호 검증

## 사용 방법

### JWT 토큰 사용
```java
@Autowired
private JwtTokenProvider tokenProvider;

// 토큰 생성
String token = tokenProvider.generateToken(authentication);

// 토큰 검증
boolean isValid = tokenProvider.validateToken(token);

// 토큰에서 사용자명 추출
String username = tokenProvider.getUsernameFromJWT(token);
```

### 암호화 유틸리티 사용
```java
@Autowired
private EncryptionUtil encryptionUtil;

// 문자열 암호화
String encrypted = encryptionUtil.encrypt("sensitive data");

// 문자열 복호화
String decrypted = encryptionUtil.decrypt(encrypted);

// 비밀번호 해싱
String hashedPassword = encryptionUtil.hashPassword("raw password");

// 비밀번호 검증
boolean matches = encryptionUtil.matchesPassword("raw password", hashedPassword);
```

## 설정

application.properties 또는 application.yml에 다음 설정이 필요합니다:

```properties
app.jwt-secret=your-jwt-secret
app.jwt-expiration-milliseconds=86400000
```

## 보안 고려사항

1. JWT 시크릿 키는 충분히 길고 복잡하게 설정해야 합니다.
2. 운영 환경에서는 암호화 키를 환경 변수나 보안 저장소에서 관리해야 합니다.
3. 토큰 만료 시간은 보안 요구사항에 맞게 적절히 설정해야 합니다.
4. HTTPS를 사용하여 통신을 암호화해야 합니다. 