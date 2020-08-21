package com.webIDE.proxy.environment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Environment {

    private String name;

    private String value;

    private String projectId;

    @Builder
    public Environment(String name, String value, String projectId) {
        this.name = name;
        this.value = value;
        this.projectId = projectId;
    }

    public Environment update(Environment environment) {
        this.name = name;
        this.value = value;
        this.projectId = projectId;

        return this;
    }

}
