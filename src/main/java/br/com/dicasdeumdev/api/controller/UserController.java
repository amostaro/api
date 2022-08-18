package br.com.dicasdeumdev.api.controller;

import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(modelMapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll().stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList()));
    }
}
