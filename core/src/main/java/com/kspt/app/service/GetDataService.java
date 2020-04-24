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

    public ResponseOrMessage<GridDataModel> getData(GetDataModel model) {
        if (!model.parameters.containsKey("page") ||
                !model.parameters.containsKey("size") ||
                !model.parameters.containsKey("sortBy") ||
                !model.parameters.containsKey("ariaSort")) {
            return new ResponseOrMessage<>("Wrong parameter \"page\" or \"size\" or \"sortBy\" or \"ariaSort\"");
        }

//        TODO таблица соответствий названий колонки на ui и название из бд
        int currentPageNumber = (int) model.parameters.get("page");
        int pageSize = (int) model.parameters.get("size");

        String ariaSort = (String) model.parameters.get("ariaSort");
        Sort sortBy;
        if (ariaSort == null){
            sortBy = Sort.by((String) model.parameters.get("sortBy")).ascending();
        } else if (ariaSort.equals("ascending")) {
            sortBy = Sort.by((String) model.parameters.get("sortBy")).descending();
        } else {
            sortBy = Sort.by((String) model.parameters.get("sortBy")).ascending();
        }

        Pageable pageable = PageRequest.of(currentPageNumber, pageSize, sortBy);

        IDataProvider iDataProvider =
                BeanFactoryAnnotationUtils.qualifiedBeanOfType(_applicationContext.getAutowireCapableBeanFactory(),
                        IDataProvider.class, model.dataName);
        return iDataProvider.getData(model.parameters, pageable);
    }
}
