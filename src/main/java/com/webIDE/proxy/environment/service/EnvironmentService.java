package com.webIDE.proxy.environment.service;

import com.webIDE.proxy.environment.model.Environment;
import com.webIDE.proxy.environment.repository.EnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {

    @Autowired
    private EnvironmentRepository environmentRepository;

    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    public Environment createEnvironment(Environment environment) {
        return environmentRepository.save(environment);
    }

    public Environment updateEnvironment(String name, Environment environment) {

        Environment environment1 = environmentRepository.findByName(name);

        return environment1.update(environment);
    }

    public List<Environment> getEnvironments(String projectId) {
        return environmentRepository.findAllByProjectId(projectId);
    }
}