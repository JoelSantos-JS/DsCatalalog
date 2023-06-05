package com.devsuperior.DsCatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCatalog.dto.RoleDTO;
import com.devsuperior.DsCatalog.dto.UserDTO;
import com.devsuperior.DsCatalog.exception.EnityNotFoundException;
import com.devsuperior.DsCatalog.model.Role;
import com.devsuperior.DsCatalog.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository ur;

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> user = ur.findAll();

        List<RoleDTO> UserDTOs = user.stream().map(e -> new RoleDTO(e)).collect(Collectors.toList());
        return UserDTOs;
    }

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(Pageable pageable) {
        Page<Role> User = ur.findAll(pageable);

        return User.map(e -> new RoleDTO(e));
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Optional<Role> obj = ur.findById(id);
        Role entity = obj.orElseThrow(() -> new EnityNotFoundException("Entity not found"));

        return new RoleDTO(entity);

    }

}
