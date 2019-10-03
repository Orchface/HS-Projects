package main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class UI
{
    private JFrame frame;
    private JLabel p1Name;
    private JLabel pig1;
    private JLabel pig2;
    private JLabel p2Name;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel p1TurnScore;
    private JLabel p2TurnScore;
    private boolean turn;
    private boolean anim;
    private boolean bk;
    private JTextField textField;
    private JTextArea bkg1;
    private JTextArea bkg2;
    private JButton p1Roll;
    private JButton p1Hold;
    private JButton p2Roll;
    private JButton p2Hold;
    private Player p1;
    private Player p2;
    private ImageIcon shaded;
    private ImageIcon unshaded;
    private Timer t;
    private JLabel lblGoal;
    
    public void run(final String p1N, final String p2N, final int p1G, final int p2G, final boolean aFlag) {
        this.anim = aFlag;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final UI window = new UI(p1N, p2N, p1G, p2G, aFlag);
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public UI(final String a, final String b, final int c, final int d, final boolean bFlag) {
        this.anim = true;
        this.bk = false;
        this.shaded = new ImageIcon(Home.class.getClassLoader().getResource("pigshaded.png"));
        this.unshaded = new ImageIcon(Home.class.getClassLoader().getResource("pigunshaded.png"));
        this.t = new Timer();
        this.anim = bFlag;
        this.initialize(a, b, c, d);
    }
    
    public void close() {
        this.frame.dispose();
    }
    
    private void initialize(final String a, final String b, final int c, final int d) {
        this.p1 = new Player(a, c);
        this.p2 = new Player(b, d);
        (this.frame = new JFrame()).setTitle("Game of Pig");
        this.frame.setBounds(100, 100, 800, 482);
        this.frame.setDefaultCloseOperation(1);
        this.frame.getContentPane().setLayout(null);
        (this.p1Name = new JLabel(this.p1.getName())).setHorizontalAlignment(0);
        this.p1Name.setFont(new Font("Tahoma", 0, 18));
        this.p1Name.setBounds(0, 0, 390, 29);
        this.frame.getContentPane().add(this.p1Name);
        (this.p2Name = new JLabel(this.p2.getName())).setHorizontalAlignment(0);
        this.p2Name.setFont(new Font("Tahoma", 0, 18));
        this.p2Name.setBounds(394, 0, 390, 29);
        this.frame.getContentPane().add(this.p2Name);
        (this.p1Score = new JLabel("Score: 0")).setFont(new Font("Tahoma", 0, 18));
        this.p1Score.setHorizontalAlignment(0);
        this.p1Score.setBounds(0, 25, 390, 31);
        this.frame.getContentPane().add(this.p1Score);
        (this.p2Score = new JLabel("Score: 0")).setFont(new Font("Tahoma", 0, 18));
        this.p2Score.setHorizontalAlignment(0);
        this.p2Score.setBounds(394, 25, 390, 31);
        this.frame.getContentPane().add(this.p2Score);
        final int gg = new Random().nextInt(2);
        this.turn = (gg == 0);
        (this.p1TurnScore = new JLabel("This Turn: 0")).setFont(new Font("Tahoma", 0, 17));
        this.p1TurnScore.setHorizontalAlignment(0);
        this.p1TurnScore.setBounds(0, 281, 390, 31);
        this.frame.getContentPane().add(this.p1TurnScore);
        (this.p2TurnScore = new JLabel("This Turn: 0")).setFont(new Font("Tahoma", 0, 17));
        this.p2TurnScore.setHorizontalAlignment(0);
        this.p2TurnScore.setBounds(394, 281, 390, 31);
        this.frame.getContentPane().add(this.p2TurnScore);
        (this.textField = new JTextField()).setBackground(Color.BLACK);
        this.textField.setEditable(false);
        this.textField.setBounds(390, 30, 4, 450);
        this.frame.getContentPane().add(this.textField);
        this.textField.setColumns(10);
        (this.pig1 = new JLabel("")).setBounds(92, 50, 200, 200);
        this.frame.getContentPane().add(this.pig1);
        (this.lblGoal = new JLabel("Goal: " + this.p1.getGoal())).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.lblGoal.setHorizontalAlignment(0);
        this.lblGoal.setFont(new Font("Tahoma", 0, 18));
        this.lblGoal.setBounds(313, 0, 158, 29);
        this.frame.getContentPane().add(this.lblGoal);
        (this.pig2 = new JLabel("")).setBounds(495, 50, 200, 200);
        this.frame.getContentPane().add(this.pig2);
        (this.p1Hold = new JButton("HOLD")).setBackground(new Color(178, 34, 34));
        this.p1Hold.setBounds(0, 390, 390, 61);
        this.frame.getContentPane().add(this.p1Hold);
        this.p1Hold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                UI.this.p1.endTurn();
                UI.this.p1.changeTurnScore(0);
                UI.this.p1Score.setText("Score: " + UI.this.p1.getScore());
                UI.this.p1TurnScore.setText("This Turn:");
                UI.access$1(UI.this, false);
                UI.this.updateButtons();
            }
        });
        (this.p1Roll = new JButton("ROLL")).setBackground(new Color(50, 205, 50));
        this.p1Roll.setBounds(0, 330, 390, 61);
        this.frame.getContentPane().add(this.p1Roll);
        this.p1Roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final Die d = new Die();
                final int num = d.roll();
                if (UI.this.anim) {
                    final DiceAnimation di = new DiceAnimation();
                    di.start(num, UI.this.frame.getX(), UI.this.frame.getY());
                }
                if (num == 1) {
                    if (UI.this.anim) {
                        UI.this.t.scheduleAtFixedRate(new PIGDelay1(), 3800L, 5000L);
                    }
                    else {
                        UI.this.t.scheduleAtFixedRate(new PIGDelay1(), 1L, 5000L);
                    }
                }
                else {
                    UI.this.p1.incrementTurnScore(num);
                    UI.this.t.scheduleAtFixedRate(new NormDelay1(), 100L, 5000L);
                }
            }
        });
        (this.p2Hold = new JButton("HOLD")).setBackground(new Color(178, 34, 34));
        this.p2Hold.setBounds(394, 390, 390, 61);
        this.frame.getContentPane().add(this.p2Hold);
        this.p2Hold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                UI.this.p2.endTurn();
                UI.this.p2.changeTurnScore(0);
                UI.this.p2Score.setText("Score: " + UI.this.p2.getScore());
                UI.this.p2TurnScore.setText("This Turn:");
                UI.access$1(UI.this, true);
                UI.this.updateButtons();
            }
        });
        (this.p2Roll = new JButton("ROLL")).setBackground(new Color(50, 205, 50));
        this.p2Roll.setBounds(394, 330, 390, 61);
        this.frame.getContentPane().add(this.p2Roll);
        this.p2Roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                final Die d = new Die();
                final int num = d.roll();
                if (UI.this.anim) {
                    final DiceAnimation di = new DiceAnimation();
                    di.start(num, UI.this.frame.getX(), UI.this.frame.getY());
                }
                if (num == 1) {
                    if (UI.this.anim) {
                        UI.this.t.scheduleAtFixedRate(new PIGDelay2(), 3800L, 5000L);
                    }
                    else {
                        UI.this.t.scheduleAtFixedRate(new PIGDelay2(), 1L, 5000L);
                    }
                }
                else {
                    UI.this.p2.incrementTurnScore(num);
                    UI.this.t.scheduleAtFixedRate(new NormDelay2(), 100L, 5000L);
                }
            }
        });
        (this.bkg1 = new JTextArea()).setEditable(false);
        this.bkg1.setBounds(0, 0, 390, 462);
        this.frame.getContentPane().add(this.bkg1);
        (this.bkg2 = new JTextArea()).setEditable(false);
        this.bkg2.setBounds(394, 0, 390, 462);
        this.frame.getContentPane().add(this.bkg2);
        this.updateButtons();
    }
    
    private void updateButtons() {
        if (this.p1.getScore() >= this.p1.getGoal()) {
            if (!this.bk) {
                final int a = End.end(this.p1.getName());
                if (a == 2) {
                    System.exit(0);
                }
                else if (a == 0) {
                    new Home();
                    this.close();
                }
                this.bk = true;
            }
        }
        else if (this.p2.getScore() >= this.p2.getGoal() && !this.bk) {
            final int a = End.end(this.p2.getName());
            if (a == 2) {
                System.exit(0);
            }
            else if (a == 0) {
                new Home();
                this.close();
            }
            this.bk = true;
        }
        if (this.turn) {
            this.p1Hold.setEnabled(true);
            this.p1Roll.setEnabled(true);
            this.p2Hold.setEnabled(false);
            this.p2Roll.setEnabled(false);
            this.pig1.setIcon(this.shaded);
            this.pig2.setIcon(this.unshaded);
        }
        else {
            this.p2Hold.setEnabled(true);
            this.p2Roll.setEnabled(true);
            this.p1Hold.setEnabled(false);
            this.p1Roll.setEnabled(false);
            this.p1Roll.setVisible(true);
            this.pig2.setIcon(this.shaded);
            this.pig1.setIcon(this.unshaded);
        }
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() + 10, this.frame.getHeight());
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() - 10, this.frame.getHeight());
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() + 10, this.frame.getHeight());
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() - 10, this.frame.getHeight());
    }
    
    static /* synthetic */ void access$1(final UI ui, final boolean turn) {
        ui.turn = turn;
    }
    
    static /* synthetic */ void access$6(final UI ui, final Timer t) {
        ui.t = t;
    }
    
    private class PIGDelay1 extends TimerTask
    {
        @Override
        public void run() {
            UI.this.p1.changeTurnScore(0);
            UI.access$1(UI.this, false);
            UI.this.p1TurnScore.setText("This Turn:");
            UI.this.p1Hold.doClick();
            UI.this.updateButtons();
            UI.this.t.cancel();
            UI.access$6(UI.this, new Timer());
        }
    }
    
    private class PIGDelay2 extends TimerTask
    {
        @Override
        public void run() {
            UI.this.p2.changeTurnScore(0);
            UI.access$1(UI.this, true);
            UI.this.p2TurnScore.setText("This Turn:");
            UI.this.p2Hold.doClick();
            UI.this.updateButtons();
            UI.this.t.cancel();
            UI.access$6(UI.this, new Timer());
        }
    }
    
    private class NormDelay2 extends TimerTask
    {
        @Override
        public void run() {
            UI.this.p2TurnScore.setText("This Turn: " + UI.this.p2.getTurnScore());
            UI.this.t.cancel();
            UI.access$6(UI.this, new Timer());
        }
    }
    
    private class NormDelay1 extends TimerTask
    {
        @Override
        public void run() {
            UI.this.p1TurnScore.setText("This Turn: " + UI.this.p1.getTurnScore());
            UI.this.t.cancel();
            UI.access$6(UI.this, new Timer());
        }
    }
}
