package com.webIDE.proxy.file;

import com.webIDE.proxy.file.model.File;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/{pid}/files")
public class FileController {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public File createFile() {
        File file1 = File.builder()
                .name("test1.js")
                .type("file")
                .permission(777)
                .contents("${BASE64_ENCODED_STRING}")
                .parentId(1)
                .build();

        return file1;

    }

}
