package com.kspt.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 03.04.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class EndTripModel {
    Long tripId;
    Double grade;
}
