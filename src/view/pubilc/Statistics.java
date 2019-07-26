package view.pubilc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistics extends JPanel {
    private JMenuItem userItem = new JMenuItem("user");
    private JLabel userLabel = new JLabel();
    private JMenuItem carItem = new JMenuItem("car");
    private JMenuItem carBrandItem = new JMenuItem("car Brand");
    private JMenuItem carModelItem = new JMenuItem("car model");
    private JMenuItem orderItem = new JMenuItem("order");
    private JMenuItem orderReserveItem = new JMenuItem("reserve order");
    private JMenuItem orderRentItem = new JMenuItem("rent order");
    private JMenuItem orderSettledItem = new JMenuItem("settled order");

    private JPanel orderPanel = new JPanel();
    public Statistics() {
        setSize(1350, 800);
        setLayout(null);

        userItem.setBounds(600,50,150,50);
        userItem.setFont(new java.awt.Font("楷书",1,30));
        userItem.setForeground(Color.magenta);

        userLabel.setBounds(500, 100,500,40);
        userLabel.setFont(new java.awt.Font("楷体",1,15));
        userLabel.setForeground(Color.cyan);

        carItem.setBounds(800,400,150,50);
        carItem.setFont(new java.awt.Font("宋体",1,70));
        carItem.setForeground(Color.pink);



        orderItem.setBounds(200,200,130,40);
        orderItem.setFont(new java.awt.Font("隶书",1,25));
        orderItem.setForeground(Color.red);

        orderPanel.setBounds(240,240,200,100);
//        orderPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));

        orderReserveItem.setBounds(0,10,200,40);
        orderReserveItem.setFont(new java.awt.Font("隶书",1,20));
        orderReserveItem.setForeground(Color.green);
        orderRentItem.setBounds(0,60,200,40);
        orderRentItem.setFont(new java.awt.Font("隶书",1,30));
        orderRentItem.setForeground(Color.magenta);


        add(userItem);
        userLabel.setVisible(false);
        add(userLabel);

        add(carItem);

        orderPanel.setVisible(false);
        add(orderItem);
        orderPanel.add(orderReserveItem);
        orderPanel.add(orderRentItem);
        add(orderPanel);

        userItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderPanel.setVisible(false);
                userLabel.setVisible(true);
                userLabel.setText("公司从创办以来共计为1000名顾客服务过！再接再励！！！");
            }
        });
        orderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLabel.setVisible(false);
                orderPanel.setVisible(true);
            }
        });


        setVisible(true);
    }
}
