package com.kspt.app.providers;

import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.table.GridDataModel;

import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
public interface IDataProvider {
    ResponseOrMessage<GridDataModel> getData(Map<String, Object> parameters);
}
