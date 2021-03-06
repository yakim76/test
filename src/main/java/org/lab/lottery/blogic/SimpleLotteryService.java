package org.lab.lottery.blogic;

import org.lab.lottery.model.ParticipantRepository;
import org.lab.lottery.model.WinnerRepository;
import org.lab.lottery.config.PrizeRange;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SimpleLotteryService implements LotteryService {
    private final ParticipantRepository participantRepository;
    private final WinnerRepository winnerRepository;
    private final RandomGenerator randomGenerator;
    private final PrizeRange prizeRange;

    @Autowired
    public SimpleLotteryService(ParticipantRepository participantRepository, WinnerRepository winnerRepository, RandomGenerator randomGenerator, PrizeRange prizeRange) {
        this.participantRepository = participantRepository;
        this.winnerRepository = winnerRepository;
        this.randomGenerator = randomGenerator;
        this.prizeRange = prizeRange;
    }

    @Override
    public Participant registerParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    @Override
    public Winner play() throws NotEnoughParticipantsException {
        List<Participant> participants = participantRepository.findAll();
        if (participants.size() < 2) {
            throw new NotEnoughParticipantsException(String.format(
                    "In order to play you have to have 2 participants at least. Now we have %d only",
                    participants.size()));
        }
        int prize = randomGenerator.randomInRange(prizeRange.getMin(), prizeRange.getMax());
        int winnerNumber = randomGenerator.randomInRange(0, participants.size() - 1);
        Winner winner = new Winner(participants.get(winnerNumber), prize);
        winnerRepository.save(winner);
        participantRepository.deleteAll();
        return winner;
    }

    @Override
    public List<Winner> getWinners() {
        return winnerRepository.findAll();
    }
}
