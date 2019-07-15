package view;

import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JLabel usernameLabel = new JLabel("用户名：");
    private JTextField usernameField = new JTextField("admin");

    private JLabel passwordLabel = new JLabel("密码：");
    private JTextField passwordField = new JPasswordField("123456");

    private JButton resetButton = new JButton("重置");
    private JButton loginButton = new JButton("登录");

    public Login() {
        setTitle("欢迎使用赛杰高级管理系统");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        usernameLabel.setBounds(40, 30, 60, 20);
        usernameField.setBounds(150, 30, 100, 20);

        passwordLabel.setBounds(40, 100, 60, 20);
        passwordField.setBounds(150, 100, 100, 20);

        resetButton.setBounds(40, 170, 60, 20);
        loginButton.setBounds(190, 170, 60, 20);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(resetButton);
        add(loginButton);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "退出", "真的要退出吗？", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                Connection connection = DButil.getConnection();

                String sql = "select user_name, user_password from user where user_name=? and user_password=?";

                try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, username);
                    ps.setObject(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Login.this.dispose();
                        new Mai();
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败");
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

    public static void main(String[] args) {
        new Login();
    }
}
