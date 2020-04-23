package com.kspt.app.providers;

import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.Address;
import com.kspt.app.entities.Car;
import com.kspt.app.entities.Passport;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

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
                return getDataForAdmin(parameters);

            case "DRIVER":
                return getDataForDriver(parameters);

            case "CLIENT":
                return getDataForClient(parameters);

            default:
                return new ResponseOrMessage<>("Wrong parameter \"for\"");
        }
    }

    public ResponseOrMessage<GridDataModel> getDataForAdmin(Map<String, Object> parameters) {
        if (!parameters.containsKey("page") || !parameters.containsKey("size") || !parameters.containsKey("sortBy")) {
            return new ResponseOrMessage<>("Wrong parameter \"page\" or \"size\"or \"sortBy\"");
        }

        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();

        String[] columns = {"No.", "Rate", "Payment Method", "Price", "Status", "Rating",
                "date Of Creation", "date Of Completion",
                "client full name", "driver full name", "full start address", "full finish address"};
        metaDataModel.setColumns(columns);
        metaDataModel.setTotalCount(tripRepository.count());

        Page<Trip> page = tripRepository.findAll(PageRequest.of(Integer.parseInt((String) parameters.get("page")),
                Integer.parseInt((String) parameters.get("size")),
                Sort.by((String) parameters.get("sortBy"))));

        List<Trip> trips = page.getContent();

        if (trips.isEmpty()) {
            return new ResponseOrMessage<>("Trips not found");
        }

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        trips.forEach(trip -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(trip.getId());
            row.add(trip.getTripRate());
            row.add(trip.getPaymentMethod());
            row.add(trip.getPrice() + "$");
            row.add(trip.getStatus());
            row.add(trip.getRating());
            row.add(formatDate(trip.getDateOfCreation()));
            Date finishDate = trip.getDateOfCompletion();
            if (finishDate == null) {
                row.add("-");
            } else {
                row.add(formatDate(finishDate));
            }
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

    public ResponseOrMessage<GridDataModel> getDataForClient(Map<String, Object> parameters) {

        if (!parameters.containsKey("personId")) {
            return new ResponseOrMessage<>("Wrong parameter");
        }

        List<Trip> trips = tripRepository.findAllByClientId((long) (int) parameters.get("personId")).orElse(null);
        if (trips == null) {
            return new ResponseOrMessage<>("Trips not found");
        }

        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();
        String[] columns = {"No.", "date of creation", "Start Address", "Finish Address"};
        metaDataModel.setColumns(columns);
        int countOfTrips = trips.size();
        metaDataModel.setTotalCount((long) countOfTrips);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        trips.forEach(trip -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(trip.getId());
            row.add(formatDate(trip.getDateOfCreation()));
            Address startAddress = trip.getStartAddress();
            row.add(startAddress.getCity() + ", " + startAddress.getStreet() + ", " + startAddress.getNumberHouse());
            Address finishAddress = trip.getFinishAddress();
            row.add(finishAddress.getCity() + ", " + finishAddress.getStreet() + ", " + finishAddress.getNumberHouse());
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
                return getDataForDriverHistory(parameters);
            case "free":
                return getDataForDriverFreeTrips(parameters);
            default:
                return new ResponseOrMessage<>("Wrong parameter DRIVER -> \"part\"");
        }
    }

    private ResponseOrMessage<GridDataModel> getDataForDriverFreeTrips(Map<String, Object> parameters) {
        if (!parameters.containsKey("personId")) {
            return new ResponseOrMessage<>("Wrong parameter");
        }

        Driver driver = driverRepository.findById((long) (int) parameters.get("personId")).orElse(null);
        if (driver == null) {
            return new ResponseOrMessage<>("Driver not found");
        }

        Car car = driver.getCar();
        if (car == null) {
            return new ResponseOrMessage<>("You must register car before book a trip");
        }

        Passport passport = driver.getPassport();
        if (passport == null) {
            return new ResponseOrMessage<>("You must register passport before book a trip");
        }

        List<Trip> trips = tripRepository.findAllByStatusAndTripRate(Constants.Status.CREATE, car.getCarRate()).orElse(null);
        if (trips == null) {
            return new ResponseOrMessage<>("Trips not found");
        }

        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();
        String[] columns = {"No.", "Price", "Payment Method", "Start Address", "Finish Address", "Client Rating"};
        metaDataModel.setColumns(columns);
        int countOfTrips = trips.size();
        metaDataModel.setTotalCount((long) countOfTrips);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        trips.forEach(trip -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(trip.getId());
            row.add(trip.getPrice() + "$");
            row.add(trip.getPaymentMethod());
            Address startAddress = trip.getStartAddress();
            row.add(startAddress.getCity() + ", " + startAddress.getStreet() + ", " + startAddress.getNumberHouse());
            Address finishAddress = trip.getFinishAddress();
            row.add(finishAddress.getCity() + ", " + finishAddress.getStreet() + ", " + finishAddress.getNumberHouse());
            row.add(trip.getClient().getRating());
            data.add(row);
        });

        dataModel.setData(data);
        dataModel.setMetaData(metaDataModel);
        return new ResponseOrMessage<>(dataModel);
    }

    private ResponseOrMessage<GridDataModel> getDataForDriverHistory(Map<String, Object> parameters) {
        if (!parameters.containsKey("personId")) {
            return new ResponseOrMessage<>("Wrong parameter");
        }

        Driver driver = driverRepository.findById((long) (int) parameters.get("personId")).orElse(null);
        if (driver == null) {
            return new ResponseOrMessage<>("Driver not found");
        }

        List<Trip> trips = tripRepository.findAllByDriverId(driver.getId()).orElse(null);
        if (trips == null) {
            return new ResponseOrMessage<>("Trips not found");
        }

        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();

        String[] columns = {"No.", "Rating", "Price", "Start Address", "Finish Address", "Client name", "Date of Creation", "Date of Completion"};
        metaDataModel.setColumns(columns);
        int countOfTrips = trips.size();
        metaDataModel.setTotalCount((long) countOfTrips);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        trips.forEach(trip -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(trip.getId());
            row.add(trip.getRating());
            row.add(trip.getPrice() + "$");
            Address startAddress = trip.getStartAddress();
            row.add(startAddress.getCity() + ", " + startAddress.getStreet() + ", " + startAddress.getNumberHouse());
            Address finishAddress = trip.getFinishAddress();
            row.add(finishAddress.getCity() + ", " + finishAddress.getStreet() + ", " + finishAddress.getNumberHouse());
            Client client = trip.getClient();
            row.add(client.getFirstName() + " " + client.getLastName() + " " + client.getRating());
            row.add(formatDate(trip.getDateOfCreation()));
            Date finishDate = trip.getDateOfCompletion();
            if (finishDate == null) {
                row.add("-");
            } else {
                row.add(formatDate(finishDate));
            }
            data.add(row);
        });

        dataModel.setData(data);
        dataModel.setMetaData(metaDataModel);
        return new ResponseOrMessage<>(dataModel);
    }

    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.ENGLISH);
        return dateFormat.format(date);
    }
}
