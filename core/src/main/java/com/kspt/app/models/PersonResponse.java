package com.kspt.app.models;

import com.kspt.app.entities.actor.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Masha on 10.03.2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonResponse extends ApiResult {

    private Person person;

    public PersonResponse(String error) {
        this.message = error;
    }

    public PersonResponse(Person person) {
        this.person = person;
    }
}
