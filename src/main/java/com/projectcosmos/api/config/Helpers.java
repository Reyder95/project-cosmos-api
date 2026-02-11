package com.projectcosmos.api.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Helpers {
    public ResponseEntity<Map<String, Object>> createResponseEntity(boolean success, Object data, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();

        response.put("success", success);
        response.put("data", data);

        if (message != null && !message.isEmpty())
            response.put("message", message);

        return ResponseEntity.status(status).body(response);
    }
}
