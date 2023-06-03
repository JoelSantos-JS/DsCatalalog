package com.devsuperior.DsCatalog.dto;

import java.io.Serializable;

import com.devsuperior.DsCatalog.model.Role;

public class RoleDTO implements Serializable {

    private long id;
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Role role) {
        id = getId();
        authority = getAuthority();
    }

    public RoleDTO(long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
