package com.kspt.app.models;

import com.kspt.app.configuration.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 10.03.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PassportModel {
    private String series;
    private String number;
    private Long id;
}
