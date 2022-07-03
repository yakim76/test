package org.lab.lottery.web;

import org.lab.lottery.blogic.SimpleLotteryService;
import org.lab.lottery.blogic.NotEnoughParticipantsException;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LotteryController implements LotteryAPI {
    private final SimpleLotteryService lotteryService;

    @Autowired
    public LotteryController(SimpleLotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Override
    public List<Participant> getParticipants() {
        return lotteryService.getParticipants();
    }

    @Override
    public Participant getParticipants(Participant participant) {
        return lotteryService.registerParticipant(participant);
    }

    @Override
    public Winner play() {
        return lotteryService.play();
    }

    @Override
    public List<Winner> winners() {
        return lotteryService.getWinners();
    }

    @Override
    public ResponseEntity<String> handleNoSuchElementFoundException(
        NotEnoughParticipantsException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(exception.getMessage());
    }
}
