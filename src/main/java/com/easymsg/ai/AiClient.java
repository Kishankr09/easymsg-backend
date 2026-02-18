package com.easymsg.ai;

public interface AiClient {
  record AiResult(String text) {}
  AiResult generate(String systemPrompt, String userPrompt);
}
