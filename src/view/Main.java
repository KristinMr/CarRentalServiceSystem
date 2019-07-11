package view;

import view.car.AddCar;
import view.car.CarsInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {

    private JMenuBar menuBar = new JMenuBar();

    private JMenu carMenu = new JMenu("车辆管理");
    private JMenuItem addCarItem = new JMenuItem("新增车辆");
    private JMenuItem queryCarItem = new JMenuItem("查询车辆");

    private JMenu userMenu = new JMenu("用户管理");
    private JMenuItem userInfoItem = new JMenuItem("个人信息");

    public Main() {
        setTitle("欢迎使用车辆租赁系统");
        setSize(1200,900);
        setLocationRelativeTo(null);
        setLayout(null);

        menuBar.setBounds(10,10,1180,50);
        addCarItem.setBackground(Color.green);

        carMenu.add(addCarItem);
        carMenu.add(queryCarItem);

        userMenu.add(userInfoItem);

        menuBar.add(carMenu);
        menuBar.add(userMenu);

        add(menuBar);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "退出","真的要退出么？",JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        addCarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCar();
            }
        });

        queryCarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarsInfo();
            }
        });

        setVisible(true);
    }
}
