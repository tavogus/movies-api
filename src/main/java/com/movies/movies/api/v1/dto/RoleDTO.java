package com.movies.movies.api.v1.dto;

import com.movies.movies.domain.model.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String authority;

    public RoleDTO() {}

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }
}
