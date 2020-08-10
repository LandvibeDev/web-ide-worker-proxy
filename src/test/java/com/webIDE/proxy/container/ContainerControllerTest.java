package com.webIDE.proxy.container;

import com.webIDE.proxy.container.model.Container;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getContainer() throws Exception {

        Container container1 = new Container(1,"sample project","sample","running","nodejs:12");

        mockMvc.perform(get("/containers/{id}",1))
                .andExpect(status().isOk())
                .andDo(print());

    }
}