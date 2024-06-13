package com.davinci.spotifake.Service.ErrorHandler;

public class ExceptionSpotifake extends RuntimeException{

        public ExceptionSpotifake(String message) {
            super(message);
        }

        public static ExceptionSpotifake throwBadRequestException(String message) {
        throw new ExceptionSpotifake(message);
        }

    }

