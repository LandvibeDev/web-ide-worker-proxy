package com.webIDE.proxy.container;

import com.webIDE.proxy.container.model.Container;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/containers")
public class ContainerController {

    public ContainerController() {}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Container getContainer(@PathVariable int id) {
        Container container1 = Container.builder()
                .id(1)
                .name("sample project")
                .description("running")
                .type("nodejs:12")
                .build();

        return container1;
    }

}
