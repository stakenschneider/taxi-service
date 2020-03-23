package com.kspt.app.service;

import com.kspt.app.entities.Credentials;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Person;
import com.kspt.app.models.CredentialModel;
import com.kspt.app.models.RegistrationModel;
import com.kspt.app.models.ResponseOrMessage;
import com.kspt.app.repository.ClientRepository;
import com.kspt.app.repository.CredentialsRepository;
import com.kspt.app.repository.DriverRepository;
import com.kspt.app.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Masha on 10.03.2020
 */
@Service
public class PersonService implements UserDetailsService {

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

    public ResponseOrMessage<Person> signUp(RegistrationModel model) {
        final Credentials credentials = new Credentials(model.getUsername(), model.getPassword());

        try {
            Client client = new Client(model.getFirstName(), model.getLastName());
            client.setCredentials(credentials);
            clientRepository.save(client);
            return new ResponseOrMessage<Person>(client);
        } catch (Exception e) {
            return new ResponseOrMessage<Person>("Login already exist");
        }

    }


//    public ResponseOrMessage<Person> signUp(RegistrationModel model) {
//        final Credentials credentials = new Credentials(model.getLogin(), model.getPassword());
//
//        try {
//            //TODO Polymorphic Queries
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

    public ResponseOrMessage<Person> signIn(CredentialModel model) {
        final Credentials credentials = credentialsRepository.findByUsernameAndPassword(
                model.getUsername(),
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
            return new ResponseOrMessage(clientRepository.findByCredentials(credentials).orElseGet(
                    () -> adminRepository.findByCredentials(credentials).orElseGet(
                            () -> driverRepository.findByCredentials(credentials).orElse(null))));
        else return new ResponseOrMessage("Incorrect username or password");
    }

    public Boolean signOut() {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialsRepository.findByUsername(username).
                orElseThrow(()->{throw new UsernameNotFoundException("User not found");});

    }
}
