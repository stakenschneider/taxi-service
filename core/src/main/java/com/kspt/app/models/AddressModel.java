package com.kspt.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 12.03.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class AddressModel {
    private String city;
    private String street;
    private int numberHouse;
}
