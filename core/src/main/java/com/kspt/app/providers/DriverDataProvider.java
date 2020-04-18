package com.kspt.app.providers;

import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.table.GridDataModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
@Component
@Qualifier("driverTable")
public class DriverDataProvider implements IDataProvider {
    @Override
    public ResponseOrMessage<GridDataModel> getData(Map<String, Object> parameters) {
        return null;
    }
}