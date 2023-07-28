package service;

import model.*;
import model.Character;

import java.util.ArrayList;
import java.util.Scanner;

public class LoadService {
    public ArrayList<Character> loadCharacters(){
        SpecialPower strategy1 = new Strategy("Strategy", 4, 1);
        SpecialPower strategy2 = new Strategy("Strategy II", 3, 1);

        Character ash = new Ash("Ash", strategy1);
        Character brooke = new Brooke("Brooke", strategy2);

        ArrayList<Character> characterList = new ArrayList<>();
        characterList.add(ash);
        characterList.add(brooke);
        return characterList;
    }

    public ArrayList<Pokemon> loadPokemons(){
        SpecialPower electricty = new Electricty("Electricty", 3, 3);
        SpecialPower water = new Water("Water", 1, 3);
        SpecialPower fire = new Fire("Fire", 5, 3);
        SpecialPower earth = new Earth("Earth", 4, 3);

        Pokemon pokemon1 = new Pikachu("Pikachu", 100, 10, TypeEnum.ELECTRICY, electricty,WeatherConditionEnum.SUNNY);
        Pokemon pokemon2 = new Sqiurtle("Squirtle", 110, 8, TypeEnum.WATER, water,WeatherConditionEnum.RAINY);
        Pokemon pokemon3 = new Charmander("Charmender", 90, 12, TypeEnum.FIRE, fire,WeatherConditionEnum.CLOUDY);
        Pokemon pokemon4 = new Balbausar("Balbausar", 140, 7, TypeEnum.EARTH, earth,WeatherConditionEnum.SNOWY);

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        pokemonList.add(pokemon1);
        pokemonList.add(pokemon2);
        pokemonList.add(pokemon3);
        pokemonList.add(pokemon4);

        return pokemonList;
    }

    public Player preparePlayer(ArrayList<model.Character> characterList){
        Scanner scanner = new Scanner(System.in);
        PlayerService playerService = new PlayerService();
        System.out.println("Please enter your name!");
        String playerName=scanner.next();

        int index=1;
        for(Character character: characterList){
            System.out.println(index+"- " + character.getName());
            index++;
        }

        //Choose character of players
        System.out.println( playerName+ " Choose your character");
        int playerCharChoice=scanner.nextInt();
        while (playerCharChoice < 1 || playerCharChoice > characterList.size()) {
            System.out.println("Invalid choice! Please select a valid character (1 to " + characterList.size() + "):");
            playerCharChoice = scanner.nextInt();
        }
        //Create Character
        Player player = playerService.createPlayer(playerName, characterList.get(playerCharChoice-1));

        //Remove chosen character from the list.
        characterList.remove(playerCharChoice-1);


        //Choose Pokemon
        System.out.println("Please choose your pokemons");

        int index2=1;
        for(Pokemon poke: player.getCharacter().getPokemonList()){
            System.out.println(index2+"- " + poke);
            index2++;
        }
        int player1PokeChoice=scanner.nextInt();
        Pokemon playerPoke=player.getCharacter().getPokemonList().get(player1PokeChoice-1);

        player.setPokemon(playerPoke);

        return player;
    }

    /*WeatherService weatherService=new WeatherService();
    public void ChangeDamageAmountAccordingToWeather(Pokemon playerPoke){
        WeatherConditionEnum randomWeather = weatherService.randomWeather();

        if (randomWeather == WeatherConditionEnum.SUNNY && playerPoke.getName()=="Pikachu") {
            System.out.println("The day is Sunny.  Pikachu's electricty can be affected bad from SUNNY weather condition.");
            playerPoke.setDamage(playerPoke.getDamage()-2);

        } else if (randomWeather == WeatherConditionEnum.RAINY && playerPoke.getName()=="Squirtle") {
            System.out.println("The day is Rainy. Squirtle 's power can be affected bad from RAINY weather condition. ");
            playerPoke.setDamage(playerPoke.getDamage()-2);
        } else if (randomWeather == WeatherConditionEnum.CLOUDY && playerPoke.getName()=="Charmender") {
            System.out.println("The day is Cloudy.  Charmender 's power can be affected bad from CLOUDY weather condition.");
            playerPoke.setDamage(playerPoke.getDamage()-2);
        } else if (randomWeather == WeatherConditionEnum.SNOWY && playerPoke.getName()=="Balbausar") {
            System.out.println("The day is Snowy. Balbausar's power can be affected bad from SNOWY weather condition.");
            playerPoke.setDamage(playerPoke.getDamage()-2);

        }
    }*/


}
