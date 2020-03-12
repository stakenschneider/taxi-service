package com.kspt.app.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Masha on 12.03.2020
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ResponseOrMessage <T> extends ApiResult{
    T ob;

    public ResponseOrMessage(T ob) {
        this.ob = ob;
    }

    public ResponseOrMessage(String error){
        this.message = error;
    }
}
