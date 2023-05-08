package com.nhnacademy.board.controller;

import com.nhnacademy.board.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LogoutControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private LogoutController logoutController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(logoutController).build();
    }

    @Test
    void logout() throws Exception {
        Cookie cookie = new Cookie("SESSION", "test");
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("id","test1");

        mockHttpSession.setAttribute("id", "test");
        mockMvc.perform(post("/logout").cookie(cookie).session(mockHttpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}