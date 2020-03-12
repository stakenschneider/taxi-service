package com.kspt.app.models;

import com.kspt.app.entities.Trip;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 12.03.2020
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TripModel extends ApiResult{

    Trip trip;

    public TripModel(Trip trip){
        this.trip = trip;
    }
    public TripModel(String error){
        this.message = error;
    }
}
