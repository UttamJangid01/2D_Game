package main;

public class Game{
    private GamePanel gamePanel;
    public Game(){
        gamePanel = new GamePanel();
        new GameWindow(gamePanel);
    }
}
