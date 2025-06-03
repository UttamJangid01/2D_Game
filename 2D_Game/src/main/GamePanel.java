package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import levels.Level;
import entity.Player;
import entity.CoinManager;
import entity.EnemyManager;
import entity.HelpMethods;
import background.Background;
import fileHandling.FileHandling;
import score.Score;

public class GamePanel extends JPanel implements Runnable{
    public final int TILE_SIZE = 32;
    public final int row = 25;
    public final int col = 50;
    public final int FPS = 120;
    public final int WIDTH = col * TILE_SIZE;
    public final int HEIGHT = row * TILE_SIZE;
    private boolean gameWin = false;

    public Thread gameThread;

    // ------- Objects ------- 
    public Level level;
    KeyHandling keyH = new KeyHandling();
    public FileHandling file; 
    Background background;
    public Player player;
    public EnemyManager enemyManager;
    HelpMethods help;
    public CoinManager coinManager;
    Score score;

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(keyH);
        this.setFocusable(true);
        
        level = new Level(this);
        file = new FileHandling(this);
        background = new Background(this);
        help = new HelpMethods(this);
        //player = new Player(this, 2 * TILE_SIZE, 4 * TILE_SIZE, 101, 71);
        player = new Player(this, 1 * TILE_SIZE, 1 * TILE_SIZE, 101, 71);
        enemyManager = new EnemyManager(this);
        coinManager = new CoinManager(this);
        score = new Score(this);
        
        level.setValues();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS; 
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        if(keyH.timer){
            score.update();
        }
        coinManager.update();
        player.update(keyH);
        enemyManager.update();
        gameWin();
    }

    private void gameWin(){
        if(coinManager.coinList.size() == 0 && enemyManager.enemys.size() == 0){
            gameThread = null;
            gameWin = true;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        score.draw(g2);
        background.draw(g2);
        coinManager.draw(g2);
        player.draw(g2);
        enemyManager.draw(g2);
        g2.setColor(Color.white);
        
        /*for(int i=0; i<=row; i++){
            g2.drawLine(0, i * TILE_SIZE, WIDTH, i * TILE_SIZE); 
        }
        for(int i=0; i<=col; i++){
            g2.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT);
        }*/
                
        if(gameThread == null && gameWin){
            g2.setColor(Color.green);
            g2.setFont(new Font("", Font.PLAIN, 45));
            g2.drawString("!! You Win !!", 700, 96);

            g2.setFont(new Font("", Font.PLAIN, 30));
            g2.drawString("Score : "+coinManager.score, 700, 146);
            g2.drawString("Time  : "+score.time, 700, 186);
        }
        else if(gameThread == null){
            g2.setColor(Color.red);
            g2.setFont(new Font("", Font.PLAIN, 45));
            g2.drawString("!! Game Over !!", 700, 96);

            g2.setFont(new Font("", Font.PLAIN, 30));
            g2.drawString("Score : "+coinManager.score, 700, 146);
            g2.drawString("Time  : "+score.time, 700, 186);
        }
        // g2.dispose();
    }
}
