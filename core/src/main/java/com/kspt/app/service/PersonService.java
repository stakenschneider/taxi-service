package com.kspt.app.service;

import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.entities.actor.Operator;
import com.kspt.app.models.CredentialModel;
import com.kspt.app.models.PersonResponse;
import com.kspt.app.models.RegistrationModel;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.CredentialsRepository;
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
    private final CredentialsRepository credentialsRepository;

    PersonService(ClientRepository clientRepository,
                  DriverRepository driverRepository,
                  OperatorRepository operatorRepository,
                  CredentialsRepository credentialsRepository) {
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.operatorRepository = operatorRepository;
        this.credentialsRepository = credentialsRepository;
    }

    public PersonResponse signUp(RegistrationModel model) {
        final Credentials credentials = new Credentials(model.getLogin(), model.getPassword());

        try {
            //TODO Polymorphic Queries
            switch (model.getPersonType()) {
                case CLIENT: {
                    Client client = new Client(model.getFirstName(), model.getSecondName(),
                            model.getPhoneNumber());
                    client.setCredentials(credentials);
                    clientRepository.save(client);
                    return new PersonResponse(client);
                }

                case DRIVER: {
                    Driver driver = new Driver(model.getFirstName(), model.getSecondName(),
                            model.getPhoneNumber());
                    driver.setCredentials(credentials);
                    driverRepository.save(driver);
                    return new PersonResponse(driver);
                }
                case OPERATOR: {
                    Operator operator = new Operator(model.getFirstName(), model.getSecondName(),
                            model.getPhoneNumber());
                    operator.setCredentials(credentials);
                    operatorRepository.save(operator);
                    return new PersonResponse(operator);
                }
                default:
                    return null;
            }
        } catch (Exception e) {
            return new PersonResponse("Login already exist");
        }
    }

    public PersonResponse signIn(CredentialModel model) {
        final Credentials credentials = credentialsRepository.findByLoginAndPassword(
                model.getLogin(),
                model.getPassword()).orElse(null);

//        EntityManager em = entityManagerFactory.createEntityManager();
//        em.getTransaction().begin();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//
//        CriteriaQuery<Credentials> credentialsCriteriaQuery = cb.createQuery(Credentials.class);
//        Root<Person> personPassportRoot = credentialsCriteriaQuery.from(Person.class);
//        credentialsCriteriaQuery.select(personPassportRoot.get("passport"));
//        em.createQuery(credentialsCriteriaQuery)
//                .getResultList();

        //TODO Polymorphic Queries
        if (credentials != null)
            return new PersonResponse(clientRepository.findByCredentials(credentials).orElseGet(
                    () -> operatorRepository.findByCredentials(credentials).orElseGet(
                            () -> driverRepository.findByCredentials(credentials).orElse(null))));
        else return new PersonResponse("Incorrect username or password");
    }

    public Boolean signOut(){ return true; }
}
