package com.videos.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Unable to find the resource");
    }
}
