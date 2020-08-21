package com.webIDE.proxy.environment.controller;

import com.webIDE.proxy.environment.model.Environment;
import com.webIDE.proxy.environment.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/env")
public class EnvironmentController {

    @Autowired
    private EnvironmentService environmentService;

    public EnvironmentController() {}

    @PostMapping(value = "", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Environment createEnvironment(@RequestBody Environment environment) {

        return environmentService.createEnvironment(environment);
    }

    @PutMapping(value = "/{name}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Environment updateEnvironment(@PathVariable String name, @RequestBody Environment environment) {
        return environmentService.updateEnvironment(name, environment);
    }

    @GetMapping(value = "" ,produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Environment> getEnvironments(@RequestParam String projectId) {
        return environmentService.getEnvironments(projectId);
    }

}
