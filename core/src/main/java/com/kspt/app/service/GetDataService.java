package com.kspt.app.service;

import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.table.GetDataModel;
import com.kspt.app.models.table.GridDataModel;
import com.kspt.app.providers.IDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by Masha on 18.04.2020
 */
@Service
public class GetDataService {
    @Autowired
    private ApplicationContext _applicationContext;

    public ResponseOrMessage<GridDataModel> getData(GetDataModel model){
        if (!model.parameters.containsKey("page") ||
                !model.parameters.containsKey("size") ||
                !model.parameters.containsKey("sortBy")) {
            return new ResponseOrMessage<>("Wrong parameter \"page\" or \"size\"or \"sortBy\"");
        }

        int currentPageNumber = (int) model.parameters.get("page");
        int pageSize = (int) model.parameters.get("size");
        Sort sortBy = Sort.by((String) model.parameters.get("sortBy"));
        Pageable pageable = PageRequest.of(currentPageNumber,pageSize, sortBy);

        IDataProvider iDataProvider =
                BeanFactoryAnnotationUtils.qualifiedBeanOfType(_applicationContext.getAutowireCapableBeanFactory(),
                        IDataProvider.class, model.dataName);
        return iDataProvider.getData(model.parameters, pageable);
    }
}
