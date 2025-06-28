package com.tnahsin.journalApplication.service;


import com.tnahsin.journalApplication.api.response.WeatherResponse;
import com.tnahsin.journalApplication.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppCache appcache;

    public WeatherResponse getWeather(String city) {

        String finalApi = appcache.APP_CACHE.get("weather_api").replace("<city>",city).replace("<api_key>",apiKey);
        ResponseEntity<WeatherResponse>response = restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
        return response.getBody();

    }
}
