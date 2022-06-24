package org.lab.lottery;

import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LotteryService {
    private final ParticipantRepository participantRepository;
    private final WinnerRepository winnerRepository;
    private final RandomGenerator randomGenerator;

    @Autowired
    public LotteryService(ParticipantRepository participantRepository,
                          WinnerRepository winnerRepository,
                          RandomGenerator randomGenerator) {
        this.participantRepository = participantRepository;
        this.winnerRepository = winnerRepository;
        this.randomGenerator = randomGenerator;
    }

    public Participant registerParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Winner play() throws NotEnoughParticipantsException {
        List<Participant> participants = participantRepository.findAll();
        if (participants.size() < 2) {
            throw new NotEnoughParticipantsException(
                    String.format("In order to play you have to have 2 participants at least. Now we have %d only",
                            participants.size()));
        }
        int prize = randomGenerator.randomInRange(1, 1000);
        int winnerNumber = randomGenerator.randomInRange(0, participants.size() - 1);
        Winner winner = new Winner(participants.get(winnerNumber), prize);
        winnerRepository.save(winner);
        participantRepository.deleteAll();
        return winner;
    }

    public List<Winner> getWinners() {
        return winnerRepository.findAll();
    }
}
