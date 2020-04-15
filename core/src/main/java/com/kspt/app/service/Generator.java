package com.kspt.app.service;

import com.github.javafaker.Faker;
import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.*;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.repository.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created by Masha on 15.04.2020
 */
@Service
public class Generator {
    Faker faker = new Faker();

    private ClientRepository clientRepository;
    private TripRepository tripRepository;
    private AddressRepository addressRepository;
    private DriverRepository driverRepository;
    private AdminRepository adminRepository;
    private final CredentialsRepository credentialsRepository;
    private CarRepository carRepository;

    Generator(ClientRepository clientRepository,
              DriverRepository driverRepository,
              AdminRepository adminRepository,
              CredentialsRepository credentialsRepository,
              TripRepository tripRepository,
              AddressRepository addressRepository,
              CarRepository carRepository) {
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.adminRepository = adminRepository;
        this.credentialsRepository = credentialsRepository;
        this.addressRepository = addressRepository;
        this.tripRepository = tripRepository;
        this.carRepository = carRepository;
    }

    private String randNumber(int num) {
        return String.valueOf(((int) (Math.random() * num - 1)));
    }

    public void generateFakeDataForClient(int count) {
        for (int i = 0; i < count; i++) {
            try {
                Client client = new Client(faker.name().firstName(), faker.name().lastName());
                Credentials credentials = new Credentials(faker.animal().name() + "@mail.ru", faker.code().isbn13());
                client.setCredentials(credentialsRepository.save(credentials));
                client.setDeleted(faker.bool().bool());
                client.setPhoneNumber(faker.phoneNumber().phoneNumber());
                client.setPassport(new Passport(randNumber(4020), randNumber(999999)));
                clientRepository.save(client);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void generateFakeDataForDriver(int count) {
        for (int i = 0; i < count; i++) {
            try {

                Driver driver = new Driver(faker.name().firstName(), faker.name().lastName());
                Credentials credentials = new Credentials(faker.ancient().god() + "@mail.ru", faker.ancient().hero());
                driver.setCredentials(credentialsRepository.save(credentials));
                driver.setDeleted(faker.bool().bool());
                driver.setPhoneNumber(faker.phoneNumber().phoneNumber());
                driver.setPassport(new Passport(randNumber(4020), randNumber(999999)));
                String carNumber = faker.code().isbn10();
                Constants.CarModels[] carModels = Constants.CarModels.values();
                int carmodelsSize = carModels.length;
                Constants.CarModels carModel = carModels[ran(carmodelsSize)];
                Constants.Color[] colors = Constants.Color.values();
                int colorSize = colors.length;
                Constants.Color color = colors[ran(colorSize)];
                Car car = new Car(carNumber, carModel, color);
                driver.setCar(carRepository.save(car));
                driverRepository.save(driver);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public int ran(int max) {
        return new Random().nextInt(max + 1);
    }

    public void generateFakeDataForTrip(int count) {
        for (int i = 0; i < count; i++) {
            try {
                Constants.Rate[] rates = Constants.Rate.values();
                Constants.Status[] status = Constants.Status.values();
                Constants.PaymentMethod[] paymentMethods = Constants.PaymentMethod.values();

                List<Client> clients = clientRepository.findAll();
                List<Driver> drivers = driverRepository.findAll();

                int countOfClient = (int) clientRepository.count() - 1;
                int countOfDrivers = (int) driverRepository.count() - 1;

                int ratesSize = rates.length;
                int statusSize = status.length;
                int paymentMethodsSize = paymentMethods.length;

                Trip trip = new Trip();
                trip.setDriver(drivers.get(ran(countOfDrivers)));
                trip.setClient(clients.get(ran(countOfClient)));
                trip.setStatus(status[ran(statusSize)]);
                trip.setPaymentMethod(paymentMethods[ran(paymentMethodsSize)]);
                trip.setTripRate(rates[ran(ratesSize)]);
                trip.setStartAddress(addressRepository.save(new Address(faker.address().city(), faker.address().streetAddress(), Integer.parseInt(faker.address().buildingNumber()))));
                trip.setFinishAddress(addressRepository.save(new Address(faker.address().city(), faker.address().streetAddress(), Integer.parseInt(faker.address().buildingNumber()))));
                trip.setDateOfCompletion(new Date());
                trip.setDateOfCreation(new Date());
                trip.setPrice(Double.parseDouble(randNumber(500)));
                tripRepository.save(trip);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
}
