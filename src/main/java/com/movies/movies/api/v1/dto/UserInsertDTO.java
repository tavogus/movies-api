package com.movies.movies.api.v1.dto;

import com.movies.movies.domain.service.validation.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    private static final long serialVersionUID = 1L;

    private String password;

    public UserInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
