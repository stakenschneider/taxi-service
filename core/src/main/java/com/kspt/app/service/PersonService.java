package com.kspt.app.service;

import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.RegistrationModel;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.CredentialsRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Masha on 10.03.2020
 */
@Service
public class PersonService {

    private ClientRepository clientRepository;
    private DriverRepository driverRepository;
    private AdminRepository adminRepository;
    private final CredentialsRepository credentialsRepository;

    PersonService(ClientRepository clientRepository,
                  DriverRepository driverRepository,
                  AdminRepository adminRepository,
                  CredentialsRepository credentialsRepository) {
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.adminRepository = adminRepository;
        this.credentialsRepository = credentialsRepository;
    }

    public ResponseOrMessage<Boolean> signUp(RegistrationModel model) {
        final Credentials credentials = new Credentials(model.getEmail(),
                model.getPassword(),
                model.getEmail().split("@")[0]);
        try {
            switch (model.getPersonType()){
                case CLIENT:
                    Client client = new Client(model.getFirstName(), model.getLastName());
                    client.setCredentials(credentials);
                    clientRepository.save(client);
                    return new ResponseOrMessage<>(true);
                case DRIVER:
                    Driver driver = new Driver(model.getFirstName(), model.getLastName());
                    driver.setCredentials(credentials);
                    driverRepository.save(driver);
                    return new ResponseOrMessage<>(true);
                default: return new ResponseOrMessage<>("Wrong parameter");
            }
        } catch (Exception e) {
            return new ResponseOrMessage<>("Login already exist");
        }

    }


//    public ResponseOrMessage<Person> signUp(RegistrationModel model) {
//        final Credentials credentials = new Credentials(model.getLogin(), model.getPassword());
//        try {
//            switch (model.getPersonType()) {
//                case CLIENT: {
//                    Client client = new Client(model.getFirstName(), model.getSecondName(),
//                            model.getPhoneNumber());
//                    client.setCredentials(credentials);
//                    clientRepository.save(client);
//                    return new ResponseOrMessage(client);
//                }
//
//                case DRIVER: {
//                    Driver driver = new Driver(model.getFirstName(), model.getSecondName(),
//                            model.getPhoneNumber());
//                    driver.setCredentials(credentials);
//                    driverRepository.save(driver);
//                    return new ResponseOrMessage(driver);
//                }
//                case ADMIN: {
//                    Admin admin = new Admin(model.getFirstName(), model.getSecondName(),
//                            model.getPhoneNumber());
//                    admin.setCredentials(credentials);
//                    adminRepository.save(admin);
//                    return new ResponseOrMessage(admin);
//                }
//                default:
//                    return null;
//            }
//        } catch (Exception e) {
//            return new ResponseOrMessage("Login already exist");
//        }
//    }

    public ResponseOrMessage<Person> signIn(Map<String, String> emailOrUserName) {
        if (emailOrUserName.containsKey("emailOrUserName")) {
            final Credentials credentials = credentialsRepository.findByEmail(emailOrUserName.get("emailOrUserName"))
                    .orElseGet(()-> credentialsRepository.findByUsername(emailOrUserName.get("emailOrUserName")).orElse(null));
            //TODO Polymorphic Queries
            if (credentials != null) {
                return new ResponseOrMessage<>(clientRepository.findByCredentials(credentials).orElseGet(
                        () -> adminRepository.findByCredentials(credentials).orElseGet(
                                () -> driverRepository.findByCredentials(credentials).orElse(null))));
            } else return new ResponseOrMessage<>("Incorrect username");
        } else return new ResponseOrMessage<>("Wrong parameter");
    }

    public Boolean signOut() {
        return true;
    }

    public ResponseOrMessage<Person> getPersonById(Map<String, Long> id) {
        if (id.containsKey("id")) {
            Long personId = id.get("id");
            //TODO Polymorphic Queries

            Person person = clientRepository.findById(personId).orElse(null);
            if (person == null){
                person = driverRepository.findById(personId).orElse(null);
                if (person == null){
                    person = adminRepository.findById(personId).orElse(null);
                }
            }
            if (person == null) {
                return new ResponseOrMessage<>("Person not found");
            } else return new ResponseOrMessage<>(person);
        } else return new ResponseOrMessage<>("Wrong parameter");
    }
}
