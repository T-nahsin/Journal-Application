package com.tnahsin.journalApplication.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponse {

    public Current current;


    public class Current{


        public int temperature;

        @JsonProperty("weatherDescriptions")
        public List<String> weather_descriptions;

        public int feelslike;

    }
}
