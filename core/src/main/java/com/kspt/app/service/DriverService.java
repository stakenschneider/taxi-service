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
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public ResponseOrMessage<Person> setPassport(Long id, PassportModel model) {
//com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: com.kspt.app.models.ResponseOrMessage["body"]->com.kspt.app.entities.actor.Driver["car"]->com.kspt.app.entities.Car$HibernateProxy$D85mTwiN["hibernateLazyInitializer"])
        //      TODO HttpMessageNotReadableException
//           example valid data in: 012265 ->JSON parse error
        final Passport passport = new Passport(model.getSeries(), model.getNumber());
        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            if (driver.getPassport() == null) {
                driver.setPassport(passport);
                driverRepository.save(driver);
            } else return new ResponseOrMessage<Person>("Passport already exist");
        } else return new ResponseOrMessage<Person>("Driver not found");
        return new ResponseOrMessage<Person>(driver);
    }

    public ApiResult setCar(Long driverId, CarModel carModel){
        Car car = new Car(carModel.getNumber(),carModel.getModel(),carModel.getColor());
        Driver driver = driverRepository.findById(driverId).orElse(null);
        if(driver == null) return new ApiResult("Driver not found");
        if(driver.getCar()!=null) return new ApiResult("Car already exist");
        driver.setCar(carRepository.save(car));
        driverRepository.save(driver);
        return new ApiResult("Car was added");
    }

    public ApiResult takeTrip(Long tripId, Long driverId) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        Driver driver = driverRepository.findById(driverId).orElse(null);

        if (trip == null) return new ApiResult("Trip not found");
        if (driver == null) return new ApiResult("Driver not found");
        if (driver.getCar() == null) return new ApiResult("The car is not registered");
        if (driver.getPassport() == null) return new ApiResult("The passport is not registered");

        switch (trip.getStatus()){
            case CREATE:
                trip.setDriver(driver);
                driver.setAvailable(false);
                trip.setStatus(Constants.Status.START);
                tripRepository.save(trip);
                return new ApiResult("Trip was assign");

            case DENY:
                return new ApiResult("Trip was deny");

            default:
                return new ApiResult("Trip already assign");
        }
    }

    public ResponseOrMessage<List<Trip>> getFreeTrips() {
        List<Trip> list = tripRepository.findByStatus(Constants.Status.CREATE).orElse(null);
        if (list == null){
            return new ResponseOrMessage<List<Trip>>("No free trips");
        } return new ResponseOrMessage<List<Trip>>(list);
    }

    public ApiResult endTrip(Long driverId, int grade){
//        todo
        Driver driver = driverRepository.findById(driverId).orElse(null);
        Trip trip = tripRepository.findByDriverId(driverId).orElse(null);

        if (driver == null ) return new ApiResult("Driver not found");
        if (trip == null) return new ApiResult("Trip not found");

        Client client = trip.getClient();

        if (client == null) return new ApiResult("Client not found");

        client.setRating((client.getRating()+grade)/2);

        driver.setAvailable(true);
        trip.setStatus(Constants.Status.FINISH);

        trip.setDateOfCompletion(new Date());
        clientRepository.save(client);
        driverRepository.save(driver);
        tripRepository.save(trip);
        return new ApiResult("Trip is over");
    }
}
