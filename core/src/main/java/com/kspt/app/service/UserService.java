package com.kspt.app.service;

import com.kspt.app.entities.actor.Role;
import com.kspt.app.entities.Credentials;
import com.kspt.app.repository.RoleRepository;
import com.kspt.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Masha on 23.03.2020
 */
@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public Credentials findUserById(Long userId) {
        Optional<Credentials> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new Credentials());
    }

    public List<Credentials> allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(Credentials user) {
        Credentials userFromDB = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
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

    public List<Credentials> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM Credentials u WHERE u.id > :paramId", Credentials.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
