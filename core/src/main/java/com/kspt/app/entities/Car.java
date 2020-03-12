package com.kspt.app.entities;


import com.kspt.app.configuration.Constants.CarModels;
import com.kspt.app.configuration.Constants.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Masha on 27.02.2020
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Car")
@EqualsAndHashCode(callSuper = true)
public class Car extends AbstractEntity{

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "model", nullable = false)
    private CarModels model;

    @Column(name = "color", nullable = false)
    private Color color;
}
