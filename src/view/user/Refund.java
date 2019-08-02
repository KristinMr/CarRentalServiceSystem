package view.user;

import util.Admin;
import util.DButil;
import util.User;
import view.Login;
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

public class Refund extends JDialog {

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

    private JLabel refundMoneyLabel = new JLabel("退款金额");
    private JTextField refundMoneyField = new JTextField();


    private JLabel adminPasswordLabel = new JLabel("管理员密码");
    private JPasswordField adminPasswordField = new JPasswordField();
    
    private JButton resetButton = new JButton("重置");
    private JButton confirmButton = new JButton("确认退款");

    public Refund(Admin admin, String userID) {
        setTitle("用户退款");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        adminIDLabel.setBounds(50, 30, 120, 30);
        adminIDField.setBounds(180, 30, 200, 30);
        adminIDField.setText(admin.getAdminID());
        adminIDField.setEditable(false);

        adminNameLabel.setBounds(450, 30, 120, 30);
        adminNameField.setBounds(580, 30, 200, 30);
        adminNameField.setText(admin.getAdminName());
        adminNameField.setEditable(false);

        userIDLabel.setBounds(50, 80, 120, 30);
        userIDField.setBounds(180, 80, 200, 30);
        userIDField.setEditable(false);

        userNameLabel.setBounds(450, 80, 120, 30);
        userNameField.setBounds(580, 80, 200, 30);
        userNameField.setEditable(false);

        userMoneyLabel.setBounds(50, 130, 120, 30);
        userMoneyField.setBounds(180, 130, 200, 30);
        userMoneyField.setEditable(false);
        
        refundMoneyLabel.setBounds(450, 130, 120, 30);
        refundMoneyField.setBounds(580, 130, 200, 30);

        adminPasswordLabel.setBounds(50, 180, 120, 30);
        adminPasswordField.setBounds(180, 180, 600, 30);

        resetButton.setBounds(50, 250, 80, 30);
        confirmButton.setBounds(630, 250, 150, 40);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-450, -370, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

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
        add(refundMoneyLabel);
        add(refundMoneyField);
        add(adminPasswordLabel);
        add(adminPasswordField);
        add(resetButton);
        add(confirmButton);
        add(bgLabel);

        Connection connection = DButil.getConnection();
        String sql = "select user_id, user_name, user_money from user where user_id = ?";

        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,userID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setUserID(rs.getString(1));
                user.setUserName(rs.getString(2));
                user.setUserMoney(rs.getString(3));
                
                userIDField.setText(rs.getString(1));
                userNameField.setText(rs.getString(2));
                userMoneyField.setText(rs.getString(3));
                refundMoneyField.setText(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refundMoneyField.setText(user.getUserMoney());
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String refundMoney = refundMoneyField.getText();
                String adminPassword = adminPasswordField.getText();
                if (Double.parseDouble(refundMoney) > Double.parseDouble(user.getUserMoney())) {
                    JOptionPane.showMessageDialog(null, "退款金额不得大于用户余额！请重新输入。");
                } else {
                    if (!adminPassword.equals(admin.getAdminPassword())) {
                        JOptionPane.showMessageDialog(null, "管理员密码不正确！请重新输入。");
                    } else {
                        Double userMoney1 = Double.parseDouble(user.getUserMoney()) - Double.parseDouble(refundMoney);
                        String message = "退款用户：" + user.getUserName() + "，用户余额：" + user.getUserMoney() + "，退款金额：" + refundMoney + "，退款后用户余额：" + userMoney1 + "。确认退款？";
                        int m = JOptionPane.showConfirmDialog(null,message,"退款确认",JOptionPane.YES_NO_OPTION);
                        if (m == 0) {
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            Connection connection1 = DButil.getConnection();
                            String sql1 = "update user set user_money = ?, user_refund_admin = ?, user_refund_time = ? where user_id = ?";

                            User user = new User();
                            try {
                                PreparedStatement ps1 = connection1.prepareStatement(sql1);
                                ps1.setObject(1, userMoney1);
                                ps1.setObject(2, admin.getAdminID());
                                ps1.setObject(3, dateFormat.format(new Date()));
                                ps1.setObject(4, userID);

                                int n = ps1.executeUpdate();

                                if (n > 0) {
                                    JOptionPane.showMessageDialog(null, "退款成功！");
                                    Refund.this.dispose();
                                    view.Main.main.removeAll();
                                    view.Main.main.repaint();
                                    view.Main.main.updateUI();

                                    Main.main.add(new UserList(admin));
                                } else {
                                    JOptionPane.showMessageDialog(null, "退款失败！");
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
