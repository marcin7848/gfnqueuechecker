package com.gfnqueuechecker.backend;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ErrorMessage {

    public static ResponseEntity<?> send(String error, HttpStatus httpStatus) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("error", error);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(obj.toString(), httpStatus);
    }
}