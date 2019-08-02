package view.recharge;

import util.Admin;
import util.DButil;
import util.User;
import view.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateRecharge extends JDialog {
    private JLabel adminIDLabel = new JLabel("管理员编号");
    private JTextField adminIDField = new JTextField();
    private JLabel adminNameLabel = new JLabel("管理员名称");
    private JTextField adminNameField = new JTextField();
    private JLabel userIDLabel = new JLabel("用户编号");
    private JTextField userIDField = new JTextField();
    private JLabel userNameLabel = new JLabel("用户名称");
    private JTextField userNameField = new JTextField();
    private JLabel userMoneyLabel = new JLabel("充值前用户余额");
    private JTextField userMoneyField = new JTextField();
    private JLabel rechargeMoneyLabel = new JLabel("充值金额");
    private JTextField rechargeMoneyField = new JTextField();
    private JLabel adminPasswordLabel = new JLabel("管理员密码");
    private JPasswordField adminPasswordField = new JPasswordField();
    private JLabel rechargeInfoLabel = new JLabel("添加备注");
    private JTextArea rechargeInfoArea = new JTextArea();
    private JButton resetButton = new JButton("重置");
    private JButton confirmButton = new JButton("确认修改充值记录");

    String userMoney;
    String rechargeMoney;
    String rehcargeInfo;

    public UpdateRecharge(Admin admin, String rechargeID) {
        setTitle("修改用户充值记录");
        setSize(540, 650);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

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
        userIDField.setEditable(false);

        userNameLabel.setBounds(50, 180, 120, 30);
        userNameField.setBounds(200, 180, 200, 30);
        userNameField.setEditable(false);

        userMoneyLabel.setBounds(50, 230, 120, 30);
        userMoneyField.setBounds(200, 230, 200, 30);
        userMoneyField.setEditable(false);

        rechargeMoneyLabel.setBounds(50, 280, 120, 30);
        rechargeMoneyField.setBounds(200, 280, 200, 30);

        adminPasswordLabel.setBounds(50, 330, 120, 30);
        adminPasswordField.setBounds(200, 330, 200, 30);

        rechargeInfoLabel.setBounds(50, 380, 120, 30);
        rechargeInfoArea.setBounds(200, 380, 200, 80);
        resetButton.setBounds(50, 500, 80, 30);
        confirmButton.setBounds(250, 500, 160, 40);

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
        add(adminPasswordLabel);
        add(adminPasswordField);
        add(rechargeInfoLabel);
        add(rechargeInfoArea);
        add(resetButton);
        add(confirmButton);

        add(bgLabel);

        Connection connection = DButil.getConnection();
        String sql = "select user.user_id, user.user_name, user.user_money, recharge.recharge_num, recharge.recharge_info from recharge, user where user.user_id = recharge.recharge_user and recharge.recharge_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, rechargeID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userIDField.setText(rs.getString(1));
                userNameField.setText(rs.getString(2));
                userMoneyField.setText(String.valueOf(Double.parseDouble(rs.getString(3)) - Double.parseDouble(rs.getString(4))));
                userMoney = rs.getString(3);
                rechargeMoneyField.setText(rs.getString(4));
                rechargeMoney = rs.getString(4);
                rehcargeInfo = rs.getString(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rechargeMoneyField.setText("");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double userMoney1 = Double.parseDouble(userMoneyField.getText()) + Double.parseDouble(rechargeMoneyField.getText());
                if (userMoney1 < 0) {
                    JOptionPane.showMessageDialog(null, "修改后用户余额小于0元！请重新修改充值金额。");
                } else {
                    if (!adminPasswordField.getText().equals(admin.getAdminPassword())) {
                        JOptionPane.showMessageDialog(null, "管理员密码不正确！请重新输入。");
                    } else {
                        int m = JOptionPane.showConfirmDialog(null, "确认", "确认修改充值记录？", JOptionPane.YES_NO_OPTION);
                        if (m == 0) {

                            String rechargeMoney1 = rechargeMoneyField.getText();

                            Date date = new Date();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String rechargeInfo1 = String.format("%s修改时间：%s，修改者编号：%s，修改者名称：%s，将充值金额(元RMB）由%s修改为%s，修改前用户余额%s，修改后用户余额%s，添加备注：%s。      ", rehcargeInfo, dateFormat.format(new Date()), admin.getAdminID(), admin.getAdminName(), rechargeMoney, rechargeMoney1, userMoney, userMoney1, rechargeInfoArea.getText());
                            Connection connection = DButil.getConnection();
                            String sql = "update recharge set recharge_num = ?, recharge_info = ? where recharge_id = ?";
                            String sql1 = "update user set user_money = ? where user_id = ?";
                            try {
                                PreparedStatement ps = connection.prepareStatement(sql);
                                ps.setObject(1, rechargeMoney1);
                                ps.setObject(2, rechargeInfo1);
                                ps.setObject(3, rechargeID);
                                int n = ps.executeUpdate();
                                if (n > 0) {
                                    PreparedStatement ps1 = connection.prepareStatement(sql1);
                                    ps1.setObject(1, userMoney1);
                                    ps1.setObject(2, userIDField.getText());
                                    int n1 = ps1.executeUpdate();
                                    if (n1 > 0) {
                                        UpdateRecharge.this.dispose();
                                        JOptionPane.showMessageDialog(null, "用户充值记录修改成功");

                                        Main.main.removeAll();
                                        Main.main.repaint();
                                        Main.main.updateUI();

                                        Main.main.add(new RechargeList(admin));
                                    } else {
                                        JOptionPane.showMessageDialog(null, "用户充值记录修改失败");
                                    }
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            } finally {
                                DButil.releaseConnection(connection);
                            }
                        }
                    }
                }
            }
        });


        setVisible(true);
    }

}
