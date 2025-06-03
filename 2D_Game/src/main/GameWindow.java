package main;

import javax.swing.JFrame;

public class GameWindow{
    private JFrame window;

    public GameWindow(GamePanel gamePanel){
        window = new JFrame();
        window.setSize(gamePanel.WIDTH, gamePanel.HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();
        window.requestFocus();
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}
