package org.lab.lottery.blogic;

public class NotEnoughParticipantsException extends RuntimeException {
    public NotEnoughParticipantsException(String message) {
        super(message);
    }
}
