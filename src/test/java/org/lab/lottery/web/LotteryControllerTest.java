package org.lab.lottery.web;

import org.junit.jupiter.api.Test;
import org.lab.lottery.LotteryService;
import org.lab.lottery.NotEnoughParticipantsException;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class LotteryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LotteryService lotteryService;

    @Test
    void getParticipants() throws Exception {
        //given
        given(lotteryService.getParticipants()).willReturn(List.of(
                Participant.builder().id(1L).name("Olga").age(23).build()
        ));
        //when
        //then
        mockMvc.perform(get("/lottery/participant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['id']").value(1L))
                .andExpect(jsonPath("$[0]['name']").value("Olga"))
                .andExpect(jsonPath("$[0]['age']").value(23));
    }

    @Test
    void testGetWinners() throws Exception {
        given(lotteryService.getWinners()).willReturn(List.of(new Winner(Participant.builder().id(1L).name("Olga").age(23).build(), 100)));
        mockMvc.perform(get("/lottery/winners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['name']").value("Olga"))
                .andExpect(jsonPath("$[0]['age']").value(23))
                .andExpect(jsonPath("$[0]['prize']").value(100));
    }

    @Test
    void testIfFailedNotEnoughParticipants() throws Exception {
        //given
        given(lotteryService.play()).willThrow(NotEnoughParticipantsException.class);
        //when
        mockMvc.perform(get("/lottery/start"))
                .andExpect(status().isExpectationFailed());
    }

}