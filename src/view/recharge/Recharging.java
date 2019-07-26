package view.recharge;

import util.Admin;
import util.DButil;
import util.User;
import view.Mai;
import view.Main;
import view.order.OrderList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Recharging extends JDialog {
    private JLabel adminIDLabel = new JLabel("管理员编号");
    private JTextField adminIDField = new JTextField();
    private JLabel adminNameLabel = new JLabel("管理员名称");
    private JTextField adminNameField = new JTextField();
    private JLabel userIDLabel = new JLabel("用户编号");
    private JTextField userIDField = new JTextField();
    private JLabel userNameLabel = new JLabel("用户名称");
    private JTextField userNameField = new JTextField();
    private JLabel userMoneyLabel = new JLabel("用户余额");
    private JTextField userMoneyField = new JTextField();
    private JLabel rechargeMoneyLabel = new JLabel("充值金额");
    private JTextField rechargeMoneyField = new JTextField();
    private JLabel rechargeInfoLabel = new JLabel("充值备注");
    private JTextArea rechargeInfoArea = new JTextArea();
    private JButton clearButton = new JButton("清空");
    private JButton rechargeButton = new JButton("确认充值");

    public Recharging(Admin admin, User user) {
        setTitle("用户充值");
        setSize(540, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-450, -370, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

        adminIDLabel.setBounds(50, 30, 120, 30);
        adminIDField.setBounds(200, 30, 200, 30);
        adminIDField.setText(admin.getAdminID());
        adminIDField.setEditable(false);

        adminNameLabel.setBounds(50, 80, 120, 30);
        adminNameField.setBounds(200, 80, 200, 30);
        adminNameField.setText(admin.getAdminName());
        adminNameField.setEditable(false);


        userIDLabel.setBounds(50, 130, 120, 30);
        userIDField.setBounds(200, 130, 200, 30);
        userIDField.setText(user.getUserID());
        userIDField.setEditable(false);

        userNameLabel.setBounds(50, 180, 120, 30);
        userNameField.setBounds(200, 180, 200, 30);
        userNameField.setText(user.getUserName());
        userNameField.setEditable(false);

        userMoneyLabel.setBounds(50, 230, 120, 30);
        userMoneyField.setBounds(200, 230, 200, 30);
        userMoneyField.setText(user.getUserMoney());
        userMoneyField.setEditable(false);

        rechargeMoneyLabel.setBounds(50, 280, 120, 30);
        rechargeMoneyField.setBounds(200, 280, 200, 30);

        rechargeInfoLabel.setBounds(50, 330, 120, 30);
        rechargeInfoArea.setBounds(200, 330, 200, 80);
        clearButton.setBounds(50, 450, 80, 30);
        rechargeButton.setBounds(250, 450, 160, 40);

        add(adminIDLabel);
        add(adminIDField);
        add(adminNameLabel);
        add(adminNameField);
        add(userIDLabel);
        add(userIDField);
        add(userNameLabel);
        add(userNameField);
        add(userMoneyLabel);
        add(userMoneyField);
        add(rechargeMoneyLabel);
        add(rechargeMoneyField);
        add(rechargeInfoLabel);
        add(rechargeInfoArea);
        add(clearButton);
        add(rechargeButton);

        add(bgLabel);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechargeMoneyField.setText("");
            }
        });

        rechargeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Double userMoney = Double.parseDouble(userMoneyField.getText()) + Double.parseDouble(rechargeMoneyField.getText());
                String rechargeMoney = rechargeMoneyField.getText();

                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String rechargeDate = dateFormat.format(date);
                String rechargeInfo = rechargeInfoArea.getText();
                Connection connection = DButil.getConnection();
                String sql = "insert into recharge (recharge_admin, recharge_user, recharge_num, recharge_date, recharge_info, recharge_recycle_bin) values (?, ?, ?, ?, ?, 0)";
                String sql1 = "update user set user_money = ? where user_id = ?";
                try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, admin.getAdminID());
                    ps.setObject(2, user.getUserID());
                    ps.setObject(3, rechargeMoney);
                    ps.setObject(4, rechargeDate);
                    ps.setObject(5, rechargeInfo);
                    int n = ps.executeUpdate();
                    if (n > 0) {
                        PreparedStatement ps1 = connection.prepareStatement(sql1);
                        ps1.setObject(1, userMoney);
                        ps1.setObject(2, user.getUserID());
                        int n1 = ps1.executeUpdate();
                        if (n1 > 0) {
                            Recharging.this.dispose();
                            JOptionPane.showMessageDialog(null, "用户充值成功");

                            Main.main.removeAll();
                            Main.main.repaint();
                            Main.main.updateUI();

                            Main.main.add(new RechargeList());
                        } else {
                            JOptionPane.showMessageDialog(null, "用户充值失败");
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection);
                }

            }
        });


        setVisible(true);
    }


}
