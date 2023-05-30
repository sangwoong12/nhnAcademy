package com.nhnacademy.springjpa.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
    @ContextConfiguration(classes = RootConfig.class),
    @ContextConfiguration(classes = WebConfig.class)
})
public class CommentEntityTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    void test() {
        Comment comment = new Comment();
        comment.setContent("content");

        CommentDetail commentDetail1 = new CommentDetail();
        commentDetail1.setDescription("...");
        commentDetail1.setComment(comment);

        CommentDetail commentDetail2 = new CommentDetail();
        commentDetail2.setDescription("???");
        commentDetail2.setComment(comment);

        List<CommentDetail> details = new ArrayList<>();
        details.add(commentDetail1);
        details.add(commentDetail2);

        comment.setDetails(details);

        commentRepository.saveAndFlush(comment);
        
        assertThat(commentRepository.findById(comment.getId()).get().getId()).isNotNull();
        assertThat(commentRepository.findById(comment.getId()).get().getId()).isEqualTo(comment.getId());
    }
}
