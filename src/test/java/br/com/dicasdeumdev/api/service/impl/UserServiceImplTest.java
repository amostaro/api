package br.com.dicasdeumdev.api.service.impl;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repository.UserRepository;
import br.com.dicasdeumdev.api.service.exception.EmailJaCadastradoException;
import br.com.dicasdeumdev.api.service.exception.UserNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "User1";
    public static final String EMAIL = "email1@email.com";
    public static final String SENHA = "user1";
    @InjectMocks // instancia REAL
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = userService.findById(ID);

        assertNotNull(response);

        assertAll(
                () -> assertEquals(User.class, response.getClass()),
                () -> assertEquals(ID, response.getId()),
                () -> assertEquals(NAME, response.getName()),
                () -> assertEquals(EMAIL, response.getEmail())
        );
    }

    @Test
    void whenFindByIdThenReturnAnUserNaoEncontradoException() {
        when(userRepository.findById(anyInt())).thenThrow(new UserNaoEncontradoException("User não encontrado."));

        try {
            userService.findById(ID);
        } catch (Exception ex) {
            assertEquals(UserNaoEncontradoException.class, ex.getClass());
            assertEquals("User não encontrado.", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userService.findAll();

        assertNotNull(response);

        assertAll(
                () -> assertEquals(1, response.size()),
                () -> assertEquals(User.class, response.get(0).getClass()),
                () -> assertEquals(ID, response.get(0).getId()),
                () -> assertEquals(NAME, response.get(0).getName()),
                () -> assertEquals(EMAIL, response.get(0).getEmail()),
                () -> assertEquals(SENHA, response.get(0).getSenha())
        );
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.create(userDTO);

        assertNotNull(response);

        assertAll(
                () -> assertEquals(User.class, response.getClass()),
                () -> assertEquals(ID, response.getId()),
                () -> assertEquals(NAME, response.getName()),
                () -> assertEquals(EMAIL, response.getEmail()),
                () -> assertEquals(SENHA, response.getSenha())
        );
    }

    @Test
    void whenCreateThenReturnEmailJaCadastradoException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            userService.create(userDTO);
        } catch (Exception ex) {
            assertEquals(EmailJaCadastradoException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema.", ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.update(userDTO);

        assertNotNull(response);

        assertAll(
                () -> assertEquals(User.class, response.getClass()),
                () -> assertEquals(ID, response.getId()),
                () -> assertEquals(NAME, response.getName()),
                () -> assertEquals(EMAIL, response.getEmail()),
                () -> assertEquals(SENHA, response.getSenha())
        );
    }

    @Test
    void whenUpdateThenReturnEmailJaCadastradoException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            userService.update(userDTO);
        } catch (Exception ex) {
            assertEquals(EmailJaCadastradoException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema.", ex.getMessage());
        }
    }

    @Test
    void whenDeleteThenReturnNoContent() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(userRepository).deleteById(anyInt());
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void whenDeleteThenReturnUserNaoEncontradoException() {
        when(userRepository.findById(anyInt())).thenThrow(new UserNaoEncontradoException("User não encontrado."));
        try {
            userService.delete(ID);
        } catch (Exception ex) {
            assertEquals(UserNaoEncontradoException.class, ex.getClass());
            assertEquals("User não encontrado.", ex.getMessage());
        }
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, SENHA);
        userDTO = new UserDTO(ID, NAME, EMAIL, SENHA);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, SENHA));
    }
}