package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Home
{
    private JFrame frame;
    AI d;
    UI m;
    
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Home window = new Home();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Home() {
        this.initialize();
    }
    
    public void end(final AI ai) {
    }
    
    public void end(final UI ui) {
    }
    
    private void initialize() {
        (this.frame = new JFrame()).setVisible(true);
        this.frame.getContentPane().setForeground(new Color(0, 128, 128));
        this.frame.getContentPane().setBackground(new Color(192, 192, 192));
        this.frame.setTitle("The Game of Pig");
        this.frame.setBounds(100, 100, 450, 319);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout(null);
        final JButton twoPlayer = new JButton("2 Player");
        twoPlayer.setBackground(new Color(0, 128, 128));
        twoPlayer.setFont(new Font("Tahoma", 0, 15));
        twoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String p1 = null;
                String p2 = null;
                p1 = "";
                p2 = "";
                int goalp = 0;
                final JTextField xField = new JTextField(5);
                final JTextField yField = new JTextField(5);
                final JTextField goal = new JTextField(3);
                final JRadioButton btn = new JRadioButton("Enable Animated Dice");
                btn.setSelected(true);
                final JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("Player 1 Name:"));
                myPanel.add(xField);
                myPanel.add(Box.createHorizontalStrut(15));
                myPanel.add(new JLabel("Player 2 Name:"));
                myPanel.add(yField);
                myPanel.add(Box.createVerticalStrut(15));
                myPanel.add(new JLabel("Goal: "));
                myPanel.add(goal);
                myPanel.add(btn);
                final int result = JOptionPane.showConfirmDialog(null, myPanel, "2-Player Setup", 2);
                if (result == 0) {
                    p1 = xField.getText();
                    p2 = yField.getText();
                    try {
                        goalp = Integer.parseInt(goal.getText());
                    }
                    catch (Exception be) {
                        JOptionPane.showMessageDialog(null, "An invalid goal was entered, defaulting to standard 100 point goal.");
                        goalp = 100;
                    }
                    if (p1.length() >= 15) {
                        p1 = String.valueOf(p1.substring(0, 15)) + "...";
                    }
                    if (p2.length() >= 15) {
                        p2 = String.valueOf(p2.substring(0, 15)) + "...";
                    }
                    if (p1.equals("")) {
                        p1 = "Player 1";
                    }
                    if (p2.equals("")) {
                        p2 = "Player 2";
                    }
                    final UI m = new UI(p1, p2, goalp, goalp, btn.isSelected());
                    m.run(p1, p2, goalp, goalp, btn.isSelected());
                    Home.this.frame.dispose();
                }
            }
        });
        twoPlayer.setBounds(0, 242, 220, 50);
        this.frame.getContentPane().add(twoPlayer);
        twoPlayer.setSelected(false);
        this.frame.setResizable(false);
        final JButton AI = new JButton("Play Against AI");
        AI.setBackground(new Color(135, 206, 250));
        AI.setFont(new Font("Tahoma", 0, 15));
        AI.setBounds(218, 242, 216, 50);
        this.frame.getContentPane().add(AI);
        AI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String p1 = null;
                p1 = "";
                int goalp = 0;
                final JTextField xField = new JTextField(5);
                final JTextField goal = new JTextField(3);
                final JPanel myPanel = new JPanel();
                final JRadioButton btn = new JRadioButton("Enable Animated Dice");
                btn.setSelected(true);
                myPanel.add(new JLabel("Player Name:"));
                myPanel.add(xField);
                myPanel.add(Box.createHorizontalStrut(15));
                myPanel.add(Box.createVerticalStrut(15));
                myPanel.add(new JLabel("Goal: "));
                myPanel.add(goal);
                myPanel.add(btn);
                final int result = JOptionPane.showConfirmDialog(null, myPanel, "AI Setup", 2);
                if (result == 0) {
                    p1 = xField.getText();
                    try {
                        goalp = Integer.parseInt(goal.getText());
                    }
                    catch (Exception be) {
                        JOptionPane.showMessageDialog(null, "An invalid goal was entered, defaulting to standard 100 point goal.");
                        goalp = 100;
                    }
                    if (p1.length() >= 15) {
                        p1 = String.valueOf(p1.substring(0, 15)) + "...";
                    }
                    if (p1.equals("")) {
                        p1 = "Player 1";
                    }
                    (Home.this.d = new AI(p1, goalp, btn.isSelected())).run(p1, goalp, btn.isSelected());
                    Home.this.frame.dispose();
                }
            }
        });
        final JLabel title = new JLabel("The Game of Pig");
        title.setHorizontalAlignment(0);
        title.setFont(new Font("MoolBoran", 0, 40));
        title.setBounds(0, 0, 434, 39);
        this.frame.getContentPane().add(title);
        final JLabel image = new JLabel(new ImageIcon(Home.class.getClassLoader().getResource("pigshaded.png")));
        image.setBounds(118, 31, 200, 200);
        this.frame.getContentPane().add(image);
    }
}
