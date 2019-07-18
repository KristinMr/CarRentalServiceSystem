package view.recharge;

import util.User;

import javax.swing.*;

public class Recharging extends JDialog {
    private JLabel userIDLabel = new JLabel("用户编号");
    private JTextField userIDField = new JTextField();
    private JLabel userNameLabel = new JLabel("用户名称");
    private JTextField userNameField = new JTextField();
    public Recharging(User user) {

        setSize(400,600);
    }
}
