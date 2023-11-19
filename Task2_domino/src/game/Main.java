package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Domino> playedTiles;
    private static Domino startingTile;

    public static void main(String[] args){
        playedTiles = new ArrayList<>();
        startingTile = null;
        Player player = new Player();
        Bot bot = new Bot();

        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayGameState();

            player.makePlayerMove(playedTiles, scanner);
            if (player.getPlayerTiles().isEmpty()) {
                System.out.println("Поздравляем! Вы выиграли!");
                break;
            }
            bot.makeBotMove(playedTiles);
            if (bot.getBotTiles().isEmpty()) {
                System.out.println("Бот выиграл. Попробуйте еще раз!");
                break;
            }
        }
    }
    private static void initializeGame() {
        List<Domino> tiles = createAndShuffleTiles();
        startingTile = tiles.remove(0);
        playedTiles.add(startingTile);
    }


    private static void displayGameState() {
        System.out.println("Сыгранные кости: " + playedTiles);
    }
    public static List<Domino> createAndShuffleTiles() {
        List<Domino> tiles = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                tiles.add(new Domino(i, j));
            }
        }
        Collections.shuffle(tiles);
        return tiles;
    }
}
