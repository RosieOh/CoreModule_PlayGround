package com.core.domain.model.moduleCore;

import java.util.List;

public class ModuleInfo {
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