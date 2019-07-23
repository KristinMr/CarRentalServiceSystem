package view.user;

import util.Admin;
import util.DButil;
import util.User;
import view.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateUser extends JDialog {


    private JLabel userNameLabel = new JLabel("用户姓名");
    private JTextField userNameField = new JTextField();

    private JLabel userTelLabel = new JLabel("联系电话");
    private JTextField userTelField = new JTextField();

    private JLabel userSexLabel = new JLabel("性别");
    private JRadioButton maleButton = new JRadioButton("男");
    private JRadioButton femaleButton = new JRadioButton("女");

    private JLabel userIDNLabel = new JLabel("身份证号");
    private JTextField userIDNField = new JTextField();

    private JLabel userEmailLabel = new JLabel("电子邮箱");
    private JTextField userEmailField = new JTextField();

    private JLabel userDrivingLicenseLabel = new JLabel("驾照编号");
    private JTextField userDrivingLicenseField = new JTextField();

    private JLabel userDriveAgeLabel = new JLabel("驾驶年龄");
    private JTextField userDriveAgeField = new JTextField();

    private JLabel userAddressLabel = new JLabel("联系地址");
    private JTextField userAddressField = new JTextField();

    private JLabel userInfoLabel = new JLabel("用户简介");
    private JTextArea userInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton confirmButton = new JButton("确认修改用户信息");

    public UpdateUser(Admin admin, String userID) {
        setTitle("管理员：" + admin.getAdminName() + " 正在更新用户信息");
        setSize(1050, 750);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-100, -100, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

        userNameLabel.setBounds(80, 50, 80, 30);
        userNameField.setBounds(180, 50, 250, 30);

        userSexLabel.setBounds(550, 50, 80, 30);
        maleButton.setBounds(650, 50, 125, 30);
        femaleButton.setBounds(775, 50, 125, 30);

        userTelLabel.setBounds(80, 120, 80, 30);
        userTelField.setBounds(180, 120, 250, 30);

        userEmailLabel.setBounds(550, 120, 80, 30);
        userEmailField.setBounds(650, 120, 250, 30);

        userIDNLabel.setBounds(80, 190, 80, 30);
        userIDNField.setBounds(180, 190, 250, 30);

        userAddressLabel.setBounds(550, 190, 80, 30);
        userAddressField.setBounds(650, 190, 250, 30);

        userDrivingLicenseLabel.setBounds(80, 260, 80, 30);
        userDrivingLicenseField.setBounds(180, 260, 250, 30);

        userDriveAgeLabel.setBounds(550, 260, 80, 30);
        userDriveAgeField.setBounds(650, 260, 250, 30);

        userInfoLabel.setBounds(80, 330, 80, 30);
        userInfoArea.setBounds(180, 330, 720, 200);

        resetButton.setBounds(100, 600, 120, 30);
        confirmButton.setBounds(750, 600, 200, 40);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        maleButton.setSelected(true);

        add(userTelLabel);
        add(userTelField);

        add(userNameLabel);
        add(userNameField);

        add(userSexLabel);
        add(maleButton);
        add(femaleButton);

        add(userIDNLabel);
        add(userIDNField);
        add(userEmailLabel);
        add(userEmailField);

        add(userDrivingLicenseLabel);
        add(userDrivingLicenseField);

        add(userDriveAgeLabel);
        add(userDriveAgeField);

        add(userAddressLabel);
        add(userAddressField);

        add(userInfoLabel);
        add(userInfoArea);

        add(resetButton);
        add(confirmButton);

        add(bgLabel);


        Connection connection = DButil.getConnection();
        String sql = "select user_name, user_sex, user_tel, user_email, user_idn, user_address, user_dln, user_dage, user_info from user where user_id = ?";

        User user = new User();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,userID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setUserName(rs.getString(1));
                user.setUserSex(rs.getString(2));
                user.setUserTel(rs.getString(3));
                user.setUserEmail(rs.getString(4));
                user.setUserIDN(rs.getString(5));
                user.setUserAddress(rs.getString(6));
                user.setUserDLN(rs.getString(7));
                user.setUserDAge(rs.getString(8));
                user.setUserInfo(rs.getString(9));

                userNameField.setText(user.getUserName());
                if (user.getUserSex().equals("男")) {
                    maleButton.setSelected(true);
                } else {
                    femaleButton.setSelected(true);
                }
                userTelField.setText(user.getUserTel());
                userEmailField.setText(user.getUserEmail());
                userIDNField.setText(user.getUserIDN());
                userAddressField.setText(user.getUserAddress());
                userDrivingLicenseField.setText(user.getUserDLN());
                userDriveAgeField.setText(user.getUserDAge());
                userInfoArea.setText(user.getUserInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        userIDNField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String id = userIDNField.getText();
                if (id.length() == 18) {
                    System.out.println("ok");
                } else {
                    System.out.println("no ok");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userNameField.setText(user.getUserName());
                if (user.getUserSex().equals("男")) {
                    maleButton.setSelected(true);
                } else {
                    femaleButton.setSelected(true);
                }
                userTelField.setText(user.getUserTel());
                userEmailField.setText(user.getUserEmail());
                userIDNField.setText(user.getUserIDN());
                userAddressField.setText(user.getUserAddress());
                userDrivingLicenseField.setText(user.getUserDLN());
                userDriveAgeField.setText(user.getUserDAge());
                userInfoArea.setText(user.getUserInfo());
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userName = userNameField.getText();
                String gender = "男";
                if (femaleButton.isSelected()) {
                    gender = "女";
                }
                String userTel = userTelField.getText();
                String userPassword = "123456";
                String userEmail = userEmailField.getText();
                String userIDN = userIDNField.getText();
                String userAddress = userAddressField.getText();
                String userDrivingLicense = userDrivingLicenseField.getText();
                String userDriveAge = userDriveAgeField.getText();
                String userAge = "18";
                String userInfo = userInfoArea.getText();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String userDateUpdate = dateFormat.format(new Date());
                if (userName.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户姓名不能为空！请重新输入。");
                } else {
                    Connection connection1 = DButil.getConnection();
                    String sql1 = "update user set user_name = ?, user_sex = ?, user_tel = ?, user_email = ?, user_idn = ?, user_address = ?, user_dln = ?, user_dage = ?, user_info = ?, user_admin_update = ?, user_date_update = ? where user_id = ?";
                    try {
                        PreparedStatement ps1 = connection1.prepareStatement(sql1);
                        ps1.setObject(1, userName);
                        ps1.setObject(2, gender);
                        ps1.setObject(3, userTel);
                        ps1.setObject(4, userEmail);
                        ps1.setObject(5, userIDN);
                        ps1.setObject(6, userAddress);
                        ps1.setObject(7, userDrivingLicense);
                        ps1.setObject(8, userDriveAge);
                        ps1.setObject(9, userInfo);
                        ps1.setObject(10, admin.getAdminID());
                        ps1.setObject(11, userDateUpdate);
                        ps1.setObject(12,userID);

                        int n = ps1.executeUpdate();

                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "用户信息更新成功！");
                            UpdateUser.this.dispose();

                            view.Main.main.removeAll();
                            view.Main.main.repaint();
                            view.Main.main.updateUI();

                            Main.main.add(new UserList(admin));
                        } else {
                            JOptionPane.showMessageDialog(null, "用户信息更新失败！");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        DButil.releaseConnection(connection1);
                    }
                }
            }
        });

        setVisible(true);
    }
}
