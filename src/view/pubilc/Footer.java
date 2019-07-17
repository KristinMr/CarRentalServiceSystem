package view.pubilc;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    private JLabel footerLabel = new JLabel("Copyright © 2019  Kristin");
    public Footer() {
        setSize(1600,20);
        setBackground(Color.black);
        footerLabel.setBounds(700,0,200,20);
        footerLabel.setForeground(Color.white);
        setLayout(null);
        add(footerLabel);
        setVisible(true);
    }
}
