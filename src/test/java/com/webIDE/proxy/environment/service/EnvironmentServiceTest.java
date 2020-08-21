package com.webIDE.proxy.environment.service;

import com.webIDE.proxy.environment.model.Environment;
import com.webIDE.proxy.environment.repository.EnvironmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EnvironmentServiceTest {

    @InjectMocks
    private EnvironmentService environmentService;

    @Mock
    private EnvironmentRepository environmentRepository;

    @Mock
    private Environment environment;

    @Test
    public void test_createEnvironment() {

        //given
        Environment expected = Environment.builder()
                .name("PATH")
                .value("$PATH:HOME/bin:/usr/app/")
                .projectId("testProject")
                .build();

        given(environmentRepository.save(expected)).willReturn(expected);

        //when
        Environment actual = environmentService.createEnvironment(expected);


        //then
        assertThat(actual).isEqualTo(expected);

        verify(environmentRepository, times(1)).save(expected);
    }

    @Test
    public void test_updateEnvironment() {

        //given
        String name = "testPATH"

        Environment old = Environment.builder()
                .name(name)
                .value("old PATH")
                .projectId("testProject")
                .build();

        Environment expected = Environment.builder()
                .name(name)
                .value("new PATH")
                .projectId("testProject")
                .build();

        given(environmentRepository.findByName(name)).willReturn(old);
        given(environment.update(expected)).willReturn(expected);

        //when
        Environment actual = environmentService.updateEnvironment(name, expected);

        //then
        assertThat(actual).isEqualTo(expected);

        verify(environment, times(1)).update(expected);
    }

    @Test
    public void test_getEnvironments() {
        //given
        String projectId = "testId";

        Environment expected1 = Environment.builder()
                .name("PATH")
                .value("$PATH:HOME/bin:/usr/app/")
                .projectId(projectId)
                .build();

        Environment expected2 = Environment.builder()
                .name("DOMAIN")
                .value("https://123.345.678.99")
                .projectId(projectId)
                .build();

        List<Environment> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);

        given(environmentRepository.findAllByProjectId(projectId)).willReturn(expected);

        //when
        List<Environment> actual = environmentService.getEnvironments(projectId);

        //then
        assertThat(actual).isEqualTo(expected);

        verify(environmentRepository, times(1)).findAllByProjectId(projectId);
    }

}