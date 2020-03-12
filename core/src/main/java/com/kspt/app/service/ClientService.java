package com.kspt.app.service;

import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.Address;
import com.kspt.app.entities.Passport;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;

import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.ApiResult;
import com.kspt.app.models.PassportModel;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.models.TripModelRequest;
import com.kspt.app.repository.AddressRepository;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by Masha on 11.03.2020
 */
@Service
public class ClientService {
    private ClientRepository clientRepository;
    private TripRepository tripRepository;
    private AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository, TripRepository tripRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.tripRepository = tripRepository;
        this.addressRepository = addressRepository;
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
            } else return new ResponseOrMessage("Passport already exist");
        } else return new ResponseOrMessage("Client not found");
        return new ResponseOrMessage(client);
    }

    public ApiResult requestCar(TripModelRequest model, Long clientId){

        Address startAddress = new Address(model.getStartAddress().getCity(),
                model.getStartAddress().getStreet(),
                model.getStartAddress().getNumberHouse());

        Address finishAddress = new Address(model.getFinishAddress().getCity(),
                model.getFinishAddress().getStreet(),
                model.getFinishAddress().getNumberHouse());

        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) return new ApiResult("Client doesnt exist");

        Trip trip = new Trip();
        trip.setStartAddress(addressRepository.save(startAddress));
        trip.setFinishAddress( addressRepository.save(finishAddress));
        trip.setClient(client);
        trip.setPrice(new Random().nextInt(600) + 40.0);
        trip.setStatus(Constants.Status.CREATE);
        tripRepository.save(trip);

        return new ApiResult("The trip was created. Wait for a response.");
    }

    public ApiResult setRate(Long tripId, int rate){
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null) return new ApiResult("Trip doesnt exist");
        if (trip.getStatus() != Constants.Status.FINISH) return new ApiResult("Trip not finished");
        trip.setRate(rate);
        return new ApiResult("Thank you for rating");
    }

    public ApiResult denyTrip(Long tripId){
        Trip trip = tripRepository.findById(tripId).orElse(null);
        if (trip == null) return new ApiResult("Trip doesnt exist");
        trip.setStatus(Constants.Status.DENY);
        tripRepository.save(trip);
        return new ApiResult("The trip was canceled");
    }
}
