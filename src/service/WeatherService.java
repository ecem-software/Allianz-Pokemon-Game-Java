package service;

import model.WeatherConditionEnum;

import java.util.Random;

public class WeatherService {

    public WeatherConditionEnum randomWeather() {
        int pickWeather = new Random().nextInt(WeatherConditionEnum.values().length);
        WeatherConditionEnum randomWeather = WeatherConditionEnum.values()[pickWeather];
        return randomWeather;
    }

}





