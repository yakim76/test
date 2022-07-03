package org.lab.lottery.model;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {

  void getParticipantByName(String olga);

  @NotNull
  List<Participant> findAll();
}
