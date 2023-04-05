package com.nhnacademy.hello.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.nio.charset.StandardCharsets;

@WebListener @Slf4j
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String counterFilterName = servletContext.getInitParameter("counterFilterName");
        String counterFilePath = "/WEB-INF/classes/" + counterFilterName;
        String realFilePath = servletContext.getRealPath(counterFilePath);
        log.info(realFilePath);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(realFilePath), StandardCharsets.UTF_8))){
            long count = Integer.parseInt(br.readLine());
            sce.getServletContext().setAttribute("counter",count);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("contextInitialized 종료");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.error("contextDestroyed 호출");
        System.out.println("contextDestroyed 호출");
        ServletContext servletContext = sce.getServletContext();
        System.out.println("servletContext = " + servletContext);
        String counterFilterName = servletContext.getInitParameter("counterFilterName");
        System.out.println("counterFilterName = " + counterFilterName);
        String counterFilePath = "/WEB-INF/classes/" + counterFilterName;
        System.out.println("counterFilePath = " + counterFilePath);
        String realFilePath = servletContext.getRealPath(counterFilePath);
        System.out.println("realFilePath = " + realFilePath);
        try(
                FileOutputStream fileOutputStream = new FileOutputStream(realFilePath);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,StandardCharsets.UTF_8);
                BufferedWriter fw = new BufferedWriter(outputStreamWriter);
        ){
            fw.write( String.valueOf(servletContext.getAttribute("counter")));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try(BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(realFilePath),StandardCharsets.UTF_8))) {
//            String count = (String) servletContext.getAttribute("counter");
//            log.error(count);
//            System.out.println("count :"+count);
//            br.write(count);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


}
