package com.kspt.app.service;

import com.kspt.app.models.ApiResult;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Masha on 12.03.2020
 */
@Service
public class AdminService {

    TripRepository tripRepository;
    DriverRepository driverRepository;
    ClientRepository clientRepository;

    public AdminService(TripRepository tripRepository,ClientRepository clientRepository) {
        this.tripRepository = tripRepository;
        this.clientRepository = clientRepository;
    }

//    public ResponseOrMessage<List<Driver>> getFreeDrivers() {
////        toDO java.lang.NullPointerException: null
//        List<Driver> list = driverRepository.findAllByAvailable(true).orElse(null);
//        if (list == null){
//            return new ResponseOrMessage<List<Driver>>("No free drivers");
//        } return new ResponseOrMessage<List<Driver>>(list);
//    }


//    TODO Polymorphic Queries
//    java.lang.NullPointerException: null
    public ApiResult deleteDriver(Long driverId) {
        if (!driverRepository.findById(driverId).isPresent()) return new ApiResult("Driver not found");
        driverRepository.deleteById(driverId);
        return new ApiResult("Driver was deleted");
    }

    public ApiResult deleteClient(Long clientId) {
        if (!clientRepository.findById(clientId).isPresent()) return new ApiResult("Client not found");
        clientRepository.deleteById(clientId);
        return new ApiResult("Client was deleted");
    }

    public ApiResult createDriver() {
        return new ApiResult("sd");
    }
}
