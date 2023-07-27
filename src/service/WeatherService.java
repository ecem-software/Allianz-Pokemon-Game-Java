package service;

import model.WeatherConditionEnum;

import java.util.Random;

public class WeatherService {

    PokemonService pokemonService = new PokemonService();

    public WeatherConditionEnum randomWeather() {
        int pickWeather = new Random().nextInt(WeatherConditionEnum.values().length);
        WeatherConditionEnum randomWeather = WeatherConditionEnum.values()[pickWeather];
        return randomWeather;
    }
//    public void createWeather(){
//        WeatherConditionEnum randomWeather = randomWeather();
//
//            if (randomWeather == WeatherConditionEnum.SUNNY) {
//                System.out.println("The day is Sunny., Pikachu's electricty can be affected bad from that weather condition.");
//
//            } else if (randomWeather == WeatherConditionEnum.RAINY) {
//                System.out.println("The day is Rainy.");
//            } else if (randomWeather == WeatherConditionEnum.CLOUDY) {
//                System.out.println("The day is Cloudy.");
//            } else if (randomWeather == WeatherConditionEnum.SNOWY) {
//                System.out.println("The day is Snowy.");
//            }
//    }


}





