package com.kspt.app.service;

import com.kspt.app.models.table.GetDataModel;
import com.kspt.app.models.table.GridDataModel;
import com.kspt.app.providers.IDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Masha on 18.04.2020
 */
@Service
public class GetDataService {
    @Autowired
    private ApplicationContext _applicationContext;

    public GridDataModel getData(GetDataModel model){
        IDataProvider iDataProvider = _applicationContext.getBean(model.dataName, IDataProvider.class);
        return iDataProvider.getData(model.parameters);
    }
}
