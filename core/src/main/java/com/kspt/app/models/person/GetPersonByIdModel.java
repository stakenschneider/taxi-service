package com.kspt.app.models.person;

import com.kspt.app.configuration.Constants.PersonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 03.04.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class GetPersonByIdModel {
    Long personId;
    PersonType personType;
}
