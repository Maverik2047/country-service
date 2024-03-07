package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Country(@JsonProperty("country_name")
                      String countryName,
                      @JsonProperty("country_code")
                      String countryCode
) {
}