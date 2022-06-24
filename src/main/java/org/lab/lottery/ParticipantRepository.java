package org.lab.lottery;

import org.lab.lottery.model.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
    void getParticipantByName(String olga);

    List<Participant> findAll();
}
