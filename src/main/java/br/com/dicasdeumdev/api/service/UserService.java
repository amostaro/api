package br.com.dicasdeumdev.api.service;

import br.com.dicasdeumdev.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
