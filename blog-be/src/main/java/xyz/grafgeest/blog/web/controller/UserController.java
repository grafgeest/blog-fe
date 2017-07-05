package xyz.grafgeest.blog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.grafgeest.blog.entity.User;
import xyz.grafgeest.blog.repository.UserRepository;
import xyz.grafgeest.blog.web.util.HeaderUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/blog/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String ENTITY_NAME = "user";
    @Autowired
    private UserRepository userRepository;

    public UserController() {
    }

    /**
     * GET  /users : get all users.
     *
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getTag(@PathVariable Long id) {
        log.debug("REST request to get Tag : {}", id);
        User user = userRepository.findOne(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createTag(@RequestBody User user) throws URISyntaxException {
        User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/blog/api/users" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }


}
