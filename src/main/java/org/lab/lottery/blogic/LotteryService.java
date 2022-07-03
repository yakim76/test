package org.lab.lottery.blogic;

import java.util.List;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;

public interface LotteryService {

  Participant registerParticipant(Participant participant);

  List<Participant> getParticipants();

  Winner play() throws NotEnoughParticipantsException;

  List<Winner> getWinners();
}
