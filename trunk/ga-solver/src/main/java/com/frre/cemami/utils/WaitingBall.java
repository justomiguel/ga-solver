/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frre.cemami.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Justo Vargas
 */
public class WaitingBall extends Thread {
    
    private JPanel box;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private boolean running = false;
    

    public WaitingBall(JPanel b) {
        box = b;
    }

    public void draw() {
        Graphics g = box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public void move() {
        if (!box.isVisible()) {
            return;
        }
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
        x += dx;
        y += dy;
        Dimension d = box.getSize();
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= d.width) {
            x = d.width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= d.height) {
            y = d.height - YSIZE;
            dy = -dy;
        }
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    @Override
    public void run() {
        running = true;
        try {
            draw();
            while(running){
                move();
                sleep(10);
            }
        }
        catch (InterruptedException e) {
        }
        Graphics g = box.getGraphics();
        g.clearRect(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    
}
