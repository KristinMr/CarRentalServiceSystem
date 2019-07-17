package view.pubilc;

import view.Main;
import view.car.CarList;
import view.rank.RankList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sidebar extends JPanel {
    private JMenuItem car = new JMenuItem("车辆管理");
    private JPanel carPanel = new JPanel();
    private JMenuItem addCar = new JMenuItem("新增车辆");
    private JMenuItem carList = new JMenuItem("车辆列表");

    private JMenuItem state = new JMenuItem("状态管理");
    private JPanel statePanel = new JPanel();
    private JMenuItem addState = new JMenuItem("新增状态");
    private JMenuItem stateList = new JMenuItem("状态列表");

    private JMenu Location = new JMenu("地点管理");
    private JMenu carModel = new JMenu("车型管理");

    public Sidebar() {
        setSize(200,800);
        setLayout(null);
        car.setBounds(10,10,this.getWidth(),40);
        car.setFont(new java.awt.Font("楷体",3,20));

        carPanel.setBounds(0,car.getY() + car.getHeight(),this.getWidth(),100);
        addCar.setBounds(0,carPanel.getY() + carPanel.getHeight(),100,50);
        addCar.setFont(new java.awt.Font("楷体",2,18));
        carList.setBounds(0,addCar.getY()+addCar.getHeight(),this.getWidth(),50);
        carList.setFont(new java.awt.Font("楷体",2,18));

        state.setBounds(10,car.getY() + car.getHeight() + 10,this.getWidth(),40);
        state.setFont(new java.awt.Font("楷体",3,20));

        statePanel.setBounds(0,state.getY() + state.getHeight(),this.getWidth(),100);
        addState.setBounds(0,statePanel.getY() + 10,100,40);
        addState.setFont(new java.awt.Font("楷体",2,18));
        stateList.setBounds(0,addState.getY() + 50,this.getWidth(),40);
        stateList.setFont(new java.awt.Font("楷体",2,18));

        carPanel.setVisible(false);
        statePanel.setVisible(false);
        add(car);
        carPanel.add(addCar);
        carPanel.add(carList);
        add(carPanel);
        add(state);
        statePanel.add(addState);
        statePanel.add(stateList);
        add(statePanel);
        setVisible(true);

        car.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statePanel.setVisible(false);
                carPanel.setVisible(true);
                state.setBounds(10, + carPanel.getHeight(),car.getWidth(),40);
            }
        });

        carList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Main.main = new RankList();
//                rankList.setVisible(false);
//                Main.showCarList();
            }
        });

        state.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.setBounds(10,60 + carPanel.getHeight(),car.getWidth(),40);
                carPanel.setVisible(false);
                statePanel.setVisible(true);
            }
        });
    }

}
