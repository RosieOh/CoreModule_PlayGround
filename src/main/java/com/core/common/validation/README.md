# Validation Module

이 모듈은 데이터 검증과 관련된 기능을 제공합니다.

## 주요 기능

### 1. ValidationUtil
- 이메일 유효성 검사
- 전화번호 유효성 검사
- 비밀번호 유효성 검사
- BindingResult 에러 처리
- 문자열 유효성 검사

### 2. 커스텀 어노테이션
- @PhoneNumber: 전화번호 형식 검증

## 사용 방법

### ValidationUtil 사용
```java
@Autowired
private ValidationUtil validationUtil;

// 이메일 검증
boolean isValidEmail = validationUtil.isValidEmail("test@example.com");

// 전화번호 검증
boolean isValidPhone = validationUtil.isValidPhoneNumber("010-1234-5678");

// 비밀번호 검증
boolean isValidPassword = validationUtil.isValidPassword("Password123!");

// BindingResult 에러 처리
Map<String, String> errors = validationUtil.getValidationErrors(bindingResult);
```

### 커스텀 어노테이션 사용
```java
public class UserDto {
    @PhoneNumber
    private String phoneNumber;
    
    // getter, setter
}
```

## 검증 규칙

### 이메일
- RFC 5322 표준을 따르는 이메일 형식
- 예: user@domain.com

### 전화번호
- 형식: XXX-XXXX-XXXX
- 예: 010-1234-5678

### 비밀번호
- 최소 8자, 최대 20자
- 대문자 포함
- 소문자 포함
- 숫자 포함
- 특수문자 포함

## 주의사항

1. null 값 처리는 @NotNull 어노테이션과 함께 사용해야 합니다.
2. 비밀번호 정책은 보안 요구사항에 따라 조정할 수 있습니다.
3. 전화번호 형식은 국가/지역에 따라 다를 수 있으므로 필요에 따라 수정해야 합니다. 