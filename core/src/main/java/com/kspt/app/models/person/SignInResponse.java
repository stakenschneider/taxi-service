package com.kspt.app.models.person;

import com.kspt.app.configuration.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 25.04.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SignInResponse {
    Long personId;
    Constants.PersonType personType;
    String password;
}