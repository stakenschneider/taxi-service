package com.kspt.app.models;

import com.kspt.app.configuration.Constants.CarModels;
import com.kspt.app.configuration.Constants.Color;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 12.03.2020
 */
@Data
@NoArgsConstructor
public class CarModel {
    private String carNumber;
    private CarModels model;
    private Color color;
    private Long driverId;
}
