package com.kspt.app.repository;

import com.kspt.app.entities.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Created by Masha on 10.03.2020
 */
@NoRepositoryBean
public interface JPARepository<T extends AbstractEntity> extends CrudRepository<T, Long> {

    @Override
    long count();

    @Override
    boolean existsById(@NotNull final Long id);

    @NotNull
    @Override
    List<T> findAll();

    @NotNull
    Page<T> findAll(Pageable pageable);

    @NotNull
    @Override
    Optional<T> findById(final Long id);

    @Override
    void deleteAll();

    @Override
    void deleteById(@NotNull Long id);

    @NotNull
    @Override
    <S extends T> S save (@NotNull final S entity);
}
