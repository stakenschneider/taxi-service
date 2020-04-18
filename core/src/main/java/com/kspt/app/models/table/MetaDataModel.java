package com.kspt.app.models.table;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Masha on 18.04.2020
 */
@Data
@NoArgsConstructor
public class MetaDataModel {
    public String[] columns;
    public int totalCount; //for pagination
//    TODO add more parameters. think about it
}
