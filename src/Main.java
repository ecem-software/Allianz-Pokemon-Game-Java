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

        WeatherService weatherService = new WeatherService();

        Scanner scanner = new Scanner(System.in);
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


        Player player1 = loadService.preparePlayer(characterList);
        Player player2 = loadService.preparePlayer(characterList);


        Boolean isGameStart = true;

        //Game is starting
        while (isGameStart) {
            System.out.println("Please press 1 to start game and please press 2 to quit the gym");
            int gameStartChoice = scanner.nextInt();
            if (gameStartChoice == 1) {
                System.out.println("First battle is starting");
                gameService.battle(player1, player2);
                if (player1.isWinner() == true) {
                    gameService.payOff(player1, player2);
                } else if (player2.isWinner() == true) {
                    gameService.payOff(player2, player1);
                }
                System.out.println("Second battle is starting! Are you ready! ");
                gameService.battle(player1, player2);

            } else {
                System.out.println("Quiting....");
                break;
            }
        }


    }
}