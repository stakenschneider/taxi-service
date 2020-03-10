package com.kspt.app.service;

import com.kspt.app.entities.*;
import com.kspt.app.models.RegistrationModel;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.OperatorRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Masha on 10.03.2020
 */
@Service
public class PersonService {

    private ClientRepository clientRepository;
    private DriverRepository driverRepository;
    private OperatorRepository operatorRepository;

    PersonService(ClientRepository clientRepository,
                  DriverRepository driverRepository,
                  OperatorRepository operatorRepository) {
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.operatorRepository = operatorRepository;
    }


    public Person register(RegistrationModel model){
        final Credentials credentials = new Credentials(model.getLogin(), model.getPassword());

        switch (model.getPersonType()) {
            case CLIENT: {
                Client client = new Client(model.getFirstName(), model.getSecondName(),
                        model.getPersonType(),model.getPassportCode(),model.getPhoneNumber());
                client.setCredentials(credentials);
                return clientRepository.save(client);
            }

            case DRIVER: {
                Driver driver = new Driver(model.getFirstName(), model.getSecondName(),
                        model.getPersonType(),model.getPassportCode(),model.getPhoneNumber());
                driver.setCredentials(credentials);
                return driverRepository.save(driver);
            }
            case OPERATOR: {
                Operator operator = new Operator(model.getFirstName(), model.getSecondName(),
                        model.getPersonType(),model.getPassportCode(),model.getPhoneNumber());
                operator.setCredentials(credentials);
                return operatorRepository.save(operator);
            }
            default:
                return null;
        }
    }
}
