package com.nhnacademy.hello;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        location = "/Users/sangwoong/Documents/nhnAcademy/week_6/day_2/class/source/file/temp"
)
@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/sangwoong/Documents/nhnAcademy/week_6/day_2/class/source/file";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()){
            log.error("part : {}",part.getName());
            //content-disposition header= form-data; name="file2"; filename=2.pdf 형식으로 헤더를
            //구성하고 있기 때문에
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);
            log.error(contentDisposition);
            //filename= 라는 문구가 있으면 파일이 있는 것이다.
            if (contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);

                if (part.getSize() > 0) {
                    part.write(UPLOAD_DIR + File.separator + fileName);
                    part.delete();
                }
            } else {
                String formValue = req.getParameter(part.getName());
                log.error("else : {}={}", part.getName(), formValue);
            }
        }
        resp.sendRedirect("/");
    }

    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}",contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}
