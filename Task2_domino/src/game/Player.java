package game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static game.Domino.createAndShuffleTiles;

public class Player {
    private List<Domino> playerTiles;

    public Player() {
        playerTiles = new ArrayList<>();
        dealTiles(createAndShuffleTiles(), playerTiles);
    }

    public List<Domino> getPlayerTiles() {
        return playerTiles;
    }

    public void makePlayerMove(List<Domino> playedTiles, JTextField inputField) {
        System.out.println("Ваши кости: " + playerTiles);

        int index;
        try {
            index = Integer.parseInt(inputField.getText()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, введите корректное число.");
            return;
        }

        if (index >= 0 && index < playerTiles.size()) {
            Domino selectedTile = playerTiles.remove(index);

            if (isMoveValid(selectedTile, playedTiles)) {
                playedTiles.add(selectedTile);
                System.out.println("Вы сыграли кость: " + selectedTile);
            } else {
                System.out.println("Выбранная кость не подходит. Выберите другую.");
                playerTiles.add(selectedTile);
            }
        } else {
            System.out.println("Пожалуйста, выберите корректный номер кости.");
        }
    }

    private void dealTiles(List<Domino> tiles, List<Domino> playerTiles) {
        for (int i = 0; i < 7; i++) {
            playerTiles.add(tiles.remove(0));
        }
    }
    private boolean isMoveValid(Domino tile, List<Domino> playedTiles) {

        if (playedTiles.isEmpty()) {
            return true;
        }

        Domino firstTile = playedTiles.get(0);
        Domino lastTile = playedTiles.get(playedTiles.size() - 1);

        return tile.getLeft() == firstTile.getLeft() || tile.getLeft() == lastTile.getRight() ||
                tile.getRight() == firstTile.getLeft() || tile.getRight() == lastTile.getRight();
    }
}