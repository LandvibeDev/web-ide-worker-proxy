package com.webIDE.proxy.container.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Container {

    private int id;

    private String name;

    private String description;

    private String status;

    private String type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime accessedAt;

    @Builder
    public Container(int id, String name, String description, String status, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accessedAt = accessedAt;
    }


}
