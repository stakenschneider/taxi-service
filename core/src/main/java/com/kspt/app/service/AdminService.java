package com.kspt.app.service;

import com.kspt.app.configuration.Constants.PersonType;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.models.response.ApiResult;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Masha on 12.03.2020
 */
@Service
public class AdminService {

    TripRepository tripRepository;
    DriverRepository driverRepository;
    ClientRepository clientRepository;

    public AdminService(TripRepository tripRepository, ClientRepository clientRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
    }

    public ApiResult deletePerson(Long personId, PersonType personType) {
        switch (personType) {
            case DRIVER:
                Driver driver = driverRepository.findById(personId).orElse(null);
                if (driver == null) {
                    return new ApiResult("Driver not found");
                }
                driver.setDeleted(true);
                driverRepository.save(driver);
                return new ApiResult("Driver was deleted");

            case CLIENT:
                Client client = clientRepository.findById(personId).orElse(null);
                if (client == null) {
                    return new ApiResult("Client not found");
                }
                client.setDeleted(true);
                clientRepository.save(client);
                return new ApiResult("Client was deleted");

            default:
                return new ApiResult("Wrong parameter");
        }
    }

    public ResponseOrMessage<List<Trip>> getAllTrips() {
        List<Trip> list = tripRepository.findAll();
        if (list.isEmpty()) return new ResponseOrMessage<>("Trips not found");
        return new ResponseOrMessage<>(list);
    }

    public ResponseOrMessage<List<Client>> getAllClients() {
        List<Client> list = clientRepository.findAll();
        if (list.isEmpty()) return new ResponseOrMessage<>("Clients not found");
        return new ResponseOrMessage<>(list);
    }

    public ResponseOrMessage<List<Driver>> getAllDrivers() {
        List<Driver> list = driverRepository.findAll();
        if (list.isEmpty()) return new ResponseOrMessage<>("Drivers not found");
        return new ResponseOrMessage<>(list);
    }
}
