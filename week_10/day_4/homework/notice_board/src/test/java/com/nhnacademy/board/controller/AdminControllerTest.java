package com.nhnacademy.board.controller;

import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.PageImpl;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.item.user.UserImpl;
import com.nhnacademy.board.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class AdminControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void home() throws Exception {
        // when
        mockMvc.perform(get("/admin"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("admin/home"));
    }

    @Test
    void getUserListByPageNum() throws Exception {
        // given
        int pageNum = 2;
        int size = 10;
        Page<User> pagedUsers = new PageImpl<>(pageNum, size, 1, 1, new ArrayList<>());
        given(userService.getPagedUsers(pageNum, size)).willReturn(pagedUsers);

        // when
        mockMvc.perform(get("/admin/user-list").param("pageNum", String.valueOf(pageNum)))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-list"))
                .andExpect(model().attribute("pagedUsers", pagedUsers));
    }

    @Test
    void getEditForm() throws Exception {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);

        given(userService.getUser(userId)).willReturn(user);

        // when
        mockMvc.perform(get("/admin/edit-user/{userId}", userId))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-edit"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    void getViewUserById() throws Exception {
        // given
        String userId = "test";
        User user = new UserImpl(userId, "tester", "12345", null);

        given(userService.getUser(userId)).willReturn(user);

        // when
        mockMvc.perform(get("/admin/view-user/{userId}", userId))
                //then
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-view"))
                .andExpect(model().attribute("user", user));
    }

    @Test
    void getAddUserForm() throws Exception {
        // when
        mockMvc.perform(get("/admin/add-user"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user-add"))
                .andExpect(model().attributeExists("userRequest"));
    }

    @Test
    void deleteUser() throws Exception {
        // given
        String userId = "test";

        // when
        mockMvc.perform(get("/admin/delete-user/{userId}", userId))
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/user-list"));

        verify(userService).deleteUser(userId);
    }
}