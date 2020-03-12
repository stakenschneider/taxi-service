package com.kspt.app.service;

import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.Car;
import com.kspt.app.entities.Passport;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.*;
import com.kspt.app.repository.CarRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Masha on 12.03.2020
 */
@Service
public class DriverService {
    private TripRepository tripRepository;
    private DriverRepository driverRepository;
    private CarRepository carRepository;

    public DriverService(TripRepository tripRepository, DriverRepository driverRepository, CarRepository carRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
    }

    public ResponseOrMessage<Person> setPassport(Long id, PassportModel model) {
//      TODO HttpMessageNotReadableException
//           example valid data in: 012265 ->JSON parse error
        final Passport passport = new Passport(model.getSeries(), model.getNumber());
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            if (driver.getPassport() == null) {
                driver.setPassport(passport);
                driverRepository.save(driver);
            } else return new ResponseOrMessage("Passport already exist");
        } else return new ResponseOrMessage("Driver not found");
        return new ResponseOrMessage(driver);
    }

    public ApiResult setCar(Long driverId, CarModel carModel){
        Car car = new Car(carModel.getNumber(),carModel.getModel(),carModel.getColor());
        Driver driver = driverRepository.findById(driverId).orElse(null);
        if(driver == null) return new ApiResult("Driver not found");
        driver.setCar(carRepository.save(car));
        driverRepository.save(driver);
        return new ApiResult("Car was added");
    }

    public ResponseOrMessage<Trip> takeTrip(Long tripId, Long driverId) {

        Trip trip = tripRepository.findById(tripId).orElse(null);
        Driver driver = driverRepository.findById(driverId).orElse(null);

        if (trip == null) return new ResponseOrMessage("Trip doesn't exist");
        if (driver == null) return new ResponseOrMessage("Driver doesn't exist");
        if (driver.getCar() == null) return new ResponseOrMessage("The car is not registered");
        if (driver.getPassport() == null) return new ResponseOrMessage("The passport is not registered");

        switch (trip.getStatus()){
            case CREATE:
                trip.setDriver(driver);
                driver.setAvailable(false);
                trip.setStatus(Constants.Status.START);
                return new ResponseOrMessage(tripRepository.save(trip));

            case DENY:
                return new ResponseOrMessage("Trip was deny");

            default:
                return new ResponseOrMessage("Trip was started with another driver");
        }
    }

    public ApiResult endTrip(Long driverId){
        Driver driver = driverRepository.findById(driverId).orElse(null);
        Trip trip = tripRepository.findByDriverId(driverId).orElse(null);

        if (driver == null ) return new ApiResult("Driver not found");
        if (trip == null) return new ApiResult("Trip not found");

        driver.setAvailable(true);
        trip.setStatus(Constants.Status.FINISH);

        driverRepository.save(driver);
        tripRepository.save(trip);
        return new ApiResult("Trip is over");
    }
}
