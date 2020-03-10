package com.kspt.app.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Masha on 10.03.2020
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="Passport")
public class Passport extends AbstractEntity {
    @Column(name = "series")
    private int series;

    @Column(name = "number")
    private int number;

    public Passport(final int series,
                    final int number) {
        this.series = series;
        this.number = number;
    }
}
