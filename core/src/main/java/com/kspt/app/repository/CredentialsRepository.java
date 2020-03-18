package com.kspt.app.repository;

import com.kspt.app.entities.Credentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Masha on 10.03.2020
 */
@Repository
public interface CredentialsRepository extends CommonRepository<Credentials> {

    Optional<Credentials> findByEmailAndPassword(@NotNull final String email,
                                                 @NotNull final String password);

    @Override
    void delete(@NotNull final Credentials credentials);
}
