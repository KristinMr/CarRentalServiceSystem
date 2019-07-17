package view.pubilc;

import javax.swing.*;

public class Main extends JPanel {
    private JButton borrowCar = new JButton("租车");
    private JButton returnCar = new JButton("还车");

    public Main(){

        setSize(1350,800);
        setLayout(null);

//        borrowCar.setBounds(100,100,);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        add(bgLabel);
        add(borrowCar);
        add(returnCar);

    }
}
