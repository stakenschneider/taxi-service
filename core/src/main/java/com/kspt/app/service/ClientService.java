package com.kspt.app.service;

import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.configuration.Constants.Status;

import com.kspt.app.entities.Address;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;

import com.kspt.app.entities.actor.Driver;
import com.kspt.app.models.response.ApiResult;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.trip.SetGradeModel;
import com.kspt.app.models.trip.TripModelRequest;
import com.kspt.app.repository.AddressRepository;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
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

    public ClientService(DriverRepository driverRepository, ClientRepository clientRepository, TripRepository tripRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.tripRepository = tripRepository;
        this.addressRepository = addressRepository;
        this.driverRepository = driverRepository;
    }

    public ApiResult requestCar(TripModelRequest model) {

        Address startAddress = new Address(model.getStartAddress().getCity(),
                model.getStartAddress().getStreet(),
                model.getStartAddress().getNumberHouse());

        Address finishAddress = new Address(model.getFinishAddress().getCity(),
                model.getFinishAddress().getStreet(),
                model.getFinishAddress().getNumberHouse());

        Client client = clientRepository.findById(model.getClientId()).orElse(null);

        if (client == null) {
            return new ApiResult("Client doesnt exist");
        }

        Boolean ifPresentCreate = tripRepository.findByClientIdAndStatus(client.getId(), Status.CREATE).isPresent();
        Boolean ifPresentStart = tripRepository.findByClientIdAndStatus(client.getId(), Status.START).isPresent();

        if (ifPresentCreate || ifPresentStart) {
            return new ApiResult("U already have active trip, u can't request to car the same time");
        }

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

    public ApiResult setGrade(SetGradeModel model) {
        Trip trip = tripRepository.findById(model.getTripId()).orElse(null);

        if (trip == null) {
            return new ApiResult("Trip doesnt exist");
        }

        if (trip.getStatus() != Status.FINISH) {
            return new ApiResult("Trip not finished");
        }

        Driver driver = driverRepository.findById(trip.getDriver().getId()).orElse(null);

        if (driver == null) {
            return new ApiResult("Driver not found");
        }

        driver.setRating((driver.getRating() + (double) model.getGrade()) / 2);
        trip.setRating(model.getGrade());

        driverRepository.save(driver);
        tripRepository.save(trip);
        return new ApiResult("Thank you for rating");
    }

    public ApiResult denyTrip(Map<String, Long> clientId) {
        if (clientId.containsKey("id")) {
            Trip trip = tripRepository.findByClientIdAndStatus(clientId.get("id"), Status.CREATE).orElse(null);
            if (trip == null) return new ApiResult("Trip doesnt exist");
            trip.setStatus(Status.DENY);
            trip.setDateOfCompletion(new Date());
            tripRepository.save(trip);
            return new ApiResult("The trip was canceled");
        } else return new ApiResult("Wrong parameter");

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
            default:
                return 0.0;
        }
    }

    public ResponseOrMessage<Trip> getActiveTrip(Map<String, Long> clientId) {
        if (clientId.containsKey("clientId")) {
            Trip trip = tripRepository.findByClientIdAndStatus(clientId.get("clientId"), Status.CREATE).orElseGet(
                    () -> tripRepository.findByClientIdAndStatus(clientId.get("clientId"), Status.START).orElse(null));

            if (trip == null) {
                return new ResponseOrMessage<>("Client does not have active trips");
            } else return new ResponseOrMessage<>(trip);

        } else return new ResponseOrMessage<>("Wrong parameter");
    }
}
