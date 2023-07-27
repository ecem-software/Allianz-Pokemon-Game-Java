package service;

import model.Character;
import model.Player;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameService {
Random rand= new Random();
    public void battle(Player player1, Player player2) {
        int playerTurn = rand.nextInt(1, 3);
        Player attacker;
        Player defender;


        Player [] playerlist={player1,player2};

        while(player1.getPokemon().getHealth() != 0 || player2.getPokemon().getHealth() != 0){
            attacker=playerlist[playerTurn%2];
            defender =playerlist[(playerTurn+1)%2];
            playerTurn+=1;

            System.out.println("Attacker :" +attacker);
            System.out.println("Defender: "+defender);

            System.out.println("Attacker choose attack type:\n1- Normal attack?\n2- PokeSpecial attack?\n3- CharSpecial attack?\n4- Both PokeSpecial and CharSpecial attack?");
            Scanner scanner=new Scanner(System.in);
            int attackChoice=scanner.nextInt();
            switch (attackChoice){
                case 1:
                    attack(attacker,defender,false,false);
                    break;
                case 2:
                    attack(attacker, defender,true,false);
                    break;
                case 3:
                    attack(attacker,defender,false,true);
                    break;
                case 4:
                    attack(attacker,defender,true,true);
                    break;

                default:
                    System.out.println("Please enter valid attack number");

            }
            healthCheck(defender);

            if(!healthCheck(defender)){
                attacker.setWinner(true);
            }


           /* if(attackChoice==1){
                attack(attacker,defender,false,false);
                healthCheck(defender);
            } else if (attackChoice==2) {
                attack(attacker, defender,true,true);
            }
            else{
                System.out.println("Please enter correct attack type!");
            }*/

        }
    }
    public void attack(Player attacker, Player defender, boolean isPokeSpecialAttack, boolean isCharSpecialAttack) {
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
    }

    public boolean healthCheck(Player player){
        if(player.getPokemon().getHealth() > 0){
            System.out.println(player.toString());
            System.out.println("Game continues");
            return true;

        } else {
            System.out.println(player.toString());
            System.out.println(player.getName() + "lost");
            return false;
        }
    }
}
