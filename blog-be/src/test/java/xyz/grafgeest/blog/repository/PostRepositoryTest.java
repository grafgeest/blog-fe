package xyz.grafgeest.blog.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.grafgeest.blog.SpringBootRestApplication;
import xyz.grafgeest.blog.entity.Post;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApplication.class)
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test(expected = ConstraintViolationException.class)
    public void givenPostRepository_whenSave_thenConstraintViolationException() {
        postRepository.save(new Post());
    }

    @Test
    public void givenPostRepository_whenSaveAndRetrievePost_thenOK() {
        Post post = postRepository.save(new Post("title", "content"));
        Post foundPost = postRepository.findOne(post.getId());

        assertNotNull(foundPost);
        assertEquals(post.getId(), foundPost.getId());
    }

}