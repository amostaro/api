package br.com.dicasdeumdev.api.controller;

import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    public static final String ID = "/{id}";
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(modelMapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok()
                .body(userService.findAll().stream()
                    .map(user -> modelMapper.map(user, UserDTO.class))
                    .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path(ID)
                .buildAndExpand(userService.create(userDTO).getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return ResponseEntity.ok().body(modelMapper.map(userService.update(userDTO), UserDTO.class));
    }

    @DeleteMapping(ID)
    ResponseEntity<UserDTO> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
