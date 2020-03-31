package com.kspt.app.configuration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Masha on 01.04.2020
 */
@RestController
public class SSEController {
// SSE for all user TODO send events to concrete user (client)
    public static final List<SseEmitter> emitters = Collections.synchronizedList( new ArrayList<>());

    @RequestMapping(path = "/stream", method = RequestMethod.GET)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        return emitter;
    }
}
