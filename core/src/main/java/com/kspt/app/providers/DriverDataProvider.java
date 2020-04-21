package com.kspt.app.providers;

import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.table.GridDataModel;
import com.kspt.app.models.table.MetaDataModel;
import com.kspt.app.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
@Component
@Qualifier("driverTable")
public class DriverDataProvider implements IDataProvider {
    DriverRepository driverRepository;


    @Override
    public ResponseOrMessage<GridDataModel> getData(Map<String, Object> parameters) {
        List<Driver> drivers = driverRepository.findAll();
        if (drivers.isEmpty()) {
            return new ResponseOrMessage<>("Drivers not found");
        }
        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();

        String[] columns = {"No.", "First Name", "Last Name", "Email","Login", "Phone Number", "Rating","Deleted", "Passport", "Car"};
        metaDataModel.setColumns(columns);
        int countOfDrivers = drivers.size();
        metaDataModel.setTotalCount(countOfDrivers);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        drivers.forEach(driver -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(driver.getId());
            row.add(driver.getFirstName());
            row.add(driver.getLastName());
            row.add(driver.getCredentials().getEmail());
            row.add(driver.getCredentials().getUsername());
            row.add(driver.getPhoneNumber());
            row.add(driver.getRating());
            row.add(driver.isDeleted());
            row.add(driver.getPassport());
            row.add(driver.getCar().getColor()+" "+driver.getCar().getModel()+" "+driver.getCar().getNumber());
            data.add(row);
        });
        dataModel.setData(data);
        dataModel.setMetaData(metaDataModel);
        return new ResponseOrMessage<>(dataModel);
    }
}