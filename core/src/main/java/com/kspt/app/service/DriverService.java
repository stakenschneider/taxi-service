package com.kspt.app.service;

import com.kspt.app.configuration.Constants.Status;
import com.kspt.app.configuration.SSEController;
import com.kspt.app.entities.Car;
import com.kspt.app.entities.Passport;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.models.info.CarModel;
import com.kspt.app.models.info.PassportModel;
import com.kspt.app.models.response.ApiResult;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.trip.SetGradeModel;
import com.kspt.app.repository.CarRepository;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

/**
 * Created by Masha on 12.03.2020
 */
@Service
public class DriverService {
    private TripRepository tripRepository;
    private DriverRepository driverRepository;
    private CarRepository carRepository;
    private ClientRepository clientRepository;


    public DriverService(TripRepository tripRepository, DriverRepository driverRepository, CarRepository carRepository, ClientRepository clientRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public ApiResult setPassport(PassportModel model) {
        final Passport passport = new Passport(model.getSeries(), model.getNumber());
        Driver driver = driverRepository.findById(model.getId()).orElse(null);
        if (driver != null) {
            if (driver.getPassport() == null) {
                driver.setPassport(passport);
                driverRepository.save(driver);
            } else return new ApiResult("Passport already exist");
        } else return new ApiResult("Driver not found");
        return new ApiResult("Passport was added");
    }

    public ApiResult setCar(CarModel carModel) {
        Car car = new Car(carModel.getCarNumber(), carModel.getModel(), carModel.getColor());
        Driver driver = driverRepository.findById(carModel.getDriverId()).orElse(null);
        if (driver == null) return new ApiResult("Driver not found");
        if (driver.getCar() != null) return new ApiResult("Car already exist");
        driver.setCar(carRepository.save(car));
        driverRepository.save(driver);
        return new ApiResult("Car was added");
    }

    public void sendSseEventsToUI(Trip notification) {
        List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
        SSEController.emitters.forEach((SseEmitter emitter) -> {
            try {
                emitter.send(notification, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                emitter.complete();
                sseEmitterListToRemove.add(emitter);
                e.printStackTrace();
            }
        });
        SSEController.emitters.removeAll(sseEmitterListToRemove);
    }

    public ApiResult takeTrip(Map<String, Long> id) {
        if (id.containsKey("tripId") || id.containsKey("driverId")) {
            Trip trip = tripRepository.findById(id.get("tripId")).orElse(null);
            Driver driver = driverRepository.findById(id.get("driverId")).orElse(null);

            if (trip == null) return new ApiResult("Trip not found");
            if (driver == null) return new ApiResult("Driver not found");
            if (driver.getCar() == null) return new ApiResult("The car is not registered");
            if (driver.getPassport() == null) return new ApiResult("The passport is not registered");

            switch (trip.getStatus()) {
                case CREATE:
                    trip.setDriver(driver);
                    driver.setAvailable(false);
                    trip.setStatus(Status.START);
                    tripRepository.save(trip);
                    trip.getDriver().setRating(Math.round(driver.getRating() * 10.0) / 10.0);
                    sendSseEventsToUI(trip);
                    return new ApiResult("Trip was assign");

                case DENY:
                    return new ApiResult("Trip was deny");

                default:
                    return new ApiResult("Trip already assign");
            }
        } else return new ApiResult("Wrong parameter");
    }

    public ApiResult endTrip(SetGradeModel model) {
//        todo
        Trip trip = tripRepository.findById(model.getTripId()).orElse(null);

        if (trip == null) return new ApiResult("Trip not found");

        Driver driver = trip.getDriver();

        if (driver == null) return new ApiResult("Driver not found");

        Client client = trip.getClient();

        if (client == null) return new ApiResult("Client not found");

        client.setRating((client.getRating() + model.getGrade()) / 2);

        driver.setAvailable(true);
        trip.setStatus(Status.FINISH);

        trip.setDateOfCompletion(new Date());
        clientRepository.save(client);
        driverRepository.save(driver);
        tripRepository.save(trip);
        sendSseEventsToUI(trip);
        return new ApiResult("Trip is over");
    }

    public ResponseOrMessage<Trip> getCurrentTrip(Map<String, Long> driverId) {
        if (driverId.containsKey("id")) {
            Trip trip = tripRepository.findByDriverIdAndStatus(driverId.get("id"), Status.START).orElse(null);

            if (trip == null) {
                return new ResponseOrMessage<>("Driver does not have active trips");
            } else return new ResponseOrMessage<>(trip);

        } else return new ResponseOrMessage<>("Wrong parameter");
    }
}
