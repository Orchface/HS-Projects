package main;

import java.awt.*;
import javax.swing.*;

public class End
{
    public static int end(final String s) {
        final Object[] options1 = { "PLAY AGAIN", "CONTINUE", "EXIT" };
        final JPanel panel = new JPanel();
        final JLabel lbl = new JLabel(String.valueOf(s) + " Wins!");
        lbl.setHorizontalAlignment(0);
        lbl.setFont(new Font("Tahoma", 0, 16));
        panel.add(lbl);
        final int a = JOptionPane.showOptionDialog(null, panel, String.valueOf(s) + " Wins!", 1, -1, null, options1, null);
        if (a == 2) {
            System.exit(0);
        }
        return a;
    }
}
