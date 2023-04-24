package com.nhnacademy.board.springmvcboard.filter;

import com.nhnacademy.notice_board.item.user.Auth;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 관리자 : 홈, 사용자 목록, 사용자 추가 , 사용자 정보 수정 , 사용자 삭제, 어드민용 사용자 조회 화면
 * 일반유저 : 홈, 게시물 목록, 게시물 조회, 게시물 수정, 게시물 삭제, 일반 유저용 사용자 조회 화면
 */
@WebFilter(
        filterName = "AuthCheckFilter",
        urlPatterns = {
                 "/user-add.do", "/admin/user-add.jsp"
                , "/edit-user-info.do", "/admin/edit-user-info.jsp"
                , "/delete-user.do"
                , "/user-list.do", "/admin/user-list.jsp"
                , "/admin-home.do", "/admin/admin-home.jsp"
                , "/user-view.do", "/admin/user-view.jsp"
        }
)
@Slf4j
public class AuthCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserRepository userRepository = (UserRepository)servletRequest.getServletContext().getAttribute("userRepository");

        HttpServletRequest req = (HttpServletRequest)  servletRequest;
        String id  = (String) req.getSession().getAttribute("id");
        //로그인 유저가 아니기 때문에 로그인
        if(id == null){
            RequestDispatcher rd = servletRequest.getRequestDispatcher("/login.do");
            rd.forward(servletRequest,servletResponse);
        }
        else{
            User user = userRepository.getUser(id);
            log.info("user : {}",user.getName() );
            if (user.getAuth().equals(Auth.ADMIN)) {
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                RequestDispatcher rd = servletRequest.getRequestDispatcher("/permission-error.do");
                rd.forward(servletRequest,servletResponse);
            }
        }
    }
}
