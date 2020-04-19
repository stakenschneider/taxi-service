package com.kspt.app.service;

import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.Trip;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Masha on 19.04.2020
 */
@Service
public class DataService {
    private TripRepository tripRepository;

    public DataService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Constants.PaymentMethod[] getPM() {
        return Constants.PaymentMethod.values();
    }

    public Constants.Color[] getCarColors() {
        return Constants.Color.values();
    }

    public Constants.CarModels[] getCarModels() {
        return Constants.CarModels.values();
    }

    public Constants.Rate[] getRates() {
        return Constants.Rate.values();
    }

    public ResponseOrMessage<Trip> getTripById(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null) {
            return new ResponseOrMessage<>("Trip not found");
        } else return new ResponseOrMessage<>(trip);
    }
}
