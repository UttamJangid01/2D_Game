package score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Score {
    GamePanel gp;
    private long hh, mm, ss;
    private int  frameCount = 0;
    public String time;
    private String HH, MM, SS;
    public Score(GamePanel gp){
        this.gp = gp;
        hh = mm = ss = 0;
        HH = MM = SS = "00";
        time = HH+":"+MM+":"+SS;
    }

    public void update(){
        frameCount++;
        if(frameCount >= gp.FPS){
            frameCount = 0;
            ss++;
            if(ss >= 60){
                ss = 0;
                mm++;
                if(mm >= 60){
                    mm = 0;
                    hh++;
                    if(hh >= 24){
                        hh = 0;
                    }
                }
            }
            SS = ss < 10 ? "0"+Long.toString(ss) : Long.toString(ss);
            MM = mm < 10 ? "0"+Long.toString(mm) : Long.toString(mm);
            HH = hh < 10 ? "0"+Long.toString(hh) : Long.toString(hh);
            time = HH+":"+MM+":"+SS;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(new Font("", Font.PLAIN, 20));
        g2.drawString("Timer : "+time, 5, 20);
        g2.drawString("Score : "+gp.coinManager.score, 200, 20);
    }
}
