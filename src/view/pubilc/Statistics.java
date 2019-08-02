package view.pubilc;

import util.Admin;
import util.DButil;
import view.Main;
import view.order.rentOrderList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public Statistics(Admin admin) {
        setSize(1350, 800);
        setLayout(null);

        userItem.setBounds(600,50,150,50);
        userItem.setFont(new java.awt.Font("楷书",1,30));
        userItem.setForeground(Color.magenta);

        userLabel.setBounds(500, 100,500,40);
        userLabel.setFont(new java.awt.Font("楷体",1,15));
//        userLabel.setForeground(Color.cyan);

        carItem.setBounds(800,400,150,50);
        carItem.setFont(new java.awt.Font("宋体",1,70));
        carItem.setForeground(Color.pink);



        orderItem.setBounds(200,200,130,40);
        orderItem.setFont(new java.awt.Font("隶书",1,25));
        orderItem.setForeground(Color.red);

        orderPanel.setBounds(240,240,200,100);

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

                Connection connection = DButil.getConnection();
                String sql = "select user_id from user order by user_id desc limit 1";
                try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        userLabel.setText("公司从创办以来共计为" + rs.getString(1) + "名顾客服务过！再接再励！！！");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection);
                }
            }
        });
        orderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLabel.setVisible(false);
                orderPanel.setVisible(true);
            }
        });

        orderRentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.removeAll();
                Main.main.repaint();
                Main.main.updateUI();

                Main.main.add(new rentOrderList(admin));
            }
        });


        setVisible(true);
    }
}
