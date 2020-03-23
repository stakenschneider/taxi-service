package com.kspt.app.entities;

/**
 * Created by Masha on 22.03.2020
 */
public class Enums {
    public enum PersonType {
        CLIENT,
        DRIVER,
        ADMIN
    }

    public enum Color {
        WHITE,
        BLACK,
        GRAY,
        YELLOW,
        RED,
        GREEN,
        ORANGE,
        BLUE,
        PURPLE
    }

    public enum Status {
        CREATE,
        START,
        FINISH,
        DENY
    }

    public enum Rate {
        ECONOMY,
        COMFORT,
        LUX,
        GOLD,
        GOD,
    }

    public enum CarModels {
        ACURA, MERCEDES, LEXUS, TESLA,
        BMW, MITSUBISHI,
        AUDI, TOYOTA, VOLKSWAGEN, VOLVO,
        FORD, HONDA, HYUNDAI, KIA, CHEVROLET
    }

    public enum PaymentMethod {
        CASH,
        CARD,
        APPLE_PAY
    }
}
