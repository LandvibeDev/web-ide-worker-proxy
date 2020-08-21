package com.webIDE.proxy.environment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webIDE.proxy.environment.model.Environment;
import com.webIDE.proxy.environment.service.EnvironmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EnvironmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private EnvironmentService environmentService;

    @Mock
    private EnvironmentController environmentController;

    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(environmentController).build();
    }

    @Test
    void test_createEnvironment() throws Exception {

        //given
        Environment expected = Environment.builder()
                .name("PATH")
                .value("$PATH:HOME/bin:/usr/app/")
                .projectId("testProject")
                .build();


        String body = objectMapper.writeValueAsString(expected);

        given(environmentService.createEnvironment(expected)).willReturn(expected);

        //when,then
        mockMvc.perform(post("/env")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(body))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(environmentService,times(1)).createEnvironment(expected);
    }

    @Test
    void test_updateEnvironment() throws Exception {

        //given
        String name = "PATH";
        Environment expected = Environment.builder()
                .name(name)
                .value("$PATH:HOME/bin:/usr/app/")
                .projectId("testProject")
                .build();


        String body = objectMapper.writeValueAsString(expected);

        given(environmentService.updateEnvironment(name,expected)).willReturn(expected);

        //when.then
        mockMvc.perform(put("/env/{name}", name)
                .content(objectMapper.writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(body))
                .andExpect(status().isOk())
                .andDo(print());

        verify(environmentService,times(1)).updateEnvironment(name,expected);

    }

    @Test
    void test_getEnvironments() throws Exception {

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

        String body = objectMapper.writeValueAsString(expected);

        given(environmentService.getEnvironments(projectId)).willReturn(expected);

        //when,then
        mockMvc.perform(get("/env").queryParam(projectId))
                .andExpect(content().string(body))
                .andExpect(status().isOk())
                .andDo(print());

        verify(environmentService,times(1)).getEnvironments(projectId);
    }

}