package com.easymsg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "easymsg.ai")
public class AiProperties {

  private String provider = "groq";
  private String baseUrl = "https://api.groq.com/openai/v1";
  private String model = "llama-3.1-8b-instant";
  private String apiKey;

  private double temperature = 0.4;
  private int maxTokens = 220;

  public String getProvider() { return provider; }
  public void setProvider(String provider) { this.provider = provider; }

  public String getBaseUrl() { return baseUrl; }
  public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }

  public String getApiKey() { return apiKey; }
  public void setApiKey(String apiKey) { this.apiKey = apiKey; }

  public double getTemperature() { return temperature; }
  public void setTemperature(double temperature) { this.temperature = temperature; }

  public int getMaxTokens() { return maxTokens; }
  public void setMaxTokens(int maxTokens) { this.maxTokens = maxTokens; }
}
