package org.lab.lottery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String city;
    private Integer prize;

    public Winner(Participant participant, Integer prize) {
        name = participant.getName();
        age = participant.getAge();
        city = participant.getCity();
        this.prize = prize;
    }
}
