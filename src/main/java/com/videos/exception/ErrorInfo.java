package com.videos.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorInfo {
    private final String url;
    private final String message;
    private final LocalDateTime time = LocalDateTime.now();

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }
}
