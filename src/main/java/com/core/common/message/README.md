# Message Module

이 모듈은 다국어 지원과 메시지 포맷팅 기능을 제공합니다.

## 주요 기능

### 1. MessageConfig
- 메시지 소스 설정
- 로케일 리졸버 설정
- 기본 인코딩 설정

### 2. MessageUtil
- 메시지 조회
- 메시지 포맷팅
- 기본 메시지 처리

## 사용 방법

### 1. 메시지 프로퍼티 파일
```properties
# messages.properties (기본)
user.notfound=사용자를 찾을 수 없습니다.
user.welcome=안녕하세요, {0}님!

# messages_en.properties (영어)
user.notfound=User not found.
user.welcome=Welcome, {0}!
```

### 2. MessageUtil 사용
```java
@Service
public class UserService {
    @Autowired
    private MessageUtil messageUtil;

    public String getWelcomeMessage(String username) {
        return messageUtil.getMessage("user.welcome", username);
    }

    public String getNotFoundMessage() {
        return messageUtil.getMessage("user.notfound");
    }
}
```

## 메시지 포맷

### 1. 단순 메시지
```properties
message.key=메시지 내용
```

### 2. 파라미터가 있는 메시지
```properties
message.key=메시지 {0} 내용 {1}
```

### 3. 기본 메시지가 있는 경우
```java
messageUtil.getMessage("key", "기본 메시지");
```

## 로케일 설정

### 1. 세션 기반 로케일
```java
@Bean
public LocaleResolver localeResolver() {
    SessionLocaleResolver resolver = new SessionLocaleResolver();
    resolver.setDefaultLocale(Locale.KOREA);
    return resolver;
}
```

### 2. 로케일 변경
```java
@GetMapping("/change-locale")
public String changeLocale(@RequestParam String lang) {
    Locale locale = new Locale(lang);
    LocaleContextHolder.setLocale(locale);
    return "redirect:/";
}
```

## 주의사항

1. 메시지 키는 일관성 있게 관리해야 합니다.
2. 모든 메시지는 UTF-8로 인코딩해야 합니다.
3. 메시지 파일은 정기적으로 백업해야 합니다.
4. 새로운 언어 추가 시 모든 메시지를 번역해야 합니다.

## 확장 가능성

1. 데이터베이스 기반 메시지 관리
2. 동적 메시지 업데이트
3. 메시지 캐싱
4. 메시지 검증 