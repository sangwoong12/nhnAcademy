package com.nhnacademy.notice_board.servlet;

import com.nhnacademy.notice_board.item.user.GeneralUser;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name = "profileServlet" , urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    static ClassLoader classLoader = PostRepository.class.getClassLoader();
    private static final String UPLOAD_DIR = classLoader.getResource("/image").getPath();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 이미지 파일 경로 설정
        String id = req.getParameter("id");
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");
        User user = userRepository.getUser(id);
        if(user.getProfileFileName() != null){
            String filePath = UPLOAD_DIR +"/" + user.getProfileFileName();

            // 이미지 파일 읽기
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);

            // 클라이언트로 이미지 전송
            byte[] buffer = new byte[1024];
            int read = 0;
            resp.setContentType("image/jpeg");
            while ((read = fis.read(buffer)) != -1) {
                resp.getOutputStream().write(buffer, 0, read);
            }
            fis.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String> userInfoMap = new HashMap<>();
        Part profilePart = null;
        for(Part part : req.getParts()){
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
            if (contentDisposition.contains("filename=")) {
                profilePart = part;
            } else {
                String formValue = req.getParameter(part.getName());
                userInfoMap.put(part.getName(),formValue);
            }
        }
        //필수값 여부
        if(userInfoMap.get("id") == null || userInfoMap.get("name") == null || userInfoMap.get("password") == null){
            throw new RuntimeException("id, name, password 는 필수 값입니다.");
        }
        if (profilePart.getSize() > 0) {
            //유저마다 구분하기 id + .jpg 로 등록 확장자는 jpeg 로 제한.
            profilePart.write(UPLOAD_DIR + File.separator + userInfoMap.get("id") + ".jpeg");
            profilePart.delete();
            userInfoMap.put("profileFileName",userInfoMap.get("id") + ".jpeg");

        } else {
            userInfoMap.put("profileFileName",null);
        }
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        if(userRepository.existById(userInfoMap.get("id"))){
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        User user = new GeneralUser(userInfoMap.get("id"),userInfoMap.get("name"),userInfoMap.get("password"),userInfoMap.get("profileFileName"));
        userRepository.add(user);

        resp.sendRedirect("/user-list.do");
    }
}
