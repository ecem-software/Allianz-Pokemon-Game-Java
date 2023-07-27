import model.*;
import model.Character;
import service.GameService;
import service.LoadService;
import service.PlayerService;
import service.WeatherService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        WeatherService weatherService=new WeatherService();

        Scanner scanner=new Scanner(System.in);
        LoadService loadService = new LoadService();
        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();

        // Load characters
        ArrayList<model.Character> characterList = loadService.loadCharacters();
        System.out.println("------Characters------");
        for (Character character : characterList) {
            System.out.println(character.toString());
        }

        // Load Pokemon
        ArrayList<Pokemon> pokemonList = loadService.loadPokemons();
        System.out.println("\n------Pokemon--------");
        for (Pokemon pokemon : pokemonList) {
            System.out.println(pokemon.toString());
        }

        //Add Pokémon to Characters
        characterList.get(0).getPokemonList().addAll(pokemonList);
        characterList.get(1).getPokemonList().addAll(pokemonList);

       // characterList.get(0).getPokemonList().add(pokemonList.get(0));
        //characterList.get(1).getPokemonList().add(pokemonList.get(1));
        /* Player player1 = playerService.createPlayer("Tuğçe", characterList.get(0));
        Player player2 = playerService.createPlayer("Hasan", characterList.get(1));

        gameService.attack(player1, player2, true, true);

        gameService.healthCheck(player2);*/

//        //Take name of players
//        System.out.println("First player: Please enter your name!");
//        String player1Name=scanner.next();
//        System.out.println("Second player: Please enter your name!");
//        String player2Name=scanner.next();
//
//        int index=1;
//        for(Character character: characterList){
//            System.out.println(index+"- " + character.getName());
//            index++;
//        }
//
//        //Choose character of players
//        System.out.println( player1Name+ " Choose your character");
//        int player1CharChoice=scanner.nextInt();
//        System.out.println(player2Name + " Choose your character");
//        int player2CharChoice=scanner.nextInt();
//
//
//        Player player1 = playerService.createPlayer(player1Name, characterList.get(player1CharChoice-1));
//        Player player2 = playerService.createPlayer(player2Name, characterList.get(player2CharChoice-1));
//
//        System.out.println("Please choose your pokemons");
//
//
//        int index2=1;
//        for(Pokemon poke: player1.getCharacter().getPokemonList()){
//            System.out.println(index2+"- " + player1.getCharacter().getPokemonList().get(index2-1));
//            index2++;
//        }
//
//        int player1PokeChoice=scanner.nextInt();
//        Pokemon player1Poke=player1.getCharacter().getPokemonList().get(player1PokeChoice-1);


        Player player1=loadService.preparePlayer(characterList);
        Player player2=loadService.preparePlayer(characterList);


        System.out.println("Please press 1 to start game and please press 2 to quit the gym");
        int gameStartChoice=scanner.nextInt();
        Boolean isGameStart=true;
        //Game is starting
        while (isGameStart){
            if(gameStartChoice==1){
                //initial weather setted.

                WeatherConditionEnum currentWeather = weatherService.randomWeather();
                System.out.println("Now the weather is " + currentWeather);
                loadService. ChangeDamageAmountAccordingToWeather(player1, currentWeather);
                loadService. ChangeDamageAmountAccordingToWeather(player2, currentWeather);

                gameService.battle(player1,player2);

            }

            else{
                System.out.println("Quiting....");
                break;
            }
        }
        WeatherService weatherService=new WeatherService();
        System.out.println(weatherService);



    }
}