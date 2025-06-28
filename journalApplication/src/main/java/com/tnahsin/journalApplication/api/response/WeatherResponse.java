package com.tnahsin.journalApplication.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    public Current current;


    @Getter
    @Setter
    public class Current{

        public int temperature;

        @JsonProperty("weatherDescriptions")
        public List<String> weather_descriptions;

        public int feelslike;

    }
}
