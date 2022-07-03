package org.lab.lottery.model;

import org.jetbrains.annotations.NotNull;
import org.lab.lottery.model.Winner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WinnerRepository extends CrudRepository<Winner, Long> {
    @NotNull
    List<Winner> findAll();
}
