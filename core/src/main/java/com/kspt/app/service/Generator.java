package com.kspt.app.service;

import com.github.javafaker.Faker;
import com.kspt.app.configuration.Constants;
import com.kspt.app.entities.*;
import com.kspt.app.entities.actor.Client;
import com.kspt.app.entities.actor.Driver;
import com.kspt.app.repository.*;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by Masha on 15.04.2020
 */
@Service
public class Generator {
    Faker faker = new Faker();

    private int driversCount = 0;
    private int clientsCount = 0;
    private int tripsCount = 0;
    private int driversCountCatch = 0;
    private int clientsCountCatch = 0;
    private int tripsCountCatch = 0;

    private static int workload = 12;
    private ClientRepository clientRepository;
    private TripRepository tripRepository;
    private AddressRepository addressRepository;
    private DriverRepository driverRepository;
    private final CredentialsRepository credentialsRepository;
    private CarRepository carRepository;
    private final Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse("01.01.2018");

    Generator(ClientRepository clientRepository,
              DriverRepository driverRepository,
              CredentialsRepository credentialsRepository,
              TripRepository tripRepository,
              AddressRepository addressRepository,
              CarRepository carRepository) throws ParseException {
        this.clientRepository = clientRepository;
        this.driverRepository = driverRepository;
        this.credentialsRepository = credentialsRepository;
        this.addressRepository = addressRepository;
        this.tripRepository = tripRepository;
        this.carRepository = carRepository;
    }

    public String generateFakeDataForClient(int count) {
        for (int i = 0; i < count; i++) {
            try {
                Client client = new Client(faker.name().firstName(), faker.name().lastName());
                Credentials credentials = new Credentials(faker.book().title().replaceAll(" ", "") + "@mail.ru", hashPassword(faker.book().publisher()));
                client.setCredentials(credentialsRepository.save(credentials));
                client.setRating((double) ran(50) / 10);
                client.setDeleted(faker.bool().bool());
                client.setPhoneNumber(faker.phoneNumber().phoneNumber());
                client.setPassport(new Passport(randNumber(4020), randNumber(999999)));
                clientRepository.save(client);
                clientsCount++;
            } catch (Exception ex) {
                System.out.println(ex);
                clientsCountCatch++;
            }
        }
        return "Generate " + clientsCount + " fake clients\n" +
                "Catch  " + clientsCountCatch + " clients\n";
    }

    public String generateFakeDataForDriver(int count) {
        Constants.CarModels[] carModels = Constants.CarModels.values();
        Constants.Color[] colors = Constants.Color.values();
        int colorSize = colors.length - 1;
        int carModelsSize = carModels.length - 1;

        for (int i = 0; i < count; i++) {
            try {
                Driver driver = new Driver(faker.name().firstName(), faker.name().lastName());
                Credentials credentials = new Credentials(faker.harryPotter().spell().replaceAll(" ", "") + "@mail.ru", hashPassword(faker.harryPotter().location()));
                Car car = new Car(generateFakeNumber(), carModels[ran(carModelsSize)], colors[ran(colorSize)]);
                driver.setCredentials(credentialsRepository.save(credentials));
                driver.setDeleted(faker.bool().bool());
                driver.setRating((double) ran(50) / 10);
                driver.setPhoneNumber(faker.phoneNumber().phoneNumber());
                driver.setPassport(new Passport(randNumber(4020), randNumber(999999)));
                driver.setCar(carRepository.save(car));
                driverRepository.save(driver);
                driversCount++;
            } catch (Exception ex) {
                System.out.println(ex);
                driversCountCatch++;
            }
        }
        return "Generate " + driversCount + " fake drivers\n" +
                "Catch " + driversCountCatch + " drivers\n";
    }

    public int ran(int max) {
        return new Random().nextInt(max + 1);
    }

    public String generateFakeDataForTrip(int count) {
        for (int i = 0; i < count; i++) {
            try {
                Constants.Rate[] rates = Constants.Rate.values();
                Constants.Status[] status_ = Constants.Status.values();
                Constants.PaymentMethod[] paymentMethods = Constants.PaymentMethod.values();

                List<Client> clients = clientRepository.findAll();
                List<Driver> drivers = driverRepository.findAll();

                int countOfClient = (int) clientRepository.count() - 1;
                int countOfDrivers = (int) driverRepository.count() - 1;

                int ratesSize = rates.length - 1 ;
                int statusSize = status_.length - 1;
                int paymentMethodsSize = paymentMethods.length - 1;

                Constants.Status status = status_[ran(statusSize)];

                Trip trip = new Trip();
                trip.setClient(clients.get(ran(countOfClient)));
                trip.setStatus(status);
                trip.setPaymentMethod(paymentMethods[ran(paymentMethodsSize)]);
                trip.setTripRate(rates[ran(ratesSize)]);
                trip.setStartAddress(addressRepository.save(new Address(faker.address().city(), faker.address().streetAddress(), Integer.parseInt(faker.address().buildingNumber()))));
                trip.setFinishAddress(addressRepository.save(new Address(faker.address().city(), faker.address().streetAddress(), Integer.parseInt(faker.address().buildingNumber()))));
                Date dateOfCreation = between(startDate, new Date());
                trip.setDateOfCreation(dateOfCreation);
                trip.setPrice(Double.parseDouble(randNumber(500)));

                switch (status) {
                    case START:
                        trip.setDriver(drivers.get(ran(countOfDrivers)));
                        break;

                    case FINISH:
                        trip.setDriver(drivers.get(ran(countOfDrivers)));
                        trip.setDateOfCompletion(between(dateOfCreation, new Date()));
                        trip.setRating(ran(5));
                        break;

                    case DENY:
                        trip.setDateOfCompletion(between(dateOfCreation, new Date()));
                        break;

                    default:
                        break;

                }
                tripRepository.save(trip);
                tripsCount++;
            } catch (Exception ex) {
                System.out.println(ex);
                tripsCountCatch++;
            }
        }

        return "Generate " + tripsCount + " fake trips\n" +
                "Catch " + tripsCountCatch + " trips\n";
    }

    public String generateFakeNumber() {
        String result = "";
        Random rnd = new Random();
        result += (char) (rnd.nextInt(26) + 'a');
        int number = ran(999);
        if (number < 10) {
            result += "00" + number;
        } else if (number < 100) {
            result += "0" + number;
        } else {
            result += number;
        }
        char b = (char) (rnd.nextInt(26) + 'a');
        char d = (char) (rnd.nextInt(26) + 'a');
        return result + b + d;
    }

    private String randNumber(int num) {
        return String.valueOf(((int) (Math.random() * num - 1)));
    }

    public static Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        return (BCrypt.hashpw(password_plaintext, salt));
    }
}
