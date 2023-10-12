package com.geobyte.geobyteinc.security;

public class LocationAlreadyExistException extends RuntimeException {
    public LocationAlreadyExistException(String message) {
        super(message);
    }
}
