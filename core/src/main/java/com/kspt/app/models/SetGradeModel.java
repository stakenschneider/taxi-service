package com.kspt.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 07.04.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SetGradeModel {
    Long tripId;
    int grade;
}
