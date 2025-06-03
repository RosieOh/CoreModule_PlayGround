package com.core.common.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 주요 기능:
 * - 모듈 소개 페이지 제공
 * - 각 모듈별 상세 설명 페이지 제공
 * - 예제 코드 및 사용법 안내
 * 
 * @author core
 */
@Controller
public class ModuleGuideController {

    private final Map<String, ModuleInfo> moduleInfoMap;

    public ModuleGuideController() {
        this.moduleInfoMap = Map.of(
            "security", new ModuleInfo(
                "Security",
                Arrays.asList(
                    "JWT 기반 인증/인가",
                    "비밀번호 암호화",
                    "보안 설정 관리"
                ),
                """
                @Autowired
                private JwtTokenProvider jwtTokenProvider;
                
                // 토큰 생성
                String token = jwtTokenProvider.generateToken(username);
                
                // 토큰 검증
                if (jwtTokenProvider.validateToken(token)) {
                    String username = jwtTokenProvider.getUsernameFromToken(token);
                }
                """,
                """
                # Security 설정
                security.jwt.secret=your-secret-key
                security.jwt.expiration=86400000
                """,
                Arrays.asList("validation", "audit"),
                Arrays.asList(
                    "JWT 시크릿 키는 안전하게 관리해야 합니다.",
                    "토큰 만료 시간은 적절히 설정해야 합니다."
                )
            ),
            "validation", new ModuleInfo(
                "Validation",
                Arrays.asList(
                    "입력값 유효성 검증",
                    "커스텀 유효성 검증 규칙",
                    "에러 메시지 관리"
                ),
                """
                @Autowired
                private ValidationUtil validationUtil;
                
                // 이메일 유효성 검증
                if (validationUtil.isValidEmail(email)) {
                    // 처리 로직
                }
                
                // 전화번호 유효성 검증
                if (validationUtil.isValidPhoneNumber(phoneNumber)) {
                    // 처리 로직
                }
                """,
                """
                # Validation 설정
                validation.email.pattern=^[A-Za-z0-9+_.-]+@(.+)$
                validation.phone.pattern=^\\d{3}-\\d{3,4}-\\d{4}$
                """,
                Arrays.asList("security", "message"),
                Arrays.asList(
                    "정규식 패턴은 성능을 고려하여 최적화해야 합니다.",
                    "유효성 검증 실패 시 적절한 에러 메시지를 제공해야 합니다."
                )
            ),
            "logging", new ModuleInfo(
                "Logging",
                Arrays.asList(
                    "AOP 기반 로깅",
                    "메서드 실행 시간 측정",
                    "예외 로깅"
                ),
                """
                @Loggable
                public void someMethod() {
                    // 메서드 내용
                }
                
                @Loggable(level = "ERROR")
                public void errorMethod() {
                    // 에러 발생 가능한 메서드
                }
                """,
                """
                # Logging 설정
                logging.level.root=INFO
                logging.level.com.core=DEBUG
                logging.pattern=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
                """,
                Arrays.asList("audit"),
                Arrays.asList(
                    "로그 레벨은 환경에 따라 적절히 설정해야 합니다.",
                    "민감한 정보는 로그에 포함되지 않도록 주의해야 합니다."
                )
            ),
            "cache", new ModuleInfo(
                "Cache",
                Arrays.asList(
                    "메모리 캐시 관리",
                    "캐시 만료 정책",
                    "캐시 통계"
                ),
                """
                @Autowired
                private CacheManager cacheManager;
                
                // 캐시에 데이터 저장
                cacheManager.put("key", value);
                
                // 캐시에서 데이터 조회
                Object value = cacheManager.get("key");
                """,
                """
                # Cache 설정
                cache.type=memory
                cache.expiration=3600
                cache.max-size=1000
                """,
                Arrays.asList("logging"),
                Arrays.asList(
                    "캐시 크기는 메모리 사용량을 고려하여 설정해야 합니다.",
                    "캐시 만료 시간은 데이터 특성에 맞게 설정해야 합니다."
                )
            ),
            "audit", new ModuleInfo(
                "Audit",
                Arrays.asList(
                    "엔티티 변경 이력 추적",
                    "작업자 정보 기록",
                    "변경 시간 기록"
                ),
                """
                @Entity
                public class User extends AuditEntity {
                    // 엔티티 필드
                }
                
                // 변경 이력 자동 기록
                userRepository.save(user);
                """,
                """
                # Audit 설정
                audit.enabled=true
                audit.track-changes=true
                audit.track-user=true
                """,
                Arrays.asList("security", "logging"),
                Arrays.asList(
                    "감사 로그는 장기 보관이 필요한 경우 별도 저장소를 사용해야 합니다.",
                    "감사 정보는 수정이 불가능하도록 해야 합니다."
                )
            ),
            "message", new ModuleInfo(
                "Message",
                Arrays.asList(
                    "다국어 메시지 관리",
                    "메시지 포맷팅",
                    "기본 메시지 처리"
                ),
                """
                @Autowired
                private MessageUtil messageUtil;
                
                // 메시지 조회
                String message = messageUtil.getMessage("error.not.found");
                
                // 파라미터가 있는 메시지
                String message = messageUtil.getMessage("welcome", "John");
                """,
                """
                # Message 설정
                spring.messages.basename=messages
                spring.messages.encoding=UTF-8
                spring.messages.cache-duration=3600
                """,
                Arrays.asList("validation"),
                Arrays.asList(
                    "메시지 키는 일관된 네이밍 규칙을 따라야 합니다.",
                    "다국어 지원이 필요한 경우 messages_ko.properties 등을 추가해야 합니다."
                )
            ),
            "file", new ModuleInfo(
                "File",
                Arrays.asList(
                    "파일 업로드/다운로드",
                    "파일 유효성 검증",
                    "파일 저장소 관리"
                ),
                """
                @Autowired
                private FileUtil fileUtil;
                
                // 파일 저장
                String savedPath = fileUtil.saveFile(multipartFile);
                
                // 파일 삭제
                fileUtil.deleteFile(filePath);
                """,
                """
                # File 설정
                file.upload-dir=./uploads
                file.max-size=10485760
                file.allowed-types=jpg,png,pdf
                """,
                Arrays.asList("validation", "security"),
                Arrays.asList(
                    "파일 업로드 크기 제한을 적절히 설정해야 합니다.",
                    "허용된 파일 타입을 명확히 지정해야 합니다."
                )
            ),
            "scheduler", new ModuleInfo(
                "Scheduler",
                Arrays.asList(
                    "작업 스케줄링",
                    "주기적 작업 실행",
                    "지연 작업 실행"
                ),
                """
                @Scheduled(cron = "0 0 * * * *")
                public void hourlyTask() {
                    // 매시 정각에 실행
                }
                
                @Scheduled(fixedDelay = 300000)
                public void periodicTask() {
                    // 5분마다 실행
                }
                """,
                """
                # Scheduler 설정
                spring.task.scheduling.pool.size=5
                spring.task.scheduling.thread-name-prefix=scheduled-task-
                """,
                Arrays.asList("logging"),
                Arrays.asList(
                    "스케줄러 스레드 풀 크기는 시스템 리소스를 고려하여 설정해야 합니다.",
                    "장기 실행 작업은 비동기로 처리해야 합니다."
                )
            )
        );
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Core Module Guide");
        model.addAttribute("welcomeMessage", "Core Module Guide에 오신 것을 환영합니다.");
        return "index";
    }

    @GetMapping("/modules")
    public String modules(Model model) {
        model.addAttribute("modules", moduleInfoMap.keySet());
        return "modules";
    }

    @GetMapping("/modules/{name}")
    public String moduleDetail(@PathVariable String name, Model model) {
        ModuleInfo moduleInfo = moduleInfoMap.get(name);
        if (moduleInfo == null) {
            return "redirect:/modules";
        }

        model.addAttribute("moduleName", moduleInfo.getName());
        model.addAttribute("features", moduleInfo.getFeatures());
        model.addAttribute("usageExample", moduleInfo.getUsageExample());
        model.addAttribute("configuration", moduleInfo.getConfiguration());
        model.addAttribute("relatedModules", moduleInfo.getRelatedModules());
        model.addAttribute("notes", moduleInfo.getNotes());

        return "module-detail";
    }

    private static class ModuleInfo {
        private final String name;
        private final List<String> features;
        private final String usageExample;
        private final String configuration;
        private final List<String> relatedModules;
        private final List<String> notes;

        public ModuleInfo(String name, List<String> features, String usageExample,
                         String configuration, List<String> relatedModules, List<String> notes) {
            this.name = name;
            this.features = features;
            this.usageExample = usageExample;
            this.configuration = configuration;
            this.relatedModules = relatedModules;
            this.notes = notes;
        }

        public String getName() {
            return name;
        }

        public List<String> getFeatures() {
            return features;
        }

        public String getUsageExample() {
            return usageExample;
        }

        public String getConfiguration() {
            return configuration;
        }

        public List<String> getRelatedModules() {
            return relatedModules;
        }

        public List<String> getNotes() {
            return notes;
        }
    }
} 