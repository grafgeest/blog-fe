package xyz.grafgeest.blog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import xyz.grafgeest.blog.entity.Post;
import xyz.grafgeest.blog.repository.PostRepository;

import java.util.List;

@RestController
@RequestMapping("/blog/api")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            log.debug("No posts content");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.debug("Return all posts");
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPost(@PathVariable("id") Long id) {
        log.debug("Fetching Post with id : {}", id);
        Post post = postRepository.findOne(id);
        if (post == null) {
            log.debug("Post with id : {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<Void> createPost(@RequestBody Post post, UriComponentsBuilder ucBuilder) {
        log.debug("Creating Post : {}", post.getTitle());

        if (postRepository.existsByTitle(post.getTitle())) {
            log.debug("A post with title : {} already exist", post.getTitle());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        post = postRepository.save(post);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/posts/{id}").buildAndExpand(post.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/posts/{id}")
    public ResponseEntity<Post> updatePostr(@PathVariable("id") Long id, @RequestBody Post post) {
        log.debug("Updating Post : {}", post.getTitle());

        Post currentPost = postRepository.findOne(id);

        if (currentPost == null) {
            log.debug("Post with id : {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentPost.setTitle(post.getTitle());
        currentPost.setContent(post.getContent());

        postRepository.save(currentPost);
        return new ResponseEntity<>(currentPost, HttpStatus.OK);
    }


    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id) {
        log.debug("Fetching & Deleting Post with id : {}", id);

        Post post = postRepository.findOne(id);
        if (post == null) {
            log.debug("Unable to delete Post with id  : {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Post> deleteAllPosts() {
        log.debug("Deleting All Posts");
        postRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
