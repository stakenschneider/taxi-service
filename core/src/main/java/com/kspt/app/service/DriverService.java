package com.kspt.app.service;

import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.Car;
import com.kspt.app.entities.Passport;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
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

    public PersonResponse setPassport(Long id, PassportModel model) {
//      TODO HttpMessageNotReadableException
//           example valid data in: 012265 ->JSON parse error
        final Passport passport = new Passport(model.getSeries(), model.getNumber());
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            if (driver.getPassport() == null) {
                driver.setPassport(passport);
                driverRepository.save(driver);
            } else return new PersonResponse("Passport already exist");
        } else return new PersonResponse("Driver not found");
        return new PersonResponse(driver);
    }

    public ApiResult setCar(Long driverId, CarModel carModel){
        Car car = new Car(carModel.getNumber(),carModel.getModel(),carModel.getColor());
        Driver driver = driverRepository.findById(driverId).orElse(null);
        if(driver == null) return new ApiResult("Driver not found");
        driver.setCar(carRepository.save(car));
        driverRepository.save(driver);
        return new ApiResult("Car was added");
    }
    public ListOfTripModel getFreeTrips() {
        List<Trip> list = tripRepository.findByStatus(Constants.Status.CREATE).orElse(null);
        if (list == null){
            return new ListOfTripModel("No free trips");
        } return new ListOfTripModel(list);
    }

    public TripModel takeTrip(Long tripId, Long driverId) {

        Trip trip = tripRepository.findById(tripId).orElse(null);
        Driver driver = driverRepository.findById(driverId).orElse(null);

        if (trip == null) return new TripModel("Trip doesn't exist");
        if (driver == null) return new TripModel("Driver doesn't exist");
        if (driver.getCar() == null) return new TripModel("The car is not registered");
        if (driver.getPassport() == null) return new TripModel("The passport is not registered");

        switch (trip.getStatus()){
            case CREATE:
                trip.setDriver(driver);
                driver.setAvailable(false);
                trip.setStatus(Constants.Status.START);
                return new TripModel(tripRepository.save(trip));

            case DENY:
                return new TripModel("Trip was deny");

            default:
                return new TripModel("Trip was started with another driver");
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
