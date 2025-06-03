# Logging Module

이 모듈은 애플리케이션의 로깅 기능을 제공합니다.

## 주요 기능

### 1. LoggingAspect
- 메소드 실행 시간 측정
- 메소드 진입/종료 로깅
- 예외 발생 시 로깅
- AOP를 통한 자동 로깅

### 2. Logback 설정
- 콘솔 로깅
- 파일 로깅
- 로그 파일 롤링
- 로그 레벨 관리

## 로그 레벨

- ERROR: 오류 발생 시
- WARN: 경고 발생 시
- INFO: 일반적인 정보
- DEBUG: 디버깅 정보
- TRACE: 상세한 디버깅 정보

## 로그 포맷

### 콘솔 로그
```
2024-03-21 10:30:45,123 INFO [main] com.core.example.ExampleClass: 메시지
```

### 파일 로그
```
2024-03-21 10:30:45,123 INFO com.core.example.ExampleClass [main] 메시지
```

## 설정

### 로그 파일 위치
- 기본 로그 파일: `./logs/spring-boot-logger.log`
- 보관 로그 파일: `./logs/archived/spring-boot-logger-YYYY-MM-DD.i.log`

### 로그 파일 관리
- 최대 파일 크기: 10MB
- 보관 기간: 30일
- 파일 롤링: 일별 + 크기 기반

## 사용 방법

### 1. 로거 사용
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleClass {
    private static final Logger logger = LoggerFactory.getLogger(ExampleClass.class);
    
    public void exampleMethod() {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warning message");
        logger.error("Error message");
    }
}
```

### 2. AOP 로깅
- 모든 메소드에 자동으로 적용됨
- 메소드 실행 시간 측정
- 예외 발생 시 자동 로깅

## 주의사항

1. 로그 파일의 크기와 보관 기간을 적절히 관리해야 합니다.
2. 민감한 정보는 로그에 기록하지 않도록 주의해야 합니다.
3. 프로덕션 환경에서는 적절한 로그 레벨을 설정해야 합니다. 