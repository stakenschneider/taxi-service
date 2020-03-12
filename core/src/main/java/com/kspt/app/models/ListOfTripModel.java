package com.kspt.app.models;

import com.kspt.app.entities.Trip;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by Masha on 12.03.2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ListOfTripModel extends ApiResult {
    private List<Trip> list;

    public ListOfTripModel(String error) {
        this.message = error;
    }

    public ListOfTripModel(List<Trip> list) {
        this.list = list;
    }
}
