package view.pubilc;

import util.Admin;
import view.Main;
import view.order.AddOrderUser;
import view.order.OrderList;
import view.recharge.AddRecharge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JPanel {

    private JLabel borrowCarLabel = new JLabel("^_^可以点击“租”字进行租车鸭^_^");
    private JMenuItem borrowCar = new JMenuItem("租");
    private JLabel returnCarLabel = new JLabel("^_^也可以点击“还”字进行还车呦^_^");
    private JMenuItem returnCar = new JMenuItem("还");

    private JMenuItem recharge = new JMenuItem("充值");

    public Index(Admin admin){

        setSize(1350,800);
        setLayout(null);

//        borrowCar.setBounds(100,100,);
//        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
//        JLabel bgLabel = new JLabel(imageIcon);


        borrowCarLabel.setBounds(300,140,300,30);
        borrowCarLabel.setForeground(Color.cyan);
        borrowCar.setBounds(300,180,250,250);
        borrowCar.setFont(new java.awt.Font("楷体",1,200));
        borrowCar.setForeground(Color.pink);

        returnCarLabel.setBounds(700,140,300,30);
        returnCarLabel.setForeground(Color.pink);
        returnCar.setBounds(700,180,250,250);
        returnCar.setFont(new java.awt.Font("楷体",1,200));
        returnCar.setForeground(Color.cyan);

        recharge.setBounds(500,550,300,100);
        recharge.setFont(new java.awt.Font("楷体",1,100));

        add(borrowCarLabel);
        add(borrowCar);
        add(returnCarLabel);
        add(returnCar);
        add(recharge);

//        add(bgLabel);


        borrowCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.removeAll();
                Main.main.repaint();
                Main.main.updateUI();

                Main.main.add(new AddOrderUser(admin));
            }
        });
        returnCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.removeAll();
                Main.main.repaint();
                Main.main.updateUI();

                Main.main.add(new OrderList(admin));

            }
        });
        recharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.removeAll();
                Main.main.repaint();
                Main.main.updateUI();

                Main.main.add(new AddRecharge(admin));
            }
        });

        setVisible(true);
    }
}
