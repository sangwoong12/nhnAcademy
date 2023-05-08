package com.nhnacademy.board.controller;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.config.WebConfig;
import com.nhnacademy.board.exception.PermissionException;
import com.nhnacademy.board.filter.AuthCheckFilter;
import com.nhnacademy.board.filter.LoginCheckFilter;
import com.nhnacademy.board.filter.ViewCountFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.Cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy(value = {
        @ContextConfiguration(classes = {RootConfig.class}),
        @ContextConfiguration(classes = {WebConfig.class}),
})
@Transactional
class WebApplicationContextTest {
    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8"))
                .addFilter(new AuthCheckFilter())
                .addFilter(new LoginCheckFilter())
                .addFilter(new ViewCountFilter())
                .build();
    }

    //adminController
    @Test
    void homeTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/home"));
    }

    @Test
    @DisplayName("pageNum 이 없을때 동작 여부")
    void getUserListByPageNumTest() throws Exception {
        // when
        mockMvc.perform(get("/admin/user-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-list"));
    }

    @Test
    void getUserListByPageNumTest2() throws Exception {
        // when
        mockMvc.perform(get("/admin/user-list?pageNum=2"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-list"));
    }

    @Test
    void getEditFormTest() throws Exception {
        mockMvc.perform(get("/admin/edit-user/test1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-edit"));
    }

    @Test
    void getViewUserByIdTest() throws Exception {
        mockMvc.perform(get("/admin/view-user/test1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-view"));
    }

    @Test
    void getAddUserFormTest() throws Exception {
        mockMvc.perform(get("/admin/add-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-add"));
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(get("/admin/delete-user/test11"))
                .andExpect(status().isFound()) //redirect == 302
                .andExpect(view().name("redirect:/admin/user-list"));
    }

    //loginController
    @Test
    void loginFormTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("login 입력값이 없을 때 다시 로그인 폼")
    void loginTest() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(status().isOk()) //redirect == 302
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("admin 이 로그인 할때")
    void loginTest2() throws Exception {
        mockMvc.perform(post("/login")
                        .param("userId", "admin")
                        .param("userPassword", "12345"))
                .andExpect(status().isFound()) //redirect == 302
                .andExpect(view().name("redirect:/admin"));
    }

    @Test
    @DisplayName("user 이 로그인 할때")
    void loginTest3() throws Exception {
        mockMvc.perform(post("/login")
                        .param("userId", "test1")
                        .param("userPassword", "12345"))
                .andExpect(status().isFound()) //redirect == 302
                .andExpect(view().name("redirect:/user"));
    }

    @Test
    @DisplayName("user 이 로그인실패 할때")
    void loginTest4() throws Exception {
        mockMvc.perform(post("/login")
                        .param("userId", "id70")
                        .param("userPassword", "123456"))
                .andExpect(status().isFound()) //redirect == 302
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    @DisplayName("logout")
    void logoutTest() throws Exception {
        mockMvc.perform(post("/logout"))
                .andExpect(status().isFound()) //redirect == 302
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    void userHomeTest() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/home"));
    }

    @Test
    @DisplayName("postList pageNum 이 없을때 동작 여부")
    void getPostListByPageNumTest() throws Exception {
        mockMvc.perform(get("/user/post-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-list"));
    }

    @Test
    @DisplayName("postList pageNum 이 없을때 동작 여부")
    void getPostListByPageNumTest2() throws Exception {
        mockMvc.perform(get("/user/post-list?pageNum=1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-list"));
    }

    @Test
    void getPostTest() throws Exception {
        Cookie cookie = new Cookie("id","test1");
        mockMvc.perform(get("/user/view-post?postId=1").cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-view"));
    }

    @Test
    @DisplayName("getPost filter 에서 count 를 보냈다는 과정하에 cookie 가 생성되는지 확인")
    void getPostTest2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/user/view-post")
                .param("postId", "1")
                .requestAttr("count", true) // 필터에서 처리되었다고 가정
                .cookie(new Cookie("1", "1")); // 쿠키 추가

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-view"))
                .andExpect(cookie().value("1", "1"));//쿠키 확인
    }

    @Test
    void deletePostTest() throws Exception {
        mockMvc.perform(get("/user/delete-post?postId=2"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/post-list"));
    }

    @Test
    void getAddPostForm() throws Exception {
        mockMvc.perform(get("/user/add-post"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-add-form"));
    }

    @Test
    @DisplayName("addPost session에 id가 없을 때 실패여부")
    void addPostTest() throws Exception {
        try {
            mockMvc.perform(post("/user/add-post")
                    .param("title", "test")
                    .param("content", "test"));
        } catch (NestedServletException e) {
            //then
            assertTrue(e.getCause() instanceof PermissionException);
        }
    }

    @Test
    @DisplayName("addPost session에 id가 있을 때 성공여부")
    void addPostTest2() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "test1");

        mockMvc.perform(post("/user/add-post")
                        .session(session)
                        .param("title", "test")
                        .param("content", "test"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/post-list"));
    }

    @Test
    @DisplayName("getEditPost session에 id가 없을 때 실패여부")
    void getEditPostFormTest() throws Exception {
        try {
            mockMvc.perform(get("/user/edit-post"));
        } catch (NestedServletException e) {
            //then
            assertTrue(e.getCause() instanceof PermissionException);
        }
    }

    @Test
    @DisplayName("getEditPost session에 id가 있을 때 성공여부")
    void getEditPostFormTest2() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "test1");

        mockMvc.perform(get("/user/edit-post?postId=3")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-edit-form"));

    }

    @Test
    @DisplayName("editPostTest session에 id가 있을 때 성공여부")
    void editPostTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "test1");

        mockMvc.perform(post("/user/edit-post?postId=3")
                        .session(session)
                        .param("title", "test")
                        .param("content", "test")
                        .param("id", "3"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/user/view-post?postId=3"));
    }

    @Test
    @DisplayName("editPostTest session에 id가 없을 때 실패여부")
    void editPostTest2() throws Exception {
        try {
            mockMvc.perform(post("/user/edit-post?postId=3")
                    .param("title", "test")
                    .param("content", "test")
                    .param("id", "3"));
        } catch (NestedServletException e) {
            //then
            assertTrue(e.getCause() instanceof PermissionException);
        }
    }

    @Test
    void viewUserTest() throws Exception {
        mockMvc.perform(get("/user/view-user?userId=test1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/view-user"));
    }

    @Test
    @DisplayName("profile 사진없이 추가 테스트")
    void addProfileTest() throws Exception {
        mockMvc.perform(post("/profile")
                        .param("id", "test100")
                        .param("name", "테스터1")
                        .param("password", "12345"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/admin/view-user/test100"));
    }

    @Test
    @DisplayName("profile 사진 추가 테스트")
    void addProfileTest2() throws Exception {
        byte[] content = "test image".getBytes();
        MockMultipartFile image = new MockMultipartFile(
                "image", "test.jpeg", "image/jpeg", content
        );

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/profile")
                .file(image)
                .param("id", "fileTest")
                .param("name", "fileTest")
                .param("password", "12345");
        mockMvc.perform(builder)
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/admin/view-user/fileTest"));
    }

    @Test
    @DisplayName("profile 사진 조회 테스트")
    void getProfileTest() throws Exception{
        mockMvc.perform(get("/profile?id=test1"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("profile 사진 추가 테스트")
    void editProfileTest() throws Exception {
        byte[] content = "test image".getBytes();
        MockMultipartFile image = new MockMultipartFile(
                "image", "test.jpeg", "image/jpeg", content
        );

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/edit-user")
                .file(image)
                .param("id", "test1")
                .param("name", "test")
                .param("password", "12345");
        mockMvc.perform(builder)
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/admin/view-user/test1"));
    }
    @Test
    @DisplayName("로그인 실패 테스트 (bindingResult)")
    void login() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login/loginForm"));
    }

    @Test
    @DisplayName("admin 로그인 테스트")
    void login2() throws Exception {
        mockMvc.perform(post("/login")
                        .queryParam("userId", "admin")
                        .queryParam("userPassword", "12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"));
    }

    @Test
    @DisplayName("user 로그인 테스트")
    void login3() throws Exception {
        mockMvc.perform(post("/login")
                        .queryParam("userId", "test1")
                        .queryParam("userPassword", "12345"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    @DisplayName("로그인 실패 테스트 (password error)")
    void login4() throws Exception {
        mockMvc.perform(post("/login")
                        .queryParam("userId", "test1")
                        .queryParam("userPassword", "123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void logout() throws Exception {
        Cookie cookie = new Cookie("SESSION", "test");
        MockHttpSession mockHttpSession = new MockHttpSession();

        mockHttpSession.setAttribute("id", "test");
        mockMvc.perform(post("/logout").cookie(cookie))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
