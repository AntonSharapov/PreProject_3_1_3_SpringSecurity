package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserServiceIMP implements UserService, UserDetailsService {

    @PersistenceContext
    private EntityManager entManager;
    UserDaoImpl userDao;

    BCryptPasswordEncoder PasswordEncoder;

    public UserServiceIMP(UserDaoImpl userDao, BCryptPasswordEncoder PasswordEncoder) {
        this.userDao = userDao;
        this.PasswordEncoder = PasswordEncoder;
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getUserByName(username);
    }

//    public User getUserById(long userId) {
//        Optional<User> userDB = userRepository.findById(userId);
//        return userDB.orElse(new User());
//    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Transactional
    public void saveUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }
    @Transactional
    public void deleteUser(Long id) {
            userDao.deleteUser(id);
    }
    @Transactional
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(userDao.getUserById(user.getId()).getPassword())){
            user.setPassword(PasswordEncoder.encode(user.getPassword()));
        }
        userDao.saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found yet:" + " " + username);
        }
        return user;
    }
}


