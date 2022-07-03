package org.lab.lottery.model;

import org.junit.jupiter.api.Test;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.lab.lottery.model.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.*;

@DataJpaTest
public class WinnerRepositoryTest {
    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testFindAll() {
        //given
        testEntityManager.persistFlushFind(new Winner(Participant.builder().name("Olga").age(21).city("Moscow").build(), 100));
        testEntityManager.persistFlushFind(new Winner(Participant.builder().name("Maria").age(23).city("New York").build(), 1000));
        //when
        List<Winner> all = winnerRepository.findAll();
        //then
        then(all).isNotNull();
        then(all.size()).isEqualTo(2);
        then(all.stream().map(e->e.getPrize().doubleValue()).reduce(0., Double::sum)).isEqualTo(1100.0);
    }
}
