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

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        Environment environment = Environment.builder()
                .name("PATH")
                .value("$PATH:HOME/bin:/usr/app/")
                .build();


        String body = objectMapper.writeValueAsString(environment);

        given(environmentService.createEnvironment(environment)).willReturn(environment);

        mockMvc.perform(post("/env")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(body))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(environmentService,times(1)).createEnvironment(environment);
    }

    void test_updateEnvironment() throws Exception {

        String name = "PATH";
        Environment environment = Environment.builder()
                .name(name)
                .value("$PATH:HOME/bin:/usr/app/")
                .build();


        String body = objectMapper.writeValueAsString(environment);

        given(environmentService.updateEnvironment(environment)).willReturn((List<Environment>) environment);

        mockMvc.perform(post("/env/{name}", name)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(body))
                .andExpect(status().isOk())
                .andDo(print());

        verify(environmentService,times(1)).updateEnvironment(environment);

    }

}