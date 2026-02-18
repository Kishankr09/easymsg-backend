package com.easymsg.config;

import com.easymsg.ai.AiClient;
import com.easymsg.ai.GroqAiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient aiWebClient() {
    return WebClient.builder().build();
  }

  @Bean
  public AiClient aiClient(AiProperties props, WebClient aiWebClient) {
    String p = props.getProvider() == null ? "" : props.getProvider().toLowerCase();
    if (!"groq".equals(p)) {
      throw new IllegalArgumentException("Only provider=groq supported in this version. Found: " + props.getProvider());
    }
    return new GroqAiClient(aiWebClient, props);
  }
}
