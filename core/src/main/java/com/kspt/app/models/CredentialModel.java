package com.kspt.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;

/**
 * Created by Masha on 10.03.2020
 */
@Data
@NoArgsConstructor
public class CredentialModel {
    private String login;
    private String password;

    public CredentialModel(@NotNull final String login,
                       @NotNull final String password) {
        this.login = login;
        this.password = password;
    }
}
