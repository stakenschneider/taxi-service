package com.kspt.app.entities;

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
@Table(name = "Address")
@EqualsAndHashCode(callSuper = true)
class Address extends AbstractEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_house")
    private int numberHouse;

    public Address(String city, String street, int numberHouse) {
        this.city = city;
        this.street = street;
        this.numberHouse = numberHouse;
    }

}

