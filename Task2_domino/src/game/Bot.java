package game;

import java.util.ArrayList;
import java.util.List;

import static game.Main.createAndShuffleTiles;

public class Bot {
    private List<Domino> botTiles;

    public Bot() {
        botTiles = new ArrayList<>();
        dealTiles(createAndShuffleTiles(), botTiles);
    }

    public List<Domino> getBotTiles() {
        return botTiles;
    }

    public void makeBotMove(List<Domino> playedTiles) {
        Domino botMove = findValidMove(playedTiles);

        if (botMove != null) {
            botTiles.remove(botMove);
        } else {
            botMove = botTiles.remove(0);
        }

        playedTiles.add(botMove);
        System.out.println("Бот сыграл кость: " + botMove);
    }

    private Domino findValidMove(List<Domino> playedTiles) {
        for (Domino tile : botTiles) {
            if (isMoveValid(tile, playedTiles)) {
                return tile;
            }
        }
        return null;
    }

    private void dealTiles(List<Domino> tiles, List<Domino> botTiles) {
        for (int i = 0; i < 7; i++) {
            botTiles.add(tiles.remove(0));
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