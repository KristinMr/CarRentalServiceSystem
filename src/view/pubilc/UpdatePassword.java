package view.pubilc;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.Admin;
import util.DButil;
import view.Login;
import view.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdatePassword extends JDialog {

    private JLabel adminIDLabel = new JLabel("管理员编号");
    private JTextField adminIDField = new JTextField();

    private JLabel passwordLabel = new JLabel("原密码");
    private JPasswordField passwordField = new JPasswordField();

    private JLabel newPasswordLabel = new JLabel("新密码");
    private JPasswordField newPasswordField = new JPasswordField();

    private JLabel reNewPasswordLabel = new JLabel("确认新密码");
    private JPasswordField reNewPasswordField = new JPasswordField();

    private JButton clearButton = new JButton("清空");
    private JButton confirmButton = new JButton("确认更新密码");

    public UpdatePassword(Admin admin) {
        setTitle("更新用户密码");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        adminIDLabel.setBounds(50, 30, 80, 30);
        adminIDField.setBounds(140, 30, 150, 30);
        String adminID = admin.getAdminID();
        adminIDField.setText(adminID);
        adminIDField.setEditable(false);

        passwordLabel.setBounds(50, 80, 80, 30);
        passwordField.setBounds(140, 80, 150, 30);

        newPasswordLabel.setBounds(50, 130, 80, 30);
        newPasswordField.setBounds(140, 130, 150, 30);

        reNewPasswordLabel.setBounds(50, 180, 80, 30);
        reNewPasswordField.setBounds(140, 180, 150, 30);

        clearButton.setBounds(50, 250, 80, 30);
        confirmButton.setBounds(170, 250, 120, 30);


        add(adminIDLabel);
        add(adminIDField);
        add(passwordLabel);
        add(passwordField);
        add(newPasswordLabel);
        add(newPasswordField);
        add(reNewPasswordLabel);
        add(reNewPasswordField);
        add(clearButton);
        add(confirmButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
                newPasswordField.setText("");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String newPassword = newPasswordField.getText();
                String reNewPassword = reNewPasswordField.getText();
                if (!password.equals("") && !newPassword.equals("") && !reNewPassword.equals("")) {
                    if (password.equals(admin.getAdminPassword())) {
                        if (newPassword.equals(reNewPassword)) {
                            Connection connection1 = DButil.getConnection();
                            String sql = "update admin set admin_password = ? where admin_id = ?";
                            try {
                                PreparedStatement preparedStatement = connection1.prepareStatement(sql);
                                preparedStatement.setObject(1, reNewPassword);
                                preparedStatement.setObject(2, admin.getAdminID());
                                int n = preparedStatement.executeUpdate();
                                if (n > 0) {
                                    JOptionPane.showMessageDialog(null, "密码更新成功！请牢记新密码。");

                                    new Login();
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            } finally {
                                DButil.releaseConnection(connection1);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "两个新密码不一致！请重新输入。");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "旧密码不正确！请重新输入。");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "新密码和旧密码都不能为空！请重新输入。");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//            InitGlobalFont(new Font("楷体", 1, 15));
        } catch (Exception e) {

        }
        new UpdatePassword(new Admin());
    }
}
