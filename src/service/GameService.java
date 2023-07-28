package service;
import model.Player;
import model.Pokemon;
import model.WeatherConditionEnum;
import java.util.Random;
import java.util.Scanner;

public class GameService {
    WeatherService weatherService = new WeatherService();
    Random rand = new Random();

    // Battle setup method
    public void battle(Player player1, Player player2) {
        int playerTurn = rand.nextInt(1, 3);
        Player attacker;
        Player defender;
        WeatherConditionEnum weatherCondition;


        Player[] playerlist = {player1, player2};

        while (player1.getPokemon().getHealth() > 0 && player2.getPokemon().getHealth() > 0) {
            attacker = playerlist[playerTurn % 2];
            defender = playerlist[(playerTurn + 1) % 2];
            playerTurn += 1;
            weatherCondition = weatherService.randomWeather();

            System.out.println("Attacker: " + attacker.getName() + ", Pokemon: " + attacker.getPokemon().getName() + ", Health: " + attacker.getPokemon().getHealth());
            System.out.println("Defender: " + defender.getName() + ", Pokemon: " + defender.getPokemon().getName() + ", Health: " + defender.getPokemon().getHealth());
            System.out.println("Current weather condition is " + weatherCondition.name());
            System.out.println("Attacker choose attack type:\n1- Normal attack?\n2- PokeSpecial attack?\n3- CharSpecial attack?\n4- Both PokeSpecial and CharSpecial attack?");
            Scanner scanner = new Scanner(System.in);
            int attackChoice = scanner.nextInt();
            switch (attackChoice) {
                case 1:
                    attack(attacker, defender, false, false, weatherCondition);
                    break;
                case 2:
                    attack(attacker, defender, true, false, weatherCondition);
                    break;
                case 3:
                    attack(attacker, defender, false, true, weatherCondition);
                    break;
                case 4:
                    attack(attacker, defender, true, true, weatherCondition);
                    break;

                default:
                    System.out.println("Please enter valid attack number");

            }

            if (!healthCheck(defender)) {
                attacker.setWinner(true);
            }
        }
    }

    public void attack(Player attacker, Player defender, boolean isPokeSpecialAttack, boolean isCharSpecialAttack, WeatherConditionEnum weatherCondition) {
        Pokemon attackingPokemon = attacker.getPokemon();
        Pokemon defendingPokemon = defender.getPokemon();

        boolean specialAttack = false;
        if (isPokeSpecialAttack && isCharSpecialAttack) {
            specialAttack = attackingPokemon.getSpecialPower().getRemainingRights() > 0
                    && attacker.getCharacter().getSpecialPower().getRemainingRights() > 0;
        } else if (isPokeSpecialAttack) {
            specialAttack = attackingPokemon.getSpecialPower().getRemainingRights() > 0;
        } else if (isCharSpecialAttack) {
            specialAttack = attacker.getCharacter().getSpecialPower().getRemainingRights() > 0;
        }

        int charRemainingRights = attacker.getCharacter().getSpecialPower().getRemainingRights();
        double weaknessRatio = 0.6;
        int oldDamage = attackingPokemon.getDamage();
        if (weatherCondition == attackingPokemon.getWeatherWeakness()) {
            attackingPokemon.setDamage((int) (attackingPokemon.getDamage() * weaknessRatio));
            System.out.println(attackingPokemon.getName() + "'s base damage decrease from " + oldDamage + " to " + attackingPokemon.getDamage());
        }


        int damage = 0;
        if (specialAttack) {
            if (isPokeSpecialAttack && isCharSpecialAttack) {
                damage += attackingPokemon.specialAttack();
                damage += attacker.getCharacter().getSpecialPower().getExtraDamage();
                attacker.getCharacter().getSpecialPower().setRemainingRights(charRemainingRights - 1);
            } else if (isPokeSpecialAttack) {
                damage += attackingPokemon.specialAttack();
            } else {
                damage += attackingPokemon.getDamage();
                damage += attacker.getCharacter().getSpecialPower().getExtraDamage();
                attacker.getCharacter().getSpecialPower().setRemainingRights(charRemainingRights - 1);
            }
        } else {
            if (isPokeSpecialAttack || isCharSpecialAttack) {
                damage = 0;
            } else {
                damage += attackingPokemon.getDamage();
            }
        }
        defendingPokemon.setHealth(defendingPokemon.getHealth() - damage);
        attackingPokemon.setDamage(oldDamage);
    }

    public boolean healthCheck(Player player) {
        if (player.getPokemon().getHealth() > 0) {
            System.out.println("Game continues");
            return true;

        } else {
            System.out.println(player.getName() + " lost");
            return false;
        }
    }

    // Game pokemon transfer method
    public void payOff(Player winner, Player loser) {
        loser.getPokemon().setHealth(loser.getPokemon().getDefaultHealth());
        winner.setPokemon(loser.getPokemon());
        winner.getCharacter().getPokemonList().add(winner.getPokemon());


        loser.getCharacter().getPokemonList().remove(loser.getPokemon());

        Pokemon lowestDamagePokemon = loser.getCharacter().getPokemonList().get(0);
        for(Pokemon poke : loser.getCharacter().getPokemonList()){
            if(poke.getDamage()<lowestDamagePokemon.getDamage() ){
                lowestDamagePokemon = poke;
            }
        }
        loser.setPokemon(lowestDamagePokemon);

    }
}
