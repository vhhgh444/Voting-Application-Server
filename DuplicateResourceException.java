package com.voting.votingApplication.exception;

public class DuplicateResourceException extends RuntimeException{
    public DuplicateResourceException(String message) {
        super(message);
    }
}
