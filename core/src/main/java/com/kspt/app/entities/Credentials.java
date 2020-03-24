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

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    public Credentials(@NotNull final String email,
                       @NotNull final String password,
                       @NotNull final String username) {
        this.email = email;
        this.password = password;
        this.username = username;

    }
}
