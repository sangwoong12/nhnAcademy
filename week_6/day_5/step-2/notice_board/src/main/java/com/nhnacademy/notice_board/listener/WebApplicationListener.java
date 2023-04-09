package com.nhnacademy.notice_board.listener;

import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.item.post.PostImpl;
import com.nhnacademy.notice_board.item.user.Admin;
import com.nhnacademy.notice_board.item.user.GeneralUser;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.post.MemoryPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PostRepository postRepository = new MemoryPostRepository();
        UserRepository userRepository = new MemoryUserRepository();

        //TODO 1 : 특별한 사용자 ServletContext 초기화 시점에 등록
        User admin = Admin.builder()
                        .id("admin")
                        .password("12345")
                        .name("관리자").build();
        userRepository.add(admin);
        ServletContext sc = sce.getServletContext();

        testData(postRepository, userRepository);

        sc.setAttribute("userRepository",userRepository);
        sc.setAttribute("postRepository",postRepository);
        sc.setAttribute("count",0);
    }

    private static void testData(PostRepository postRepository, UserRepository userRepository) {
        User user = GeneralUser.builder().id("user").password("12345").name("일반 유저").build();
        User user1 = GeneralUser.builder().id("이병헌").password("12345").name("이병헌").build();
        User user2 = GeneralUser.builder().id("장동건").password("12345").name("장동건").build();
        User user3 = GeneralUser.builder().id("송강호").password("12345").name("송강호").build();
        User user4 = GeneralUser.builder().id("황정민").password("12345").name("황정민").build();
        User user5 = GeneralUser.builder().id("곽철용").password("12345").name("곽철용").build();
        User user6 = GeneralUser.builder().id("문동은").password("12345").name("문동은").build();

        userRepository.add(user);
        userRepository.add(user1);
        userRepository.add(user2);
        userRepository.add(user3);
        userRepository.add(user4);
        userRepository.add(user5);
        userRepository.add(user6);

        Post post = new PostImpl("너,너","어두운 방 안에서 혼자 있는 나 그리고 하 </br> 네가 불러주지 않아도 난 괜찮아 난 괜찮아","user");
        Post post1 = new PostImpl("달콤한 인생","어느 깊은 가을밤, 잠에서 깨어난 제자가 울고 있었다.</br> 그 모습을 본 스승이 기이하게 여겨 제자에게 물었다.</br> '무서운 꿈을 꾸었느냐?'</br> 아닙니다, 달콤한 꿈을 꾸었습니다.</br> '그런데 왜 그리 슬피 우느냐?'</br> 제자는 눈믈을 닦아내며 나지막히 말했다.</br> '그 꿈은 이루어질수 없기 때문입니다.' ","이병헌");
        Post post2 = new PostImpl("변호인","대한민국 주권은 국민에게 있고 모든 권력은 국민으로부터 나온다.</br> 국가란 국민입니다.","송강호");
        Post post3 = new PostImpl("친구","니가가라 하와이..","장동건");
        Post post4 = new PostImpl("수리남","나대지 말라고, 어딜 불경스럽게! 이게 아직도 깡패짓거리를 하고 있네?</br> 야 이 삐삐야. 아직 회개가 덜 됐구만?","황정민");
        Post post5 = new PostImpl("더글로리","우리 같이 천천히 말라 죽어 보자, 연진아.","문동은");
        Post post6 = new PostImpl("더글로리","매일 생각했어 연진아","문동은");
        Post post7 = new PostImpl("타짜","\"나 깡패 아니다. 나도 적금 붓고 보험들고 살고 그런다.\" </br> 노래 그만해! 이 새끼야!</br>\"화란아, 나도 순정이 있다. 니가 이런 식으로 내 순정을 짓밟으면은, 마 그때는 깡패가 되는 거야!\"</br>\"내가 널 깡패처럼 납치라도 하랴? 앉어!\"","곽철용");
        Post post8 = new PostImpl("타짜","\"어이 젊은 친구, 신사답게 행동해. 보름 후에 다시 와.\"","곽철용");
        Post post9 = new PostImpl("타짜","\"묻고 더블로 가!\"","곽철용");
        Post post10 = new PostImpl("타짜","\"마포대교는 무너졌냐? 이 삐삐야?\"","곽철용");
        Post post11 = new PostImpl("타짜","\"카메라도 안 되고.. 약도 안 되고.. 이 안에 배신자가 있다. 이게 내 결론이다. 내 돈 어딨어? 아~ 잘 모르시지?\"","곽철용");

        postRepository.register(post);
        postRepository.register(post1);
        postRepository.register(post2);
        postRepository.register(post3);
        postRepository.register(post4);
        postRepository.register(post5);
        postRepository.register(post6);
        postRepository.register(post7);
        postRepository.register(post8);
        postRepository.register(post9);
        postRepository.register(post10);
        postRepository.register(post11);
    }
}
