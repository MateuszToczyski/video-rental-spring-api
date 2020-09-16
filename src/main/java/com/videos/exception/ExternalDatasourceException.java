package com.videos.exception;

public class ExternalDatasourceException extends RuntimeException {
    public ExternalDatasourceException() {
        super("Unable to fetch data from external source");
    }
}
