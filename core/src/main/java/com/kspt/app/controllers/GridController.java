package com.kspt.app.controllers;

import com.kspt.app.models.table.GetDataModel;
import com.kspt.app.models.table.GridDataModel;
import com.kspt.app.service.GetDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Masha on 18.04.2020
 */
@RestController
public class GridController {
    @Autowired
    private GetDataService getDataService;

    @PostMapping("/getGridData")
    public GridDataModel getData(@RequestBody GetDataModel model) {
        return getDataService.getData(model);
    }
}
