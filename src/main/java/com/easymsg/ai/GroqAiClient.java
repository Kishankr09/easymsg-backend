package com.easymsg.ai;

import com.easymsg.config.AiProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public class GroqAiClient implements AiClient {

  private final WebClient webClient;
  private final AiProperties props;

  public GroqAiClient(WebClient aiWebClient, AiProperties props) {
    this.webClient = aiWebClient;
    this.props = props;
  }

  @Override
  public AiResult generate(String systemPrompt, String userPrompt) {
    if (props.getApiKey() == null || props.getApiKey().isBlank()) {
      throw new IllegalArgumentException("Missing EASYMSG_AI_KEY. Please set environment variable EASYMSG_AI_KEY.");
    }

    Map<String, Object> body = Map.of(
        "model", props.getModel(),
        "messages", List.of(
            Map.of("role", "system", "content", systemPrompt),
            Map.of("role", "user", "content", userPrompt)
        ),
        "temperature", props.getTemperature(),
        "max_tokens", props.getMaxTokens()
    );

    Map<?, ?> resp = webClient.post()
        .uri(props.getBaseUrl() + "/chat/completions")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + props.getApiKey())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .bodyToMono(Map.class)
        .block();

    if (resp == null) throw new RuntimeException("Empty response from Groq");

    Object choicesObj = resp.get("choices");
    if (!(choicesObj instanceof List<?> choices) || choices.isEmpty()) {
      throw new RuntimeException("Groq response missing choices");
    }

    Object first = choices.get(0);
    if (!(first instanceof Map<?, ?> firstMap)) {
      throw new RuntimeException("Groq response invalid choices item");
    }

    Object messageObj = firstMap.get("message");
    if (!(messageObj instanceof Map<?, ?> msgMap)) {
      throw new RuntimeException("Groq response missing message");
    }

    Object contentObj = msgMap.get("content");
    String content = contentObj == null ? "" : contentObj.toString().trim();

    if (content.isBlank()) throw new RuntimeException("Groq returned empty content");

    return new AiResult(content);
  }
}
