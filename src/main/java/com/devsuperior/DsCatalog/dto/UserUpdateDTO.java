package com.devsuperior.DsCatalog.dto;

import java.io.Serializable;

import com.devsuperior.DsCatalog.services.validation.UserInsertValid;
import com.devsuperior.DsCatalog.services.validation.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

}
