package com.kspt.app.repository;

import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.Trip;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Masha on 12.03.2020
 */
@Repository
public interface TripRepository extends JPARepository<Trip> {
    Optional<List<Trip>> findByStatus(Constants.Status status);
    Optional<Trip> findByDriverId(Long id);
    Optional<List<Trip>> findAllByClientId(Long id);
}
