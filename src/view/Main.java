package view;

import util.MyLayout;
import view.car.CarList;
import view.pubilc.AdminMenuBar;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
//    private JPanel menuBar = new JPanel();
    private JPanel menuBar = new AdminMenuBar();
    private JPanel sidebar = new JPanel();
    private JPanel main = new CarList();
    private JPanel footer = new JPanel();

    private JButton indexButton = new JButton("首页");

    private JButton jButton = new JButton("car");
    private JButton jButton1 = new JButton("car");
    private JButton jButton2 = new JButton("car");
    private JButton loginButton = new JButton("登录");

    public Main() {
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());


//        menuBar.setBackground(Color.BLACK);
        sidebar.setBackground(Color.green);
//        main.setBackground(Color.blue);
        footer.setBackground(Color.magenta);


        add(menuBar, MyLayout.setValues(0,0,2,1,1,0.08));
        add(sidebar, MyLayout.setValues(0,1,1,1,0.12,0.9));
        add(main, MyLayout.setValues(1,1,1,1,0.88,0.9));
        add(footer, MyLayout.setValues(0,2,2,1,1,0.02));
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
