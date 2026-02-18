package com.easymsg.dto;

import com.easymsg.domain.Category;
import com.easymsg.domain.OutputLanguage;
import com.easymsg.domain.Tone;

public class GenerateMessageResponse {
  private String refinedMessage;
  private Category category;
  private Tone tone;
  private OutputLanguage language;
  private String provider;
  private String model;

  public GenerateMessageResponse() {}

  public GenerateMessageResponse(String refinedMessage, Category category, Tone tone,
                                 OutputLanguage language, String provider, String model) {
    this.refinedMessage = refinedMessage;
    this.category = category;
    this.tone = tone;
    this.language = language;
    this.provider = provider;
    this.model = model;
  }

  public String getRefinedMessage() { return refinedMessage; }
  public void setRefinedMessage(String refinedMessage) { this.refinedMessage = refinedMessage; }

  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }

  public Tone getTone() { return tone; }
  public void setTone(Tone tone) { this.tone = tone; }

  public OutputLanguage getLanguage() { return language; }
  public void setLanguage(OutputLanguage language) { this.language = language; }

  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }

  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }
}
