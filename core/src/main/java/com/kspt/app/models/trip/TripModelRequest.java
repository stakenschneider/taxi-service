package com.kspt.app.models.trip;

import com.kspt.app.configuration.Constants.PaymentMethod;
import com.kspt.app.configuration.Constants.Rate;
import com.kspt.app.models.info.AddressModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 11.03.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TripModelRequest {
    private Long clientId;
    private AddressModel startAddress;
    private AddressModel finishAddress;
    private Rate rate;
    private PaymentMethod paymentMethod;
}
