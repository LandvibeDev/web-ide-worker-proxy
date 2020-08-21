package com.webIDE.proxy.environment.repository;

import com.webIDE.proxy.environment.model.Environment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnvironmentRepository{

    List<Environment> findAllByProjectId(String projectId);

    Environment findByName(String name);
}
