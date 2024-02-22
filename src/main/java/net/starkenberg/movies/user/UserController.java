package net.starkenberg.movies.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {
    private final UserRepository repo;
    private final UserService service;

    private String myPath = "/v1/users/";

    public UserController(UserRepository repo, UserService service) {
        this.repo = repo;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder b) {
        user = repo.save(user);
        UriComponents uriComponents =
                b.path(myPath + "{id}").buildAndExpand(user.getId());
        return ResponseEntity.created(uriComponents.toUri()).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(repo.findByUserId(userId));
    }

}
