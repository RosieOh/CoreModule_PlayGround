package com.core.common.model.modules;

import java.util.Arrays;

import com.core.common.model.ModuleInfo;

public class LoggingModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Logging",
            Arrays.asList(
                "AOP 기반 로깅",
                "메서드 실행 시간 측정",
                "예외 로깅",
                "로그 레벨 관리",
                "로그 포맷팅",
                "구조화된 로깅",
                "로그 분석"
            ),
            """
            // 1. AOP 기반 로깅
            @Aspect
            @Component
            public class LoggingAspect {
                private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
                
                @Around("@annotation(Loggable)")
                public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
                    long startTime = System.currentTimeMillis();
                    Object result = joinPoint.proceed();
                    long endTime = System.currentTimeMillis();
                    
                    logger.info("Method: {} executed in {}ms",
                        joinPoint.getSignature().getName(),
                        endTime - startTime);
                        
                    return result;
                }
            }
            
            // 2. 구조화된 로깅
            @Component
            public class StructuredLogger {
                private static final Logger logger = LoggerFactory.getLogger(StructuredLogger.class);
                
                public void logEvent(String eventType, Map<String, Object> data) {
                    MDC.put("eventType", eventType);
                    data.forEach(MDC::put);
                    
                    logger.info("Event occurred");
                    
                    MDC.clear();
                }
            }
            
            // 3. 예외 로깅
            @ExceptionHandler(Exception.class)
            public ResponseEntity<?> handleException(Exception e) {
                logger.error("Error occurred: ", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            
            // 4. 로그 분석
            @Service
            public class LogAnalysisService {
                public List<LogEntry> analyzeLogs(String logFile, LocalDateTime startTime, LocalDateTime endTime) {
                    return Files.lines(Paths.get(logFile))
                        .filter(line -> isWithinTimeRange(line, startTime, endTime))
                        .map(this::parseLogEntry)
                        .collect(Collectors.toList());
                }
            }
            """,
            """
            # Logging 설정
            logging.level.root=INFO
            logging.level.com.core=DEBUG
            logging.pattern=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
            logging.file.name=logs/application.log
            logging.file.max-size=10MB
            logging.file.max-history=30
            logging.structured.enabled=true
            logging.analysis.enabled=true
            logging.retention.days=90
            """,
            Arrays.asList("audit"),
            Arrays.asList(
                "로그 레벨은 환경에 따라 적절히 설정해야 합니다.",
                "민감한 정보는 로그에 포함되지 않도록 주의해야 합니다.",
                "로그 파일은 정기적으로 아카이빙하고 관리해야 합니다.",
                "성능에 영향을 주지 않도록 로깅 패턴을 최적화해야 합니다.",
                "분산 환경에서는 중앙화된 로그 수집 시스템을 고려해야 합니다.",
                "구조화된 로깅을 통해 로그 분석을 용이하게 해야 합니다.",
                "로그 보관 기간은 법적 요구사항을 고려하여 설정해야 합니다."
            )
        );
    }
} 