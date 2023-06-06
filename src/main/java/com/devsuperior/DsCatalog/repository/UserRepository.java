package com.devsuperior.DsCatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.DsCatalog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
