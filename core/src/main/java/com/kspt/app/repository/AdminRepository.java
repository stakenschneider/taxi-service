package com.kspt.app.repository;

import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.actor.Admin;
import com.kspt.app.entities.actor.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Masha on 10.03.2020
 */
@Repository
public interface AdminRepository extends CommonRepository<Admin> {
    Optional<Person> findByCredentials(@NotNull final Credentials credentials);
}
