package com.webIDE.proxy.file.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class File {

    private int id;

    private String name;

    private String type;

    private int permission;

    private String contents;

    private int parentId;

    @Builder
    public File(int id, String name, String type, int permission, String contents, int parentId) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.contents = contents;
        this.parentId = parentId;
    }
}
