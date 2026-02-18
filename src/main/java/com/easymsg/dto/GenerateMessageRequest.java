package com.easymsg.dto;

import com.easymsg.domain.Category;
import com.easymsg.domain.OutputLanguage;
import com.easymsg.domain.Tone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GenerateMessageRequest {

  @NotBlank(message = "message is required")
  private String message;

  @NotNull(message = "category is required")
  private Category category;

  @NotNull(message = "tone is required")
  private Tone tone;

  @NotNull(message = "language is required")
  private OutputLanguage language;

  private String context;

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }

  public Tone getTone() { return tone; }
  public void setTone(Tone tone) { this.tone = tone; }

  public OutputLanguage getLanguage() { return language; }
  public void setLanguage(OutputLanguage language) { this.language = language; }

  public String getContext() { return context; }
  public void setContext(String context) { this.context = context; }
}
