# Core Module

이 프로젝트는 Spring Boot 기반의 공통 모듈들을 제공합니다.

## 모듈 구성

### 1. Security Module
- JWT 기반 인증/인가 처리
- Spring Security 설정
- 암호화 유틸리티 (AES, BCrypt)
- 보안 관련 공통 기능 제공

### 2. Validation Module
- 데이터 유효성 검증
- 커스텀 검증 어노테이션
- 이메일, 전화번호, 비밀번호 등 검증
- BindingResult 에러 처리

### 3. Logging Module
- AOP 기반 메소드 로깅
- Logback 설정
- 로그 파일 관리
- 로그 레벨 관리

### 4. Cache Module
- Spring Cache 설정
- 캐시 유틸리티
- 캐시 데이터 관리
- 캐시 만료 정책

### 5. Audit Module
- 엔티티 변경 이력 추적
- 사용자 활동 로깅
- JPA Auditing
- 감사 정보 관리

### 6. Message Module
- 다국어 지원
- 메시지 포맷팅
- 로케일 관리
- 메시지 소스 설정

### 7. File Module
- 파일 업로드/다운로드
- 파일 유효성 검증
- 파일 저장소 관리
- 파일 확장자/크기 검증

### 8. Scheduler Module
- 작업 스케줄링
- 배치 처리
- Cron 표현식 지원
- 스케줄러 설정

## 사용 방법

각 모듈의 자세한 사용 방법은 해당 모듈의 README.md 파일을 참조하세요.

## 의존성

- Spring Boot 3.4.6
- Spring Security
- Spring Data JPA
- Spring Cache
- JWT
- Lombok
- SLF4J
- Logback

## 주의사항

1. 각 모듈은 독립적으로 사용할 수 있도록 설계되었습니다.
2. 모듈 간의 의존성을 최소화했습니다.
3. 설정은 application.properties 또는 application.yml에서 관리됩니다.
4. 보안 관련 설정은 반드시 환경 변수나 보안 저장소에서 관리해야 합니다.

## 확장 가능성

1. 새로운 모듈 추가
2. 기존 모듈 기능 확장
3. 외부 시스템 연동
4. 모니터링 기능 추가 