# Audit Module

이 모듈은 엔티티 변경 이력 추적과 사용자 활동 로깅 기능을 제공합니다.

## 주요 기능

### 1. AuditEntity
- 생성 시간 추적
- 수정 시간 추적
- 생성자 추적
- 수정자 추적

### 2. UserActivityLog
- 사용자 활동 로깅
- IP 주소 기록
- User-Agent 기록
- 타임스탬프 기록

## 사용 방법

### 1. AuditEntity 사용
```java
@Entity
public class User extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    // ... 다른 필드들
}
```

### 2. UserActivityLog 사용
```java
@Service
public class UserActivityService {
    @Autowired
    private UserActivityLogRepository repository;
    
    public void logActivity(String username, String action, String resource) {
        UserActivityLog log = new UserActivityLog();
        log.setUsername(username);
        log.setAction(action);
        log.setResource(resource);
        log.setIpAddress(getCurrentIpAddress());
        log.setUserAgent(getCurrentUserAgent());
        
        repository.save(log);
    }
}
```

## 감사 정보

### AuditEntity 필드
- createdAt: 생성 시간
- updatedAt: 수정 시간
- createdBy: 생성자
- updatedBy: 수정자

### UserActivityLog 필드
- id: 로그 ID
- username: 사용자명
- action: 수행한 작업
- resource: 대상 리소스
- details: 상세 정보
- timestamp: 발생 시간
- ipAddress: IP 주소
- userAgent: User-Agent 정보

## 설정

### JPA Auditing 활성화
```java
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getName);
    }
}
```

## 주의사항

1. 감사 정보는 수정이 불가능하도록 설정해야 합니다.
2. 민감한 정보는 로그에 기록하지 않도록 주의해야 합니다.
3. 로그 데이터의 보관 기간을 정책에 맞게 설정해야 합니다.
4. 대용량 로그 처리를 위한 전략이 필요합니다.

## 확장 가능성

1. 로그 데이터 아카이빙
2. 로그 분석 기능
3. 실시간 모니터링
4. 알림 기능 