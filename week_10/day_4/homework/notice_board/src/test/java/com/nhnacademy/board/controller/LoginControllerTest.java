package com.nhnacademy.board.controller;

import com.nhnacademy.board.domain.LoginRequest;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.item.user.UserImpl;
import com.nhnacademy.board.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LoginControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    LoginController loginController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    @DisplayName("로그인 상태가 아닐때 테스트")
    void loginForm() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/login"))
                // Then
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"))
                .andExpect(model().attributeExists("loginRequest"));
    }

    @Test
    @DisplayName("admin 일경우 redirect 테스트")
    void loginForm2() throws Exception {
        // given
        String userId = "admin";
        String name = "admin";
        User user = new UserImpl(userId, name, "12345", null);
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("id", userId);
        mockHttpSession.setAttribute("name", name);

        // When
        when(userService.getUser(user.getId())).thenReturn(user);

        // Then
        mockMvc.perform(get("/login").cookie(new Cookie("SESSION", session.getId())).session(mockHttpSession))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/admin"));
    }

    @Test
    @DisplayName("admin 아닐경우 redirect 테스트")
    void loginForm3() throws Exception {
        // given
        String userId = "user";
        String name = "user";
        User user = new UserImpl(userId, name, "12345", null);
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("id", userId);
        mockHttpSession.setAttribute("name", name);

        // When
        when(userService.getUser(user.getId())).thenReturn(user);

        // Then
        mockMvc.perform(get("/login").cookie(new Cookie("SESSION", session.getId())).session(mockHttpSession))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user"));
    }

    //    @Test
    void login() throws Exception {
        // TODO BindingResult 에서 막힌다. 이것을 해결할려면 통합테스트 아니여도 가능한지 찾아보기.
        // boot에서는 WebMWebMvcTest 로 가능한거 같음.

        // given
        String userId = "user";
        String name = "user";
        String password = "12345";
        User user = new UserImpl(userId, name, password, null);

        LoginRequest loginRequest = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest, "userId", userId);
        ReflectionTestUtils.setField(loginRequest, "userPassword", password);
        // When
        when(userService.existUser(loginRequest)).thenReturn(true);
        when(userService.getUser(loginRequest.getUserId())).thenReturn(user);

        // Then
        mockMvc.perform(post("/login")
                        .queryParam("userId", userId)
                        .queryParam("userPassword", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }
}