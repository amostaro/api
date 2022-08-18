package br.com.dicasdeumdev.api.service.exception;

public class UserNaoEncontradoException extends RuntimeException{

    public UserNaoEncontradoException(String message) {
        super(message);
    }
}
