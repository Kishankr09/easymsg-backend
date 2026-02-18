package com.easymsg.service;

import com.easymsg.ai.AiClient;
import com.easymsg.config.AiProperties;
import com.easymsg.dto.GenerateMessageRequest;
import com.easymsg.dto.GenerateMessageResponse;
import org.springframework.stereotype.Service;

@Service
public class MessageGeneratorService {

  private final AiClient aiClient;
  private final AiProperties props;

  public MessageGeneratorService(AiClient aiClient, AiProperties props) {
    this.aiClient = aiClient;
    this.props = props;
  }

  public GenerateMessageResponse generate(GenerateMessageRequest request) {
    String system = PromptBuilder.buildSystemPrompt();
    String user = PromptBuilder.buildUserPrompt(request);

    AiClient.AiResult result = aiClient.generate(system, user);

    return new GenerateMessageResponse(
        result.text(),
        request.getCategory(),
        request.getTone(),
        request.getLanguage(),
        props.getProvider(),
        props.getModel()
    );
  }
}
