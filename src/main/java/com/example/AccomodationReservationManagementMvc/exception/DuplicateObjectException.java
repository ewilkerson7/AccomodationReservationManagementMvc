package com.example.AccomodationReservationManagementMvc.exception;

public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException(String message) {
        super(message);
    }
}