package com.core.domain.controller.moduleGuide;

import java.util.HashMap;
import java.util.Map;

import com.core.domain.model.moduleCore.ModuleInfo;
import com.core.domain.model.moduleCore.modules.SecurityModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.domain.model.moduleCore.modules.AuditModule;
import com.core.domain.model.moduleCore.modules.CacheModule;
import com.core.domain.model.moduleCore.modules.FileModule;
import com.core.domain.model.moduleCore.modules.LoggingModule;
import com.core.domain.model.moduleCore.modules.MessageModule;
import com.core.domain.model.moduleCore.modules.SchedulerModule;
import com.core.domain.model.moduleCore.modules.ValidationModule;

/**
 * 주요 기능:
 * - 모듈 소개 페이지 제공
 * - 각 모듈별 상세 설명 페이지 제공
 * - 예제 코드 및 사용법 안내
 * 
 * @author core
 */
@Controller
@RequestMapping("/modules")
public class ModuleGuideController {

    private final Map<String, ModuleInfo> moduleInfoMap;

    public ModuleGuideController() {
        this.moduleInfoMap = new HashMap<>();
        moduleInfoMap.put("security", SecurityModule.getInfo());
        moduleInfoMap.put("validation", ValidationModule.getInfo());
        moduleInfoMap.put("logging", LoggingModule.getInfo());
        moduleInfoMap.put("cache", CacheModule.getInfo());
        moduleInfoMap.put("audit", AuditModule.getInfo());
        moduleInfoMap.put("message", MessageModule.getInfo());
        moduleInfoMap.put("file", FileModule.getInfo());
        moduleInfoMap.put("scheduler", SchedulerModule.getInfo());
    }

    @GetMapping
    public String modules(Model model) {
        model.addAttribute("modules", moduleInfoMap);
        return "modules/list";
    }

    @GetMapping("/{name}")
    public String moduleDetail(@PathVariable String name, Model model) {
        ModuleInfo moduleInfo = moduleInfoMap.get(name.toLowerCase());
        if (moduleInfo == null) {
            return "redirect:/modules";
        }

        model.addAttribute("module", moduleInfo);
        return "modules/detail";
    }
} 