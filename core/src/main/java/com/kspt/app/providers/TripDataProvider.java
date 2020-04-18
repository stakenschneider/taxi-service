package com.kspt.app.providers;

import com.kspt.app.entities.Address;
import com.kspt.app.entities.Trip;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.table.GridDataModel;
import com.kspt.app.models.table.MetaDataModel;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.TripRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
@Component
@Qualifier("tripTable")
public class TripDataProvider implements IDataProvider {
    TripRepository tripRepository;
    DriverRepository driverRepository;
    ClientRepository clientRepository;

    public TripDataProvider(TripRepository tripRepository, ClientRepository clientRepository, DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public ResponseOrMessage<GridDataModel> getData(Map<String, Object> parameters) {
        if (!parameters.containsKey("for")) {
            return new ResponseOrMessage<>("Wrong parameter \"for\"");
        }
        switch ((String) parameters.get("for")) {
            case "ADMIN":
                return getDataForAdmin();

            case "DRIVER":
                return getDataForDriver(parameters);

            case "CLIENT":
                return getDataForClient();

            default:
                return new ResponseOrMessage<>("Wrong parameter \"for\"");
        }
    }

    public ResponseOrMessage<GridDataModel> getDataForAdmin() {
        List<Trip> trips = tripRepository.findAll();
        if (trips.isEmpty()) {
            return new ResponseOrMessage<>("Trips not found");
        }
        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();

        String[] columns = {"No.", "Rate", "Payment Method", "Price", "Status", "Rating",
                "date Of Creation", "date Of Completion",
                "client full name", "driver full name", "full start address", "full finish address"};
        metaDataModel.setColumns(columns);
        int countOfTrips = trips.size();
        metaDataModel.setTotalCount(countOfTrips);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        trips.forEach(trip -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(trip.getId());
            row.add(trip.getTripRate());
            row.add(trip.getPaymentMethod());
            row.add(trip.getPrice());
            row.add(trip.getStatus());
            row.add(trip.getRating());
            row.add(trip.getDateOfCreation());
            row.add(trip.getDateOfCompletion());
            Client client = trip.getClient();
            if (client != null) {
                row.add(client.getFirstName() + " " + client.getLastName());
            } else {
                row.add("-");
            }
            Driver driver = trip.getDriver();
            if (driver != null) {
                row.add(driver.getFirstName() + " " + driver.getLastName());
            } else {
                row.add("-");
            }
            Address startAddress = trip.getStartAddress();
            if (startAddress != null) {
                row.add(startAddress.getCity() + ", " + startAddress.getStreet() + ", " + startAddress.getNumberHouse());
            } else {
                row.add("-");
            }
            Address finishAddress = trip.getFinishAddress();
            if (finishAddress != null) {
                row.add(finishAddress.getCity() + ", " + finishAddress.getStreet() + ", " + finishAddress.getNumberHouse());
            } else {
                row.add("-");
            }
            data.add(row);
        });
        dataModel.setData(data);
        dataModel.setMetaData(metaDataModel);
        return new ResponseOrMessage<>(dataModel);
    }

    public ResponseOrMessage<GridDataModel> getDataForDriver(Map<String, Object> parameters) {
        if (!parameters.containsKey("part")) {
            return new ResponseOrMessage<>("Wrong parameter \"part\"");
        }
        switch ((String) parameters.get("part")) {
            case "history":
                return null;
            case "free":
                return null;
            default:
                break;
        }
        return null;
    }

    public ResponseOrMessage<GridDataModel> getDataForClient() {
        //                              <td>{{row.dateOfCreation | date}}</td>
//              <td><i class="fas fa-map-marker-alt" style="color: red"></i> {{row.startAddress.city}}
//                , {{row.startAddress.street}}, {{row.startAddress.numberHouse}} <br>
//                <i class="fas fa-map-marker-alt" style="color: #1100ff"></i> {{row.finishAddress.city}}
//                , {{row.finishAddress.street}}, {{row.finishAddress.numberHouse}}</td>
        return null;
    }
}
