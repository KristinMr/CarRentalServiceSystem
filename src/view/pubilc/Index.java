package view.pubilc;

import util.Admin;
import view.Main;
import view.order.AddOrderUser;
import view.order.OrderList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JPanel {
    private JButton borrowCarButton = new JButton("租车");
    private JButton returnCarButton = new JButton("还车");

    public Index(Admin admin){

        setSize(1350,800);
        setLayout(null);

//        borrowCar.setBounds(100,100,);
//        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
//        JLabel bgLabel = new JLabel(imageIcon);


        borrowCarButton.setBounds(200,350,200,50);
        borrowCarButton.setFont(new java.awt.Font("楷体",1,20));
        returnCarButton.setBounds(600,350,200,50);
        returnCarButton.setFont(new java.awt.Font("楷体",1,20));
        add(borrowCarButton);
        add(returnCarButton);

//        add(bgLabel);


        borrowCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.removeAll();
                Main.main.repaint();
                Main.main.updateUI();

                Main.main.add(new AddOrderUser(admin));
            }
        });
        returnCarButton.addActionListener(new ActionListener() {
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
