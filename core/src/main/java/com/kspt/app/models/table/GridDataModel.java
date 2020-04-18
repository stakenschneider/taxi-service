package com.kspt.app.models.table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by Masha on 18.04.2020
 */
@Data
@NoArgsConstructor
public class GridDataModel {
    public ArrayList<ArrayList<Object>> data;
    public MetaDataModel metaData;
}
