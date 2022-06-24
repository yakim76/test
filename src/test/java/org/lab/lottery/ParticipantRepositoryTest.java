package org.lab.lottery;

import org.junit.jupiter.api.Test;
import org.lab.lottery.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class ParticipantRepositoryTest {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void testGetParticipantByName() {
        //given
        Participant participant = testEntityManager.persistFlushFind(new Participant(null, "Olga", 21, "Moscow"));
        //when
        participantRepository.getParticipantByName("Olga");
        //then
        then(participant.getId()).isNotNull();
        then(participant.getName()).isEqualTo("Olga");
    }

    @Test
    void testCount(){
        //given
        testEntityManager.persistFlushFind(new Participant(null, "Olga", 21, "Moscow"));
        testEntityManager.persistFlushFind(new Participant(null, "Maria", 23, "Sochi"));
        //when
        Long count = participantRepository.count();
        //then
        then(count).isNotNull();
        then(count).isEqualTo(2L);
    }

    @Test
    void testGetAll(){
        //given
        testEntityManager.persistFlushFind(new Participant(null, "Olga", 21, "Moscow"));
        testEntityManager.persistFlushFind(new Participant(null, "Maria", 23, "Sochi"));
        //when
        List<Participant> all = participantRepository.findAll();
        //then
        then(all).isNotNull();
        then(all.size()).isEqualTo(2);
    }
}
