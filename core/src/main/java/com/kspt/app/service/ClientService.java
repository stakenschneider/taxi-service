package com.kspt.app.service;

import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.Status;

import com.kspt.app.entities.Address;
import com.kspt.app.entities.Passport;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;

import com.kspt.app.entities.actor.Driver;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.ApiResult;
import com.kspt.app.models.PassportModel;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.models.TripModelRequest;
import com.kspt.app.repository.AddressRepository;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Masha on 11.03.2020
 */
@Service
public class ClientService {
    private ClientRepository clientRepository;
    private TripRepository tripRepository;
    private AddressRepository addressRepository;
    private DriverRepository driverRepository;

    public ClientService(DriverRepository driverRepository,ClientRepository clientRepository, TripRepository tripRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.tripRepository = tripRepository;
        this.addressRepository = addressRepository;
        this.driverRepository = driverRepository;
    }

    public ResponseOrMessage<Person> setPassport(Long id, PassportModel model) {

//      TODO HttpMessageNotReadableException
//           example valid data in: 012265 ->JSON parse error
        final Passport passport = new Passport(model.getSeries(), model.getNumber());
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            if (client.getPassport() == null) {
                client.setPassport(passport);
                clientRepository.save(client);
            } else return new ResponseOrMessage<Person> ("Passport already exist");
        } else return new ResponseOrMessage<Person> ("Client not found");
        return new ResponseOrMessage<Person>(client);
    }

    public ApiResult requestCar(TripModelRequest model, Long clientId) {

        Address startAddress = new Address(model.getStartAddress().getCity(),
                model.getStartAddress().getStreet(),
                model.getStartAddress().getNumberHouse());

        Address finishAddress = new Address(model.getFinishAddress().getCity(),
                model.getFinishAddress().getStreet(),
                model.getFinishAddress().getNumberHouse());

        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) return new ApiResult("Client doesnt exist");

        Trip trip = new Trip();
        Rate rate = model.getRate();

        trip.setPaymentMethod(model.getPaymentMethod());
        trip.setDateOfCreation(new Date());
        trip.setStartAddress(addressRepository.save(startAddress));
        trip.setFinishAddress(addressRepository.save(finishAddress));
        trip.setClient(client);
        trip.setTripRate(rate);
        trip.setPrice(setPrice(rate));
        trip.setStatus(Status.CREATE);
        tripRepository.save(trip);

        return new ApiResult("The trip was created. Wait for a response.");
    }

    public ApiResult setGrade(Long tripId, int grade) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null) return new ApiResult("Trip doesnt exist");
        if (trip.getStatus() != Status.FINISH) return new ApiResult("Trip not finished");

        Driver driver = driverRepository.findById(trip.getDriver().id).orElse(null);
        if (driver == null) return new ApiResult("Driver not found");

        driver.setRating((driver.getRating()+trip.getRating())/2);

        trip.setRating(grade);

        driverRepository.save(driver);
        tripRepository.save(trip);
        return new ApiResult("Thank you for rating");
    }

    public ApiResult denyTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null) return new ApiResult("Trip doesnt exist");
        trip.setStatus(Status.DENY);
        trip.setDateOfCompletion(new Date());
        tripRepository.save(trip);
        return new ApiResult("The trip was canceled");
    }

    private Double setPrice(Rate rate) {
        switch (rate) {
            case ECONOMY:
                return new Random().nextInt(560) + 40.0;
            case COMFORT:
                return new Random().nextInt(900) + 300.0;
            case GOLD:
            case LUX:
                return new Random().nextInt(10000) + 5000.0;
            case GOD:
                return new Random().nextInt(Integer.MAX_VALUE) + 0.0;
            default: return 0.0;
        }
    }

    public ResponseOrMessage<List<Trip>> getHistoryOfTrips(Long clientId) {
        List<Trip> list = tripRepository.findAllByClientId(clientId).orElse(null);
        if (list == null) return new ResponseOrMessage<>("Trips not found");
        else return new ResponseOrMessage<>(list);
    }

    public ApiResult changePaymentMethod(Long tripId, PaymentMethod newPaymentMethod) {
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip==null) return new ApiResult("Trip not found");
        trip.setPaymentMethod(newPaymentMethod);
        return new ApiResult("Payment Method was changed");
    }
}