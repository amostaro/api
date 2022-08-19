package br.com.dicasdeumdev.api.service.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repository.UserRepository;
import br.com.dicasdeumdev.api.service.UserService;
import br.com.dicasdeumdev.api.service.exception.EmailJaCadastradoException;
import br.com.dicasdeumdev.api.service.exception.UserNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNaoEncontradoException("User não encontrado.")
        );
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        this.findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        this.findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO userDTO) {
        Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
            throw new EmailJaCadastradoException("Email já cadastrado no sistema.");
        }
    }

}
