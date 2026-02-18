package com.easymsg.api;

import com.easymsg.dto.GenerateMessageRequest;
import com.easymsg.dto.GenerateMessageResponse;
import com.easymsg.service.MessageGeneratorService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

  private final MessageGeneratorService service;

  public MessageController(MessageGeneratorService service) {
    this.service = service;
  }

  @PostMapping(value = "/generate",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public GenerateMessageResponse generate(@Valid @RequestBody GenerateMessageRequest request) {
    return service.generate(request);
  }

  @GetMapping("/health")
  public String health() {
    return "EasyMsg API is up";
  }
}
