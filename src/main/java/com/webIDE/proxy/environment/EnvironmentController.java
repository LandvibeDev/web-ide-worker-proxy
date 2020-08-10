package com.webIDE.proxy.environment;

import com.webIDE.proxy.environment.model.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project/{pid}/env")
public class EnvironmentController {

    public EnvironmentController() {}

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Environment> createEnvironment(@RequestParam String name, @RequestParam String value) {
        Environment environment1 = new Environment("PATH", "$PATH:HOME/bin:/usr/app/");
        Environment environment2 = new Environment("DOMAIN1", " https://123.345.678.99");

        List<Environment> listEnvironment = new ArrayList<>();
        listEnvironment.add(environment1);
        listEnvironment.add(environment2);


        return listEnvironment;

    }




}
