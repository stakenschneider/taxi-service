package com.kspt.app.models.info;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 10.03.2020
 */
@Data
@NoArgsConstructor
public class CredentialModel {
    private String email;
    private String password;
}
