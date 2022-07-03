package org.lab.lottery.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.lab.lottery.blogic.NotEnoughParticipantsException;
import org.lab.lottery.model.Participant;
import org.lab.lottery.model.Winner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@Api(tags = "lottery-service")
public interface LotteryAPI {

  @ApiOperation(value = "List of participants", nickname = "participant", response = Participant.class, responseContainer = "List")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "An array of participants", response = Participant.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request due to invalid request. Please, make sure that request is correct")
  })
  @GetMapping(value = "/lottery/participant", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Participant> getParticipants();

  @ApiOperation(value = "Adds participant", nickname = "participant", response = Participant.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Created participant", response = Participant.class),
      @ApiResponse(code = 400, message = "Bad request due to invalid request. Please, make sure that request is correct")
  })
  @PostMapping(value = "/lottery/participant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Participant getParticipants(Participant participant);

  @ApiOperation(value = "Play lottery", nickname = "play-lottery", response = Winner.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "chosen winner", response = Winner.class),
      @ApiResponse(code = 417, message = "If there are no enough participants")
  })
  @GetMapping(value = "/lottery/start", produces = MediaType.APPLICATION_JSON_VALUE)
  Winner play();

  @ApiOperation(value = "List of winners", nickname = "winner", response = Winner.class, responseContainer = "List")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "An array of winners", response = Winner.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Bad request due to invalid request. Please, make sure that request is correct")
  })
  @GetMapping(value = "/lottery/winners", produces = MediaType.APPLICATION_JSON_VALUE)
  List<Winner> winners();

  @ExceptionHandler(NotEnoughParticipantsException.class)
  @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
  ResponseEntity<String> handleNoSuchElementFoundException(
      NotEnoughParticipantsException exception
  );
}
