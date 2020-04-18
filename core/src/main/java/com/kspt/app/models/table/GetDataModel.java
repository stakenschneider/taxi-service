package com.kspt.app.models.table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
@Data
@NoArgsConstructor
//@EqualsAndHashCode()
public class GetDataModel {
    public String dataName;
    public Map<String, Object> parameters;
}
