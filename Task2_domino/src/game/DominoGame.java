package game;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static game.Domino.createAndShuffleTiles;

public class DominoGame extends JFrame {
    private static List<Domino> playedTiles;
    private static Domino startingTile;
    private Player player;
    private Bot bot;
    private JTextField inputField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DominoGame gui = new DominoGame();
            gui.init();
            gui.setVisible(true);
        });
    }

    public void init() {
        playedTiles = new ArrayList<>();
        startingTile = null;
        player = new Player();
        bot = new Bot();

        initializeGame();

        setTitle("Domino Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel playedTilesPanel = new JPanel();
        playedTilesPanel.setLayout(new BoxLayout(playedTilesPanel, BoxLayout.Y_AXIS));
        addDominoField(playedTilesPanel, playedTiles, "Сыгранные кости:");
        mainPanel.add(playedTilesPanel, BorderLayout.CENTER);

        JPanel playerTilesPanel = new JPanel();
        playerTilesPanel.setLayout(new BoxLayout(playerTilesPanel, BoxLayout.X_AXIS));
        addDominoField(playerTilesPanel, player.getPlayerTiles(), "Ваши кости:");
        mainPanel.add(playerTilesPanel, BorderLayout.EAST);

        JPanel botTilesPanel = new JPanel();
        botTilesPanel.setLayout(new BoxLayout(botTilesPanel, BoxLayout.X_AXIS));
        addDominoField(botTilesPanel, bot.getBotTiles(), "Кости бота:");
        mainPanel.add(botTilesPanel, BorderLayout.WEST);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Выберите номер кости:");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        inputPanel.add(label);
        inputField = new JTextField(4);
        inputPanel.add(inputField);
        JButton playButton = new JButton("Сделать ход");
        playButton.setFont(new Font("Arial", Font.PLAIN, 20));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlayerMove();
            }
        });
        inputPanel.add(playButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addDominoField(JPanel panel, List<Domino> tiles, String label) {
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.PLAIN, 36));
        panel.add(labelComponent);

        for (Domino tile : tiles) {
            JLabel tileLabel = new JLabel(tile.toString());
            tileLabel.setFont(new Font("Arial", Font.PLAIN, 36));
            panel.add(tileLabel);
        }
    }

    private void initializeGame() {
        List<Domino> tiles = createAndShuffleTiles();
        startingTile = tiles.remove(0);
        playedTiles.add(startingTile);
    }

    private void handlePlayerMove() {
        try {
            int index = Integer.parseInt(inputField.getText()) - 1;
            player.makePlayerMove(playedTiles, inputField);

            if (player.getPlayerTiles().isEmpty()) {
                showAlert("Поздравляем! Вы выиграли!");
                dispose();
            } else {
                bot.makeBotMove(playedTiles);
                if (bot.getBotTiles().isEmpty()) {
                    showAlert("Бот выиграл. Попробуйте еще раз!");
                    dispose();
                }
                displayGameState();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Пожалуйста, введите корректное число.");
        }
    }

    private void displayGameState() {
        getContentPane().removeAll();

        JPanel playedTilesPanel = new JPanel();
        playedTilesPanel.setLayout(new BoxLayout(playedTilesPanel, BoxLayout.Y_AXIS));
        addDominoField(playedTilesPanel, playedTiles, "Сыгранные кости:");
        getContentPane().add(playedTilesPanel, BorderLayout.CENTER);

        JPanel playerTilesPanel = new JPanel();
        playerTilesPanel.setLayout(new BoxLayout(playerTilesPanel, BoxLayout.X_AXIS));
        addDominoField(playerTilesPanel, player.getPlayerTiles(), "Ваши кости:");
        getContentPane().add(playerTilesPanel, BorderLayout.EAST);

        JPanel botTilesPanel = new JPanel();
        botTilesPanel.setLayout(new BoxLayout(botTilesPanel, BoxLayout.X_AXIS));
        addDominoField(botTilesPanel, bot.getBotTiles(), "Кости бота:");
        getContentPane().add(botTilesPanel, BorderLayout.WEST);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Выберите номер кости:");
        inputPanel.add(label);
        inputField = new JTextField(2);
        inputPanel.add(inputField);
        JButton playButton = new JButton("Сделать ход");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlayerMove();
            }
        });
        inputPanel.add(playButton);

        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message, "Результат игры", JOptionPane.INFORMATION_MESSAGE);
    }
}