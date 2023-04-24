package com.nhnacademy.notice_board.listener;

import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.item.post.PostImpl;
import com.nhnacademy.notice_board.item.user.UserImpl;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.post.JsonPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.JsonUserRepository;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PostRepository postRepository = new JsonPostRepository();
        UserRepository userRepository = new JsonUserRepository();

        //TODO 1 : 특별한 사용자 ServletContext 초기화 시점에 등록
        User admin = new UserImpl("admin","관리자","12345",null,"ADMIN");

        userRepository.add(admin);

        ServletContext sc = sce.getServletContext();

        testData(postRepository, userRepository);

        sc.setAttribute("userRepository",userRepository);
        sc.setAttribute("postRepository",postRepository);
        sc.setAttribute("count",0);
    }

    private static void testData(PostRepository postRepository, UserRepository userRepository) {
        User user = new UserImpl("user","일반 유저","12345","user.jpeg","USER");
        User user1 = new UserImpl("이병헌","이병헌","12345","이병헌.jpeg","USER");
        User user2 = new UserImpl("장동건","장동건","12345","장동건.jpeg","USER");
        User user3 = new UserImpl("송강호","송강호","12345","송강호.jpeg","USER");
        User user4 = new UserImpl("문동은","문동은","12345","문동은.jpeg","USER");
        User user5 = new UserImpl("황정민","황정민","12345","황정민.jpeg","USER");
        User user6 = new UserImpl("곽철용","곽철용","12345","곽철용.jpeg","USER");
        userRepository.add(user);
        userRepository.add(user1);
        userRepository.add(user2);
        userRepository.add(user3);
        userRepository.add(user4);
        userRepository.add(user5);
        userRepository.add(user6);

        Post post = new PostImpl(1,"너,너","어두운 방 안에서 혼자 있는 나 그리고 하 </br> 네가 불러주지 않아도 난 괜찮아 난 괜찮아","user", LocalDateTime.now(),0);
        Post post1 = new PostImpl(2,"달콤한 인생","어느 깊은 가을밤, 잠에서 깨어난 제자가 울고 있었다.</br> 그 모습을 본 스승이 기이하게 여겨 제자에게 물었다.</br> '무서운 꿈을 꾸었느냐?'</br> 아닙니다, 달콤한 꿈을 꾸었습니다.</br> '그런데 왜 그리 슬피 우느냐?'</br> 제자는 눈믈을 닦아내며 나지막히 말했다.</br> '그 꿈은 이루어질수 없기 때문입니다.' ","이병헌",LocalDateTime.now(),0);
        Post post2 = new PostImpl(3,"변호인","대한민국 주권은 국민에게 있고 모든 권력은 국민으로부터 나온다.</br> 국가란 국민입니다.","송강호",LocalDateTime.now(),0);
        Post post3 = new PostImpl(4,"친구","니가가라 하와이..","장동건",LocalDateTime.now(),0);
        Post post4 = new PostImpl(5,"수리남","나대지 말라고, 어딜 불경스럽게! 이게 아직도 깡패짓거리를 하고 있네?</br> 야 이 삐삐야. 아직 회개가 덜 됐구만?","황정민",LocalDateTime.now(),0);
        Post post5 = new PostImpl(6,"더글로리","우리 같이 천천히 말라 죽어 보자, 연진아.","문동은",LocalDateTime.now(),0);
        Post post6 = new PostImpl(7,"더글로리","매일 생각했어 연진아","문동은",LocalDateTime.now(),0);
        Post post7 = new PostImpl(8,"타짜","\"나 깡패 아니다. 나도 적금 붓고 보험들고 살고 그런다.\" </br> 노래 그만해! 이 새끼야!</br>\"화란아, 나도 순정이 있다. 니가 이런 식으로 내 순정을 짓밟으면은, 마 그때는 깡패가 되는 거야!\"</br>\"내가 널 깡패처럼 납치라도 하랴? 앉어!\"","곽철용",LocalDateTime.now(),0);
        Post post8 = new PostImpl(9,"타짜","\"어이 젊은 친구, 신사답게 행동해. 보름 후에 다시 와.\"","곽철용",LocalDateTime.now(),0);
        Post post9 = new PostImpl(10,"타짜","\"묻고 더블로 가!\"","곽철용",LocalDateTime.now(),0);
        Post post10 = new PostImpl(11,"타짜","\"마포대교는 무너졌냐? 이 삐삐야?\"","곽철용",LocalDateTime.now(),0);
        Post post11 = new PostImpl(12,"타짜","\"카메라도 안 되고.. 약도 안 되고.. 이 안에 배신자가 있다. 이게 내 결론이다. 내 돈 어딨어? 아~ 잘 모르시지?\"","곽철용",LocalDateTime.now(),0);

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
