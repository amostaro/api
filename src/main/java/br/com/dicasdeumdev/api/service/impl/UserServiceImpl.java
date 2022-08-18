package br.com.dicasdeumdev.api.service.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.repository.UserRepository;
import br.com.dicasdeumdev.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User não encontrado.")
        );
        return user;
    }
}
