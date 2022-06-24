package org.lab.lottery.web;

import org.lab.lottery.LotteryService;
import org.lab.lottery.NotEnoughParticipantsException;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LotteryController {
    private final LotteryService lotteryService;

    @Autowired
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lottery/participant")
    List<Participant> getParticipants() {
        return lotteryService.getParticipants();
    }

    @PostMapping("/lottery/participant")
    Participant getParticipants(Participant participant) {
        return lotteryService.registerParticipant(participant);
    }

    @GetMapping("/lottery/start")
    Winner play() {
        return lotteryService.play();
    }

    @GetMapping("/lottery/winners")
    List<Winner> winners() {
        return lotteryService.getWinners();
    }

    @ExceptionHandler(NotEnoughParticipantsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            NotEnoughParticipantsException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(exception.getMessage());
    }
}
