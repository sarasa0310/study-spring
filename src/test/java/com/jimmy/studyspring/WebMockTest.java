package com.jimmy.studyspring;

import com.jimmy.studyspring.greeting.GreetingController;
import com.jimmy.studyspring.greeting.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GreetingService service;

    @Test
    void greetingShouldReturnMessageFromService() throws Exception {
        // given
        when(service.greet())
                .thenReturn("Hello, Mock");

        // when
        ResultActions actions =
                mvc.perform(
                        get("/greeting"))
                        .andDo(print());

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock")));
    }

}
