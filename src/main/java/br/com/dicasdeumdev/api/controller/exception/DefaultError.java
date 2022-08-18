package br.com.dicasdeumdev.api.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DefaultError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
