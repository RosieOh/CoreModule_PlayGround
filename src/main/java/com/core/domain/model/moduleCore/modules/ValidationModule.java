package com.core.domain.model.moduleCore.modules;

import java.util.Arrays;

import com.core.domain.model.moduleCore.ModuleInfo;

public class ValidationModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Validation",
            Arrays.asList(
                "입력값 유효성 검증",
                "커스텀 유효성 검증 규칙",
                "에러 메시지 관리",
                "그룹 기반 검증",
                "크로스 필드 검증",
                "계층적 검증",
                "동적 검증 규칙"
            ),
            """
            // 1. 기본 유효성 검증
            public class UserDTO {
                @NotNull(message = "이름은 필수입니다")
                @Size(min = 2, max = 50, message = "이름은 2~50자 사이여야 합니다")
                private String name;
                
                @Email(message = "올바른 이메일 형식이 아닙니다")
                private String email;
                
                @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다")
                private String phone;
            }
            
            // 2. 커스텀 검증 규칙
            @Constraint(validatedBy = PasswordStrengthValidator.class)
            @Target({ElementType.FIELD})
            @Retention(RetentionPolicy.RUNTIME)
            public @interface PasswordStrength {
                String message() default "비밀번호는 8자 이상이며, 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다";
                Class<?>[] groups() default {};
                Class<? extends Payload>[] payload() default {};
            }
            
            // 3. 그룹 기반 검증
            public interface ValidationGroups {
                interface Create {}
                interface Update {}
            }
            
            public class UserDTO {
                @NotNull(groups = ValidationGroups.Create.class)
                private String username;
                
                @NotNull(groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
                private String email;
            }
            
            // 4. 크로스 필드 검증
            @Target({ElementType.TYPE})
            @Retention(RetentionPolicy.RUNTIME)
            @Constraint(validatedBy = PasswordMatchValidator.class)
            public @interface PasswordMatch {
                String message() default "비밀번호가 일치하지 않습니다";
                Class<?>[] groups() default {};
                Class<? extends Payload>[] payload() default {};
            }
            
            // 5. 동적 검증 규칙
            @Service
            public class DynamicValidationService {
                public void validateWithRules(Object target, List<ValidationRule> rules) {
                    for (ValidationRule rule : rules) {
                        if (!rule.validate(target)) {
                            throw new ValidationException(rule.getErrorMessage());
                        }
                    }
                }
            }
            """,
            """
            # Validation 설정
            validation.email.pattern=^[A-Za-z0-9+_.-]+@(.+)$
            validation.phone.pattern=^\\d{3}-\\d{3,4}-\\d{4}$
            validation.password.min-length=8
            validation.password.require-special=true
            validation.password.require-number=true
            validation.groups.enabled=true
            validation.cross-field.enabled=true
            validation.dynamic-rules.enabled=true
            """,
            Arrays.asList("security", "message"),
            Arrays.asList(
                "정규식 패턴은 성능을 고려하여 최적화해야 합니다.",
                "유효성 검증 실패 시 적절한 에러 메시지를 제공해야 합니다.",
                "검증 규칙은 비즈니스 요구사항에 맞게 커스터마이징해야 합니다.",
                "크로스 필드 검증은 별도의 커스텀 검증기를 구현해야 합니다.",
                "검증 로직은 서비스 계층에서도 추가로 수행하는 것이 좋습니다.",
                "그룹 기반 검증은 상황에 따라 적절히 활용해야 합니다.",
                "동적 검증 규칙은 유연성을 고려하여 설계해야 합니다."
            )
        );
    }
} 