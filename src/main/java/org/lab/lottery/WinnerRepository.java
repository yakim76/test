package org.lab.lottery;

import org.lab.lottery.model.Winner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WinnerRepository extends CrudRepository<Winner, Long> {
    List<Winner> findAll();
}
