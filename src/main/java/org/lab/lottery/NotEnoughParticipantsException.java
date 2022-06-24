package org.lab.lottery;

public class NotEnoughParticipantsException extends RuntimeException {
    public NotEnoughParticipantsException(String message) {
        super(message);
    }
}
