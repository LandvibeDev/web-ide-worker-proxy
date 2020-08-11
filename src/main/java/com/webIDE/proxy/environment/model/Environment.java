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

    @Builder
    public Environment(String name, String value) {
        this.name = name;
        this.value = value;
    }


}
