package com.kspt.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.kspt.app.configuration.Constants.PersonType;

/**
 * Created by Masha on 10.03.2020
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class RegistrationModel extends CredentialModel{
    protected String firstName;
    protected String lastName;
}
