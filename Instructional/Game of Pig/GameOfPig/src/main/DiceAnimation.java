package main;

import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class DiceAnimation extends JFrame
{
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private int number;
    private JLabel dice;
    
    public void start(final int num, final int x, final int y) {
        this.number = num;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final DiceAnimation frame = new DiceAnimation(num, x, y);
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public DiceAnimation() {
    }
    
    public DiceAnimation(final int num, final int x, final int y) throws IOException {
        this.number = num;
        this.setDefaultCloseOperation(1);
        this.setBounds(x, y, 800, 500);
        this.contentPane = new JPanel();
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        final ImageIcon gifImage = new ImageIcon(Home.class.getClassLoader().getResource("pig" + this.number + ".gif"));
        (this.dice = new JLabel(gifImage)).setBounds(80, 0, 640, 480);
        this.contentPane.add(this.dice);
        final Timer t = new Timer();
        this.dice.setIcon(gifImage);
        t.scheduleAtFixedRate(new Runner(), 3800L, 1L);
        t.scheduleAtFixedRate(new GIFStop(), 1400L, 4000L);
    }
    
    private void end() {
        this.dispatchEvent(new WindowEvent(this, 201));
    }
    
    private class Runner extends TimerTask
    {
        @Override
        public void run() {
            DiceAnimation.this.end();
        }
    }
    
    private class GIFStop extends TimerTask
    {
        @Override
        public void run() {
            DiceAnimation.this.dice.setIcon(new ImageIcon(Home.class.getClassLoader().getResource("pig" + DiceAnimation.this.number + ".png")));
        }
    }
}
