//package com.core.common.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 모듈별 학습을 위한 컨트롤러
// */
//@Controller
//@RequestMapping("/modules")
//public class ModuleLearningController {
//
//    private final Map<String, ModuleData> modules = new HashMap<>();
//
//    public ModuleLearningController() {
//        initializeModules();
//    }
//
//    /**
//     * 모듈별 학습 페이지로 이동
//     */
//    @GetMapping("/{moduleName}")
//    public String learnModule(@PathVariable String moduleName, Model model) {
//        ModuleData module = modules.get(moduleName);
//        if (module == null) {
//            return "redirect:/";
//        }
//        model.addAttribute("module", module);
//        return "modules/learn";
//    }
//
//    /**
//     * 학습 단계 완료 처리
//     */
//    @PostMapping("/{moduleName}/steps/{stepId}/complete")
//    @ResponseBody
//    public Map<String, Object> completeStep(
//            @PathVariable String moduleName,
//            @PathVariable String stepId) {
//        ModuleData module = modules.get(moduleName);
//        if (module != null) {
//            module.completeStep(stepId);
//            Map<String, Object> response = new HashMap<>();
//            response.put("success", true);
//            response.put("progress", module.getProgress());
//            return response;
//        }
//        return Map.of("success", false);
//    }
//
//    /**
//     * 모듈 데이터 초기화
//     */
//    private void initializeModules() {
//        // Security Module
//        ModuleData security = new ModuleData("Security Module",
//            "JWT 기반 인증/인가, 비밀번호 암호화 등 보안 관련 기능");
//        security.addGoal("JWT 토큰 기반 인증 구현");
//        security.addGoal("Spring Security 설정 및 커스터마이징");
//        security.addGoal("비밀번호 암호화 및 보안");
//        security.addStep("JWT 토큰 생성 및 검증", "JWT 토큰의 구조와 생성 방법을 학습합니다.");
//        security.addStep("Spring Security 설정", "Spring Security 기본 설정과 커스터마이징 방법을 학습합니다.");
//        security.addStep("비밀번호 암호화", "BCrypt를 사용한 비밀번호 암호화 방법을 학습합니다.");
//        modules.put("security", security);
//
//        // Validation Module
//        ModuleData validation = new ModuleData("Validation Module",
//            "입력값 유효성 검증, 커스텀 검증 규칙 등");
//        validation.addGoal("기본 유효성 검증 구현");
//        validation.addGoal("커스텀 검증 규칙 작성");
//        validation.addStep("기본 유효성 검증", "Spring Validation 기본 어노테이션 사용법을 학습합니다.");
//        validation.addStep("커스텀 검증 규칙", "커스텀 검증 규칙 작성 방법을 학습합니다.");
//        modules.put("validation", validation);
//
//        // Logging Module
//        ModuleData logging = new ModuleData("Logging Module",
//            "AOP 기반 로깅, 메서드 실행 시간 측정 등");
//        logging.addGoal("AOP를 활용한 로깅 구현");
//        logging.addGoal("메서드 실행 시간 측정");
//        logging.addStep("AOP 로깅 설정", "AOP를 사용한 로깅 설정 방법을 학습합니다.");
//        logging.addStep("실행 시간 측정", "메서드 실행 시간을 측정하는 방법을 학습합니다.");
//        modules.put("logging", logging);
//
//        // Cache Module
//        ModuleData cache = new ModuleData("Cache Module",
//            "메모리 캐시 관리, 캐시 만료 정책 등");
//        cache.addGoal("캐시 매니저 설정");
//        cache.addGoal("캐시 정책 구현");
//        cache.addStep("캐시 매니저 설정", "Spring Cache 설정 방법을 학습합니다.");
//        cache.addStep("캐시 정책", "캐시 만료 정책과 갱신 전략을 학습합니다.");
//        modules.put("cache", cache);
//
//        // Audit Module
//        ModuleData audit = new ModuleData("Audit Module",
//            "엔티티 변경 이력 추적, 작업자 정보 기록 등");
//        audit.addGoal("엔티티 변경 이력 추적");
//        audit.addGoal("작업자 정보 기록");
//        audit.addStep("엔티티 변경 추적", "JPA Auditing을 사용한 엔티티 변경 추적 방법을 학습합니다.");
//        audit.addStep("작업자 정보 기록", "작업자 정보를 자동으로 기록하는 방법을 학습합니다.");
//        modules.put("audit", audit);
//
//        // Message Module
//        ModuleData message = new ModuleData("Message Module",
//            "다국어 메시지 관리, 메시지 포맷팅 등");
//        message.addGoal("다국어 메시지 관리");
//        message.addGoal("메시지 포맷팅");
//        message.addStep("다국어 설정", "MessageSource를 사용한 다국어 설정 방법을 학습합니다.");
//        message.addStep("메시지 포맷팅", "메시지 포맷팅과 파라미터 처리 방법을 학습합니다.");
//        modules.put("message", message);
//
//        // File Module
//        ModuleData file = new ModuleData("File Module",
//            "파일 업로드/다운로드, 파일 유효성 검증 등");
//        file.addGoal("파일 업로드 구현");
//        file.addGoal("파일 다운로드 구현");
//        file.addStep("파일 업로드", "MultipartFile을 사용한 파일 업로드 방법을 학습합니다.");
//        file.addStep("파일 다운로드", "파일 다운로드 구현 방법을 학습합니다.");
//        modules.put("file", file);
//
//        // Scheduler Module
//        ModuleData scheduler = new ModuleData("Scheduler Module",
//            "작업 스케줄링, 주기적 작업 실행 등");
//        scheduler.addGoal("스케줄러 설정");
//        scheduler.addGoal("주기적 작업 구현");
//        scheduler.addStep("스케줄러 설정", "Spring Scheduler 설정 방법을 학습합니다.");
//        scheduler.addStep("주기적 작업", "Cron 표현식을 사용한 주기적 작업 설정 방법을 학습합니다.");
//        modules.put("scheduler", scheduler);
//    }
//
//    /**
//     * 모듈 데이터를 담는 내부 클래스
//     */
//    private static class ModuleData {
//        private final String name;
//        private final String description;
//        private final List<String> goals = new ArrayList<>();
//        private final List<Step> steps = new ArrayList<>();
//
//        public ModuleData(String name, String description) {
//            this.name = name;
//            this.description = description;
//        }
//
//        public void addGoal(String goal) {
//            goals.add(goal);
//        }
//
//        public void addStep(String title, String description) {
//            steps.add(new Step(UUID.randomUUID().toString(), title, description));
//        }
//
//        public void completeStep(String stepId) {
//            steps.stream()
//                .filter(step -> step.id.equals(stepId))
//                .findFirst()
//                .ifPresent(step -> step.completed = true);
//        }
//
//        public String getProgress() {
//            if (steps.isEmpty()) return "0";
//            long completedCount = steps.stream().filter(step -> step.completed).count();
//            return String.valueOf((completedCount * 100) / steps.size());
//        }
//
//        // Getters
//        public String getName() { return name; }
//        public String getDescription() { return description; }
//        public List<String> getGoals() { return goals; }
//        public List<Step> getSteps() { return steps; }
//    }
//
//    /**
//     * 학습 단계를 담는 내부 클래스
//     */
//    private static class Step {
//        private final String id;
//        private final String title;
//        private final String description;
//        private boolean completed;
//
//        public Step(String id, String title, String description) {
//            this.id = id;
//            this.title = title;
//            this.description = description;
//            this.completed = false;
//        }
//
//        // Getters
//        public String getId() { return id; }
//        public String getTitle() { return title; }
//        public String getDescription() { return description; }
//        public boolean isCompleted() { return completed; }
//    }
//}