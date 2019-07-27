package view.pubilc;

import util.Admin;
import view.Main;
import view.order.AddOrderUser;
import view.order.OrderList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JPanel {
    private JMenuItem borrowCar = new JMenuItem("租");

    private JMenuItem returnCar = new JMenuItem("还");

    public Index(Admin admin){

        setSize(1350,800);
        setLayout(null);

//        borrowCar.setBounds(100,100,);
//        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
//        JLabel bgLabel = new JLabel(imageIcon);


        borrowCar.setBounds(300,250,250,250);
        borrowCar.setFont(new java.awt.Font("楷体",1,200));
        borrowCar.setForeground(Color.pink);
        returnCar.setBounds(700,250,250,250);
        returnCar.setFont(new java.awt.Font("楷体",1,200));
        returnCar.setForeground(Color.cyan);
        add(borrowCar);
        add(returnCar);

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

        setVisible(true);
    }
}
