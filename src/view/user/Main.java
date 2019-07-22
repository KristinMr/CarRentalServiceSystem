package view.user;

import util.MyLayout;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();

    private JPanel sidebarPanel = new JPanel();

    private JPanel mainPanel = new JPanel();

    private JPanel rightPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    public Main() {
        setTitle("消费管理系统");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        topPanel.setLayout(null);
        leftPanel.setLayout(null);
        sidebarPanel.setLayout(null);
        mainPanel.setLayout(null);
        rightPanel.setLayout(null);
        bottomPanel.setLayout(null);


        topPanel.setBackground(Color.BLACK);
        leftPanel.setBackground(Color.BLACK);
        sidebarPanel.setBackground(Color.BLUE);
        mainPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.BLACK);
        bottomPanel.setBackground(Color.BLACK);


        add(topPanel, MyLayout.setValues(0, 0, 4, 1, 1, 0.12));
        add(leftPanel, MyLayout.setValues(0, 1, 1, 1, 0.01, 0.85));

        add(sidebarPanel, MyLayout.setValues(1, 1, 1, 1, 0.15, 0.85));
        add(mainPanel, MyLayout.setValues(2, 1, 1, 1, 0.83, 0.85));

        add(rightPanel, MyLayout.setValues(3, 1, 1, 1, 0.01, 0.85));
        add(bottomPanel, MyLayout.setValues(2, 0, 4, 1, 1, 0.03));


        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
