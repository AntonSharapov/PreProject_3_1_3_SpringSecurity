package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entManager;
    UserRepository userRepository;
    RoleRepository roleRepository;

    BCryptPasswordEncoder PasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder PasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.PasswordEncoder = PasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUserById(Long userId) {
        Optional<User> userDB = userRepository.findById(userId);
        return userDB.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User usDB = userRepository.findByUsername(user.getUsername());

        if (usDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void edUser(User user) {
        userRepository.save(entManager.merge(user));
    }
    //    public List<User> usergetList(Long idMin) {
//        return entManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
//                .setParameter("paramId", idMin).getResultList();
//    }
}


