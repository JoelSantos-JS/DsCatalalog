package com.devsuperior.DsCatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.DsCatalog.dto.RoleDTO;
import com.devsuperior.DsCatalog.services.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RolesController {

    @Autowired
    private RoleService cs;

    @GetMapping()
    public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable) {

        Page<RoleDTO> list = cs.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cs.findById(id));
    }

}
