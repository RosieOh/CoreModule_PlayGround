package com.core.domain.model.moduleCore.modules;

import java.util.Arrays;

import com.core.domain.model.moduleCore.ModuleInfo;

public class AuditModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Audit",
            Arrays.asList(
                "데이터 변경 추적",
                "감사 로그 관리",
                "감사 정책 설정",
                "감사 보고서 생성",
                "감사 이벤트 처리",
                "감사 데이터 보관",
                "감사 데이터 분석"
            ),
            """
            // 1. 감사 엔티티 설정
            @Entity
            @EntityListeners(AuditingEntityListener.class)
            public class User {
                @Id
                private Long id;
                
                @CreatedBy
                private String createdBy;
                
                @LastModifiedBy
                private String lastModifiedBy;
                
                @CreatedDate
                private LocalDateTime createdDate;
                
                @LastModifiedDate
                private LocalDateTime lastModifiedDate;
            }
            
            // 2. 감사 설정
            @Configuration
            @EnableJpaAuditing
            public class AuditConfig {
                @Bean
                public AuditorAware<String> auditorProvider() {
                    return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                        .map(SecurityContext::getAuthentication)
                        .map(Authentication::getName);
                }
            }
            
            // 3. 감사 로그 생성
            @Aspect
            @Component
            public class AuditLogAspect {
                @Around("@annotation(audited)")
                public Object logAudit(ProceedingJoinPoint joinPoint, Audited audited) {
                    AuditLog log = new AuditLog();
                    log.setAction(audited.action());
                    log.setEntity(joinPoint.getTarget().getClass().getSimpleName());
                    log.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
                    log.setTimestamp(LocalDateTime.now());
                    
                    Object result = joinPoint.proceed();
                    
                    log.setResult("SUCCESS");
                    auditLogRepository.save(log);
                    return result;
                }
            }
            
            // 4. 감사 보고서 생성
            @Service
            public class AuditReportService {
                public Report generateAuditReport(LocalDateTime startDate, LocalDateTime endDate) {
                    List<AuditLog> logs = auditLogRepository
                        .findByTimestampBetween(startDate, endDate);
                    
                    return Report.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .totalActions(logs.size())
                        .actionsByUser(groupByUser(logs))
                        .actionsByType(groupByType(logs))
                        .build();
                }
            }
            
            // 5. 감사 데이터 분석
            @Service
            public class AuditAnalysisService {
                public AnalysisResult analyzeAuditData(String entityType) {
                    List<AuditLog> logs = auditLogRepository.findByEntity(entityType);
                    
                    return AnalysisResult.builder()
                        .totalChanges(logs.size())
                        .changesByUser(calculateChangesByUser(logs))
                        .changesByAction(calculateChangesByAction(logs))
                        .riskScore(calculateRiskScore(logs))
                        .build();
                }
            }
            """,
            """
            # Audit 설정
            audit.enabled=true
            audit.retention-days=365
            audit.log-level=INFO
            audit.report.enabled=true
            audit.report.schedule=0 0 0 * * *
            audit.analysis.enabled=true
            audit.analysis.batch-size=1000
            audit.storage.type=database
            audit.export.enabled=true
            audit.export.format=CSV
            """,
            Arrays.asList("logging", "security"),
            Arrays.asList(
                "감사 로그는 법적 요구사항에 맞게 보관해야 합니다.",
                "감사 데이터는 무결성을 보장해야 합니다.",
                "감사 정책은 비즈니스 요구사항에 맞게 설정해야 합니다.",
                "감사 보고서는 정기적으로 생성하고 검토해야 합니다.",
                "감사 데이터 분석을 통해 보안 위험을 조기에 발견해야 합니다.",
                "감사 이벤트는 실시간으로 모니터링해야 합니다.",
                "감사 데이터는 안전하게 보관하고 접근을 제한해야 합니다."
            )
        );
    }
} 