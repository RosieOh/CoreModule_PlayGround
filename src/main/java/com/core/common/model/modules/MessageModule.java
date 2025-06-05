package com.core.common.model.modules;

import java.util.Arrays;

import com.core.common.model.ModuleInfo;

public class MessageModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Message",
            Arrays.asList(
                "다국어 메시지 관리",
                "메시지 그룹화",
                "메시지 템플릿",
                "메시지 검증",
                "메시지 포맷팅",
                "메시지 우선순위",
                "메시지 이력 관리"
            ),
            """
            // 1. 메시지 소스 설정
            @Configuration
            public class MessageConfig {
                @Bean
                public MessageSource messageSource() {
                    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
                    messageSource.setBasenames("classpath:messages");
                    messageSource.setDefaultEncoding("UTF-8");
                    return messageSource;
                }
                
                @Bean
                public LocaleResolver localeResolver() {
                    SessionLocaleResolver resolver = new SessionLocaleResolver();
                    resolver.setDefaultLocale(Locale.KOREAN);
                    return resolver;
                }
            }
            
            // 2. 메시지 사용
            @Service
            public class MessageService {
                @Autowired
                private MessageSource messageSource;
                
                public String getMessage(String code, Object[] args, Locale locale) {
                    return messageSource.getMessage(code, args, locale);
                }
            }
            
            // 3. 메시지 템플릿
            @Service
            public class MessageTemplateService {
                public String processTemplate(String template, Map<String, Object> variables) {
                    return templateEngine.process(template, new Context() {{
                        setVariables(variables);
                    }});
                }
            }
            
            // 4. 메시지 검증
            @Service
            public class MessageValidationService {
                public ValidationResult validateMessage(Message message) {
                    return ValidationResult.builder()
                        .isValid(validateContent(message.getContent()))
                        .isFormatValid(validateFormat(message.getFormat()))
                        .isLengthValid(validateLength(message.getContent()))
                        .build();
                }
            }
            
            // 5. 메시지 이력 관리
            @Service
            public class MessageHistoryService {
                public void saveMessageHistory(Message message) {
                    MessageHistory history = MessageHistory.builder()
                        .messageId(message.getId())
                        .content(message.getContent())
                        .locale(message.getLocale())
                        .modifiedBy(SecurityContextHolder.getContext().getAuthentication().getName())
                        .modifiedAt(LocalDateTime.now())
                        .build();
                    
                    messageHistoryRepository.save(history);
                }
            }
            """,
            """
            # Message 설정
            message.default-locale=ko_KR
            message.cache-seconds=3600
            message.encoding=UTF-8
            message.template.enabled=true
            message.validation.enabled=true
            message.history.enabled=true
            message.history.retention-days=90
            message.priority.enabled=true
            message.format.default=HTML
            """,
            Arrays.asList("validation"),
            Arrays.asList(
                "메시지는 일관된 형식과 스타일을 유지해야 합니다.",
                "다국어 메시지는 문화적 차이를 고려해야 합니다.",
                "메시지 템플릿은 재사용성을 고려하여 설계해야 합니다.",
                "메시지 검증은 보안과 품질을 보장해야 합니다.",
                "메시지 이력은 변경 추적을 위해 보관해야 합니다.",
                "메시지 우선순위는 비즈니스 중요도에 따라 설정해야 합니다.",
                "메시지 포맷팅은 일관성 있게 적용해야 합니다."
            )
        );
    }
} 