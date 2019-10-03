package main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class AI
{
    private JFrame frame;
    private JLabel p1Name;
    private JLabel pig1;
    private JLabel pig2;
    private JLabel p2Name;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel p1TurnScore;
    private JLabel lblGoal;
    private boolean turn;
    private boolean anim;
    private boolean rr;
    private boolean showAI;
    private boolean dd;
    private JTextField textField;
    private JTextArea bkg1;
    private JTextArea bkg2;
    private JButton p1Roll;
    private JButton p1Hold;
    private Player p1;
    private Player p2;
    private ImageIcon shaded;
    private ImageIcon unshaded;
    private Timer t;
    private JRadioButton show;
    private JTextArea textArea;
    private JScrollPane jsp;
    private JScrollBar vertical;
    
    public void run(final String name, final int goal, final boolean aFlag) {
        this.anim = aFlag;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final AI window = new AI(name, goal, aFlag);
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public AI(final String a, final int bb, final boolean bFlag) {
        this.anim = true;
        this.dd = false;
        this.shaded = new ImageIcon(Home.class.getClassLoader().getResource("pigshaded.png"));
        this.unshaded = new ImageIcon(Home.class.getClassLoader().getResource("pigunshaded.png"));
        this.t = new Timer();
        this.anim = bFlag;
        this.initialize(a, bb);
    }
    
    public void close() {
        this.frame.dispose();
    }
    
    private void initialize(final String a, final int b) {
        this.p1 = new Player(a, b);
        this.p2 = new Player("Computer", b);
        (this.frame = new JFrame()).setTitle("Game of Pig");
        this.frame.setBounds(100, 100, 800, 483);
        this.frame.setDefaultCloseOperation(1);
        this.frame.getContentPane().setLayout(null);
        (this.lblGoal = new JLabel("Goal: " + this.p1.getGoal())).setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.lblGoal.setHorizontalAlignment(0);
        this.lblGoal.setFont(new Font("Tahoma", 0, 18));
        this.lblGoal.setBounds(313, 0, 158, 29);
        this.frame.getContentPane().add(this.lblGoal);
        (this.p1Name = new JLabel(this.p1.getName())).setHorizontalAlignment(0);
        this.p1Name.setFont(new Font("Tahoma", 0, 18));
        this.p1Name.setBounds(0, 0, 390, 31);
        this.frame.getContentPane().add(this.p1Name);
        (this.p2Name = new JLabel("Computer")).setHorizontalAlignment(0);
        this.p2Name.setFont(new Font("Tahoma", 0, 18));
        this.p2Name.setBounds(394, 0, 390, 22);
        this.frame.getContentPane().add(this.p2Name);
        (this.show = new JRadioButton("Show AI Rolls")).setHorizontalAlignment(0);
        this.show.setFont(new Font("Times New Roman", 1, 17));
        this.show.setSelected(false);
        this.show.setBackground(Color.WHITE);
        this.show.setBounds(394, 262, 390, 23);
        this.frame.getContentPane().add(this.show);
        this.show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                if (!AI.this.showAI) {
                    AI.this.enableLog();
                }
                else {
                    AI.this.disableLog();
                }
                AI.access$11(AI.this, !AI.this.showAI);
            }
        });
        (this.textArea = new JTextArea()).setEditable(false);
        this.textArea.setBackground(new Color(192, 192, 192));
        (this.jsp = new JScrollPane(this.textArea, 20, 31)).setBounds(394, 332, 380, 119);
        this.jsp.setVisible(false);
        this.frame.getContentPane().add(this.jsp);
        this.vertical = this.jsp.getVerticalScrollBar();
        (this.p1Score = new JLabel("Score: 0")).setFont(new Font("Tahoma", 0, 18));
        this.p1Score.setHorizontalAlignment(0);
        this.p1Score.setBounds(0, 25, 390, 31);
        this.frame.getContentPane().add(this.p1Score);
        (this.p2Score = new JLabel("Score: 0")).setFont(new Font("Tahoma", 0, 18));
        this.p2Score.setHorizontalAlignment(0);
        this.p2Score.setBounds(394, 25, 390, 31);
        this.frame.getContentPane().add(this.p2Score);
        (this.p1TurnScore = new JLabel("This Turn: 0")).setFont(new Font("Tahoma", 0, 17));
        this.p1TurnScore.setHorizontalAlignment(0);
        this.p1TurnScore.setBounds(0, 281, 390, 31);
        this.frame.getContentPane().add(this.p1TurnScore);
        (this.textField = new JTextField()).setBackground(Color.BLACK);
        this.textField.setEditable(false);
        this.textField.setBounds(390, 30, 4, 450);
        this.frame.getContentPane().add(this.textField);
        this.textField.setColumns(10);
        (this.pig1 = new JLabel("")).setBounds(92, 50, 200, 200);
        this.frame.getContentPane().add(this.pig1);
        (this.pig2 = new JLabel("")).setToolTipText("hi");
        this.pig2.setBounds(495, 50, 200, 200);
        this.frame.getContentPane().add(this.pig2);
        (this.p1Hold = new JButton("HOLD")).setBackground(new Color(178, 34, 34));
        this.p1Hold.setBounds(0, 390, 390, 61);
        this.frame.getContentPane().add(this.p1Hold);
        this.p1Hold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                AI.this.p1.endTurn();
                AI.this.p1.changeTurnScore(0);
                AI.this.p1Score.setText("Score: " + AI.this.p1.getScore());
                AI.this.p1TurnScore.setText("This Turn:");
                AI.access$1(AI.this, false);
                AI.this.updateButtons();
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
                if (AI.this.anim) {
                    final DiceAnimation di = new DiceAnimation();
                    di.start(num, AI.this.frame.getX(), AI.this.frame.getY());
                }
                if (num == 1) {
                    if (AI.this.anim) {
                        AI.this.t.scheduleAtFixedRate(new PIGDelay1(), 3800L, 5000L);
                    }
                    else {
                        AI.this.t.scheduleAtFixedRate(new PIGDelay1(), 1L, 5000L);
                    }
                    AI.access$1(AI.this, false);
                }
                else {
                    AI.this.p1.incrementTurnScore(num);
                    AI.this.p1TurnScore.setText("This Turn: " + AI.this.p1.getTurnScore());
                }
            }
        });
        (this.bkg1 = new JTextArea()).setEditable(false);
        this.bkg1.setBounds(0, 0, 390, 462);
        this.frame.getContentPane().add(this.bkg1);
        (this.bkg2 = new JTextArea()).setEditable(false);
        this.bkg2.setBounds(394, 0, 390, 462);
        this.frame.getContentPane().add(this.bkg2);
        this.pig1.setIcon(this.shaded);
        this.pig2.setIcon(this.unshaded);
    }
    
    private void updateButtons() {
        if (this.p1.getScore() >= this.p1.getGoal()) {
            if (!this.rr) {
                final int a = End.end(this.p1.getName());
                if (a == 2) {
                    System.exit(0);
                }
                else if (a == 0) {
                    new Home();
                    this.close();
                }
                this.rr = true;
            }
        }
        else if (this.p2.getScore() >= this.p2.getGoal()) {
            final int a = End.end(this.p2.getName());
            if (a == 2) {
                System.exit(0);
            }
            else if (a == 0) {
                new Home();
                this.close();
            }
        }
        if (this.turn) {
            this.p1Hold.setEnabled(true);
            this.p1Roll.setEnabled(true);
            this.p2.changeTurnScore(0);
            this.p2Score.setText("Score: " + this.p2.getScore());
            this.pig1.setIcon(this.shaded);
            this.pig2.setIcon(this.unshaded);
        }
        else {
            this.p1Hold.setEnabled(false);
            this.p1Roll.setEnabled(false);
            this.p1Roll.setVisible(true);
            this.pig2.setIcon(this.shaded);
            this.pig1.setIcon(this.unshaded);
            if (!this.dd) {
                this.playWOS();
            }
        }
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() + 10, this.frame.getHeight());
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() - 10, this.frame.getHeight());
    }
    
    private void playWOS() {
        this.vertical.setValue(this.vertical.getMaximum());
        final int n = new Die().roll();
        this.textArea.append("Rolled a " + ((n == 1) ? "PIG" : Integer.valueOf(n)) + "\n");
        if (n == 1) {
            this.p2.changeTurnScore(0);
            this.turn = true;
        }
        else {
            this.p2.incrementTurnScore(n);
        }
        if (!this.turn && this.p2.getScore() + this.p2.getTurnScore() < this.p1.getScore() && this.p1.getScore() - (this.p2.getScore() + this.p2.getTurnScore()) >= 5) {
            for (int tmp = (this.p1.getScore() - (this.p2.getScore() + this.p2.getTurnScore())) / 3, i = 0; i < ((tmp > 4) ? 4 : tmp); ++i) {
                final Die di = new Die();
                final int num = di.roll();
                this.textArea.append("Rolled a " + ((num == 1) ? "PIG" : Integer.valueOf(num)) + "\n");
                if (num == 1) {
                    this.p2.changeTurnScore(0);
                    break;
                }
                this.p2.incrementTurnScore(num);
            }
        }
        this.p2.endTurn();
        this.textArea.append("[END OF TURN]\n\n");
        this.p2.changeTurnScore(0);
        this.p2Score.setText("Score: " + this.p2.getScore());
        this.turn = true;
        this.updateButtons();
    }
    
    private void enableLog() {
        this.jsp.setVisible(true);
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() + 1, this.frame.getHeight());
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() - 1, this.frame.getHeight());
    }
    
    private void disableLog() {
        this.jsp.setVisible(false);
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() + 1, this.frame.getHeight());
        this.frame.setBounds(this.frame.getX(), this.frame.getY(), this.frame.getWidth() - 1, this.frame.getHeight());
    }
    
    static /* synthetic */ void access$1(final AI ai, final boolean turn) {
        ai.turn = turn;
    }
    
    static /* synthetic */ void access$4(final AI ai, final Timer t) {
        ai.t = t;
    }
    
    static /* synthetic */ void access$11(final AI ai, final boolean showAI) {
        ai.showAI = showAI;
    }
    
    private class PIGDelay1 extends TimerTask
    {
        @Override
        public void run() {
            AI.this.p1.changeTurnScore(0);
            AI.access$1(AI.this, false);
            AI.this.p1TurnScore.setText("This Turn:");
            AI.this.p1.endTurn();
            AI.this.t.cancel();
            AI.access$4(AI.this, new Timer());
            AI.this.p1Hold.doClick();
            AI.this.updateButtons();
        }
    }
}
