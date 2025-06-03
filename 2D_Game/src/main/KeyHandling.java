package main;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;

public class KeyHandling implements KeyListener, MouseListener{
    public boolean space, up, down, left, right, attack, timer; 

    public KeyHandling(){
        space = left = right = attack = timer = false;
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            space = true;
        else if(e.getKeyCode() == KeyEvent.VK_W)
            up = true;
        else if(e.getKeyCode() == KeyEvent.VK_S)
            down = true;
        else if(e.getKeyCode() == KeyEvent.VK_A)
            left = true;
        else if(e.getKeyCode() == KeyEvent.VK_D)
            right = true;
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            space = false;
        else if(e.getKeyCode() == KeyEvent.VK_W)
            up = false;
        else if(e.getKeyCode() == KeyEvent.VK_S)
            down = false;
        else if(e.getKeyCode() == KeyEvent.VK_A)
            left = false;
        else if(e.getKeyCode() == KeyEvent.VK_D)
            right = false;
    }

    @Override
    public void keyTyped(KeyEvent e){
        timer = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        attack = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
