package com.devsuperior.DsCatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.DsCatalog.dto.UserDTO;
import com.devsuperior.DsCatalog.dto.UserInsertDTO;
import com.devsuperior.DsCatalog.services.ResourceNotFoundException;
import com.devsuperior.DsCatalog.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService cs;

    @GetMapping()
    public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable) {

        Page<UserDTO> list = cs.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cs.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserInsertDTO user) throws Exception {
        UserDTO cat = cs.create(user);
        return ResponseEntity.status(201).body(cat);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserInsertDTO category) {
        UserDTO cat = cs.update(id, category);

        return ResponseEntity.ok().body(cat);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws ResourceNotFoundException {
        cs.delete(id);
        return ResponseEntity.noContent().build();
    }

}