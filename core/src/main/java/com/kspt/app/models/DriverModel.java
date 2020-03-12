package com.kspt.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 12.03.2020
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class DriverModel extends ApiResult{
    protected String firstName;
    protected String secondName;
}
