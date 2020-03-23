package com.kspt.app.repository;

import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.actor.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Masha on 10.03.2020
 */
@Repository
public interface ClientRepository extends JPARepository<Client> {
    Optional<Person> findByCredentials(@NotNull final Credentials credentials);
}
