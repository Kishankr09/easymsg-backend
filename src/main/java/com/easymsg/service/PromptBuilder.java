package com.easymsg.service;

import com.easymsg.dto.GenerateMessageRequest;

public class PromptBuilder {

  public static String buildSystemPrompt() {
    return """
      You are EasyMsg, a helpful assistant that rewrites user messages.
      Rules:
      - Keep meaning same, make it clear, well-written, and ready to send.
      - Output ONLY the final message. No explanation, no quotes.
      - Avoid offensive or inappropriate content.
      """;
  }

  public static String buildUserPrompt(GenerateMessageRequest r) {
    String langRule = switch (r.getLanguage()) {
      case English -> "Write the final message strictly in English.";
      case Hinglish -> "Write the final message strictly in Hinglish (Hindi words in Roman script mixed with English naturally).";
      case Hindi -> "Write the final message strictly in Hindi (Devanagari script).";
    };

    String toneRule = switch (r.getTone()) {
      case Professional -> "Tone: professional, respectful, concise.";
      case Polite -> "Tone: polite, warm, respectful.";
      case Friendly -> "Tone: friendly, casual (but respectful).";
    };

    String context = (r.getContext() == null || r.getContext().isBlank())
        ? ""
        : ("\nExtra context: " + r.getContext().trim());

    return """
      Rewrite/refine this message for sending:
      ---
      %s
      ---
      Category: %s
      %s
      %s
      %s
      """.formatted(
        r.getMessage().trim(),
        r.getCategory(),
        toneRule,
        langRule,
        context
    );
  }
}
