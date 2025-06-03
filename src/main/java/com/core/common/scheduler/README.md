# Scheduler Module

이 모듈은 작업 스케줄링과 배치 처리 기능을 제공합니다.

## 주요 기능

### 1. SchedulerConfig
- 스케줄러 활성화
- 스케줄러 설정

### 2. ExampleScheduler
- 일일 작업 스케줄링
- 시간별 작업 스케줄링
- 주기적 작업 스케줄링
- 지연 작업 스케줄링

## 사용 방법

### 1. 스케줄러 설정
```java
@Configuration
@EnableScheduling
public class SchedulerConfig {
    // 스케줄러 설정
}
```

### 2. 스케줄러 구현
```java
@Component
public class CustomScheduler {
    @Scheduled(cron = "0 0 12 * * ?")  // 매일 정오에 실행
    public void scheduledTask() {
        // 작업 수행
    }
}
```

## 스케줄링 옵션

### 1. Cron 표현식
```
초 분 시 일 월 요일
* * * * * *
```

예시:
- `0 0 0 * * ?` : 매일 자정
- `0 0 12 * * ?` : 매일 정오
- `0 0/30 * * * ?` : 30분마다
- `0 0 8-10 * * ?` : 매일 8시, 9시, 10시

### 2. Fixed Rate
```java
@Scheduled(fixedRate = 60000)  // 1분마다 실행
```

### 3. Fixed Delay
```java
@Scheduled(fixedDelay = 60000)  // 이전 작업 완료 후 1분 후 실행
```

### 4. Initial Delay
```java
@Scheduled(initialDelay = 60000, fixedRate = 600000)  // 1분 후 시작하여 10분마다 실행
```

## 주의사항

1. 스케줄러 작업은 가능한 한 짧게 유지해야 합니다.
2. 장시간 실행되는 작업은 비동기로 처리해야 합니다.
3. 스케줄러 작업의 실패를 적절히 처리해야 합니다.
4. 서버 시간대 설정을 확인해야 합니다.

## 확장 가능성

1. 동적 스케줄링
2. 작업 상태 모니터링
3. 작업 재시도 메커니즘
4. 분산 스케줄링 