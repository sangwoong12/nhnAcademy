package com.nhnacademy.notice_board.listener;

import com.nhnacademy.notice_board.repository.post.PostRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@WebListener
public class VisitorsCountListener implements ServletContextListener {


    static ClassLoader classLoader = PostRepository.class.getClassLoader();
    private static final String FILE_PATH = classLoader.getResource("/").getPath();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String path = FILE_PATH + "visitorsCount.dat";
        File file = new File(path);

        if (!file.exists()) { // 파일이 존재하지 않을 때
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))){
            String s = br.readLine();
            if(Objects.isNull(s)){
                sce.getServletContext().setAttribute("visitorsCount",0);
            } else {
                long count = Integer.parseInt(s);
                sce.getServletContext().setAttribute("visitorsCount",count);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String path = FILE_PATH + "visitorsCount.dat";
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,StandardCharsets.UTF_8);
                BufferedWriter fw = new BufferedWriter(outputStreamWriter);
        ){
            fw.write( String.valueOf(servletContext.getAttribute("visitorsCount")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
