package com.kspt.app.entities;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * Created by Masha on 07.03.2020
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "Credentials")
@EqualsAndHashCode(callSuper = true)
public final class Credentials extends AbstractEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public Credentials(@NotNull final String login,
                       @NotNull final String password) {
        this.login = login;
        this.password = password;
    }

}
