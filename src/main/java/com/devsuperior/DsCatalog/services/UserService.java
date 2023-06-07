package com.devsuperior.DsCatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.DsCatalog.dto.RoleDTO;
import com.devsuperior.DsCatalog.dto.UserDTO;
import com.devsuperior.DsCatalog.dto.UserInsertDTO;
import com.devsuperior.DsCatalog.exception.EnityNotFoundException;
import com.devsuperior.DsCatalog.model.Role;
import com.devsuperior.DsCatalog.model.User;
import com.devsuperior.DsCatalog.repository.RoleRepository;
import com.devsuperior.DsCatalog.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository ur;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository rr;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> user = ur.findAll();

        List<UserDTO> UserDTOs = user.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
        return UserDTOs;
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> User = ur.findAll(pageable);

        return User.map(e -> new UserDTO(e));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = ur.findById(id);
        User entity = obj.orElseThrow(() -> new EnityNotFoundException("Entity not found"));

        return new UserDTO(entity);

    }

    @Transactional
    public UserDTO create(UserInsertDTO user1) throws Exception {

        User user = new User();

        copyDtoToEntity(user1, user);
        user.setPassword(passwordEncoder.encode(user1.getPassword()));

        user = ur.save(user);

        return new UserDTO(user);
    }

    public void copyDtoToEntity(UserDTO user1, User user) {
        user.setFirstName(user1.getFirstName());
        user.setLastName(user1.getLastName());
        user.setEmail(user1.getEmail());
        user.getRoles().clear();

        for (RoleDTO roleDTO : user1.getRoles()) {
            Role role = rr.getOne(roleDTO.getId());

            user.getRoles().add(role);
        }

    }

    @Transactional
    public UserDTO update(long id, UserInsertDTO category) {

        try {
            User User = ur.getOne(id);
            copyDtoToEntity(category, User);
            User = ur.save(User);

            UserDTO UserDTO = new UserDTO(User);

            return UserDTO;

        } catch (Exception e) {
            // TODO: handle exception
            throw new EnityNotFoundException("Entity not found");
        }

        // Atualiza o objeto (com id)

    }

    public void delete(long id) throws ResourceNotFoundException {
        try {
            ur.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);

        } catch (Exception e) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("Não é possível deletar uma categoria que possui urodutos");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user = ur.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }

        System.out.println("Username" + username);

        return user;

    }
}
