package br.com.dicasdeumdev.api.controller;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

//        return ResponseEntity.ok().body(new User(1, "Andre", "email@email.com", "123"));
        return ResponseEntity.ok().body(userService.findById(id));
    }
}
