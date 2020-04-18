package com.kspt.app.providers;

import com.kspt.app.models.table.GridDataModel;

import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
public interface IDataProvider {
    GridDataModel getData(Map<String, Object> parameters);
}
