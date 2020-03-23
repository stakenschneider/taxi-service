package com.kspt.app.repository;

import com.kspt.app.entities.Credentials;

import java.util.Optional;

/**
 * Created by Masha on 23.03.2020
 */
public interface UserRepository extends CommonRepository<Credentials>  {
    Optional<Credentials> findByUsername(String username);
}
