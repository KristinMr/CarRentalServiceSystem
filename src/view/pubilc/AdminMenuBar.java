package view.pubilc;

import javax.swing.*;

public class AdminMenuBar extends JPanel {
    private ImageIcon imageIcon = new ImageIcon("/Users/cappuyang/IdeaProjects/CarRentalServiceSystem/src/source/Index.png");
    private JButton indexButton = new JButton(imageIcon);
    public AdminMenuBar() {
        setSize(1600,72);
        setLayout(null);
        indexButton.setBounds(20,0,180, 72);
        add(indexButton);
        setVisible(true);
    }
}
