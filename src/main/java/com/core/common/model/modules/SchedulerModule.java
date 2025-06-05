package com.core.common.model.modules;

import java.util.Arrays;

import com.core.common.model.ModuleInfo;

public class SchedulerModule {
    public static ModuleInfo getInfo() {
        return new ModuleInfo(
            "Scheduler",
            Arrays.asList(
                "작업 스케줄링",
                "작업 상태 모니터링",
                "재시도 정책",
                "작업 우선순위",
                "작업 의존성 관리",
                "분산 작업 처리",
                "작업 이력 관리"
            ),
            """
            // 1. 스케줄러 설정
            @Configuration
            @EnableScheduling
            public class SchedulerConfig {
                @Bean
                public TaskScheduler taskScheduler() {
                    ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
                    scheduler.setPoolSize(5);
                    scheduler.setThreadNamePrefix("scheduler-");
                    return scheduler;
                }
            }
            
            // 2. 스케줄된 작업
            @Component
            public class ScheduledTasks {
                @Scheduled(fixedRate = 60000)
                public void reportCurrentTime() {
                    log.info("Current time: {}", LocalDateTime.now());
                }
                
                @Scheduled(cron = "0 0 12 * * ?")
                public void dailyTask() {
                    // 매일 정오에 실행되는 작업
                }
            }
            
            // 3. 작업 재시도
            @Service
            public class RetryableTaskService {
                @Retryable(
                    value = {Exception.class},
                    maxAttempts = 3,
                    backoff = @Backoff(delay = 1000)
                )
                public void executeTask() {
                    // 재시도 가능한 작업
                }
                
                @Recover
                public void recover(Exception e) {
                    // 재시도 실패 후 복구 작업
                }
            }
            
            // 4. 작업 의존성
            @Service
            public class DependentTaskService {
                @Async
                public CompletableFuture<Result> task1() {
                    return CompletableFuture.supplyAsync(() -> {
                        // 첫 번째 작업
                        return new Result();
                    });
                }
                
                @Async
                public CompletableFuture<Result> task2(Result result1) {
                    return CompletableFuture.supplyAsync(() -> {
                        // 두 번째 작업 (task1의 결과에 의존)
                        return new Result();
                    });
                }
            }
            
            // 5. 작업 모니터링
            @Service
            public class TaskMonitorService {
                public TaskStatus getTaskStatus(String taskId) {
                    return TaskStatus.builder()
                        .taskId(taskId)
                        .status(getCurrentStatus(taskId))
                        .lastExecutionTime(getLastExecutionTime(taskId))
                        .nextExecutionTime(getNextExecutionTime(taskId))
                        .executionCount(getExecutionCount(taskId))
                        .build();
                }
            }
            """,
            """
            # Scheduler 설정
            scheduler.pool-size=5
            scheduler.thread-name-prefix=scheduler-
            scheduler.retry.max-attempts=3
            scheduler.retry.delay=1000
            scheduler.monitor.enabled=true
            scheduler.history.enabled=true
            scheduler.history.retention-days=30
            scheduler.distributed.enabled=false
            scheduler.priority.enabled=true
            scheduler.dependency.enabled=true
            """,
            Arrays.asList("logging", "audit"),
            Arrays.asList(
                "스케줄러 스레드 풀 크기는 시스템 리소스를 고려하여 설정해야 합니다.",
                "작업 재시도 정책은 비즈니스 요구사항에 맞게 설정해야 합니다.",
                "작업 의존성은 순환 참조를 피하도록 설계해야 합니다.",
                "작업 모니터링은 실시간으로 수행해야 합니다.",
                "분산 환경에서는 작업 중복 실행을 방지해야 합니다.",
                "작업 우선순위는 비즈니스 중요도에 따라 설정해야 합니다.",
                "작업 이력은 문제 해결을 위해 보관해야 합니다."
            )
        );
    }
} 