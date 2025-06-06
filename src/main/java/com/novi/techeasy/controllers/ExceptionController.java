package com.novi.techeasy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.security.PublicKey;

    @ControllerAdvice
    public class ExceptionController {

        @ExceptionHandler(IndexOutOfBoundsException.class)
        public ResponseEntity<String> handleIndexOutOfBoundsException(IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Televisie is niet gevonden");
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGenericException(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Er gaat iets mis!");
        }
    }