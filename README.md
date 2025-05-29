# Core Common Module

Spring Boot 3.4 기반의 공통 모듈입니다. 다른 프로젝트에서 재사용할 수 있는 공통 기능들을 제공합니다.

## 주요 기능

1. 공통 응답 처리 (`ApiResponse`)
2. 전역 예외 처리 (`GlobalExceptionHandler`, `BusinessException`)
3. 메소드 실행 시간 로깅 (`@LogExecutionTime`)

## 설치 방법

### Gradle

```gradle
dependencies {
    implementation 'com.core:core-common:0.0.1-SNAPSHOT'
}
```

### Maven

```xml
<dependency>
    <groupId>com.core</groupId>
    <artifactId>core-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## 사용 예시

### 1. 공통 응답 처리

```java
@RestController
public class SampleController {
    
    @GetMapping("/sample")
    public ApiResponse<String> sample() {
        return ApiResponse.success("Hello World");
    }
    
    @GetMapping("/sample-with-message")
    public ApiResponse<String> sampleWithMessage() {
        return ApiResponse.success("성공적으로 처리되었습니다.", "Hello World");
    }
    
    @GetMapping("/error-sample")
    public ApiResponse<Void> errorSample() {
        return ApiResponse.error("에러가 발생했습니다.");
    }
}
```

### 2. 전역 예외 처리

```java
@Service
public class SampleService {
    
    public void throwBusinessException() {
        throw new BusinessException("비즈니스 로직 오류가 발생했습니다.");
    }
    
    public void throwBusinessExceptionWithCode() {
        throw new BusinessException("INVALID_INPUT", "잘못된 입력값입니다.");
    }
}
```

### 3. 메소드 실행 시간 로깅

```java
@Service
public class SampleService {
    
    @LogExecutionTime("데이터 조회 시간")
    public List<Data> getData() {
        // 데이터 조회 로직
        return dataList;
    }
    
    @LogExecutionTime
    public void processData() {
        // 데이터 처리 로직
    }
}
```

## 응답 형식

### 성공 응답
```json
{
    "success": true,
    "message": "성공적으로 처리되었습니다.",
    "data": {
        // 응답 데이터
    }
}
```

### 에러 응답
```json
{
    "success": false,
    "message": "에러 메시지",
    "data": null
}
```

## 주의사항

1. Spring Boot 3.4 이상 버전에서 사용 가능합니다.
2. Java 17 이상이 필요합니다.
3. Lombok이 필요하므로 IDE에 Lombok 플러그인이 설치되어 있어야 합니다.
