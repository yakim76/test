package org.lab.lottery;


import org.junit.jupiter.api.Test;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class LotteryServiceTest {
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    @Test
    void testAddParticipant() {
        //given
        Participant olga = Participant.builder().name("Olga").age(21).city("Moscow").build();
        Participant maria = Participant.builder().name("Maria").age(23).city("Mushosransk").build();
        //when
        lotteryService.registerParticipant(olga);
        lotteryService.registerParticipant(maria);
        //then
        List<Participant> all = participantRepository.findAll();
        then(all).isNotNull();
        then(all.size()).isEqualTo(2);
    }

    @Test
    void testGetAll() {
        //given
        participantRepository.save(Participant.builder().name("Olga").age(21).city("Moscow").build());
        participantRepository.save(Participant.builder().name("Maria").age(23).city("Mushosransk").build());
        //when
        List<Participant> all = lotteryService.findAll();
        //then
        then(all).isNotNull();
        then(all.size()).isEqualTo(2);
    }

    @Test
    void testPlayFailIfLeeThen2Participant() {
        lotteryService.registerParticipant(Participant.builder().name("Olga").age(21).city("Moscow").build());
        assertThatExceptionOfType(NotEnoughParticipantsException.class).isThrownBy(
                () -> {
                    Winner winner = lotteryService.play();
                }
        );
    }

    @Test
    void testPlay() throws NotEnoughParticipantsException {
        //given
        Participant olga = Participant.builder().name("Olga").age(21).city("Moscow").build();
        Participant maria = Participant.builder().name("Maria").age(23).city("Mushosransk").build();
        //when
        lotteryService.registerParticipant(olga);
        lotteryService.registerParticipant(maria);
        Winner winner = lotteryService.play();
        //then
        then(winner).isNotNull();
        then(participantRepository.findAll().isEmpty()).isTrue();
    }

    @Test
    void testListWinners(){
        //given
        Participant olga = Participant.builder().name("Olga").age(21).city("Moscow").build();
        Participant maria = Participant.builder().name("Maria").age(23).city("Mushosransk").build();
        lotteryService.registerParticipant(olga);
        lotteryService.registerParticipant(maria);
        //when
        lotteryService.play();
        List<Winner> winners = lotteryService.getWinners();
        //then
        then(winners).isNotNull();
        then(winners.size()).isEqualTo(1);
    }
}
