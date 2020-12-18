package com.example.storehouse;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = StorehouseApplication.class)
@AutoConfigureMockMvc
class StorehouseApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MockMvc mvc;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }

    @Test
    @SneakyThrows
    void shouldHaveHealthCheckHandlerAndCheckIsUP() {
        mvc.perform(get("/actuator/health"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", equalTo("UP")))
        ;
    }

}
