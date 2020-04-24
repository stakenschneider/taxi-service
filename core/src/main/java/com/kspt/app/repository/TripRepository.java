package com.kspt.app.repository;

import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.Status;
import com.kspt.app.entities.Trip;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

/**
 * Created by Masha on 12.03.2020
 */
@Repository
public interface TripRepository extends JPARepository<Trip> {
    Optional<Page<Trip>> findAllByStatusAndTripRate(Status status, Rate tripRate, Pageable pageable);
    Optional<Page<Trip>> findAllByClientId(Long id, Pageable pageable);
    Optional<Page<Trip>> findAllByDriverId(Long id, Pageable pageable);
    Optional<Trip> findByClientIdAndStatus(Long clientId, Status status);
    Optional<Trip> findByDriverIdAndStatus(Long driverId, Status status);
}
