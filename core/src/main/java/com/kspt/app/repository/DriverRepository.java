package com.kspt.app.repository;

import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.actor.Driver;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Masha on 10.03.2020
 */
@Repository
public interface DriverRepository extends CommonRepository<Driver> {
    Optional<Driver> findByCredentials(@NotNull final Credentials credentials);
    Optional<List<Driver>> findAllByAvailable(@NotNull final Boolean available);
}