package com.movies.movies.api.v1.dto;

import com.movies.movies.domain.service.validation.UserUpdateValid;

@UserUpdateValid // Vai verificar se o email que eu estou inserindo já existe no banco
public class UserUpdateDTO extends UserDTO {

    private static final long serialVersionUID = 1L;

}
