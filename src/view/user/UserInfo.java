package view.user;

import util.Admin;
import util.Chooser;
import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserInfo extends JPanel {

    private JLabel userTelLabel = new JLabel("联系电话");
    private JTextField userTelField = new JTextField();

    private JLabel userPasswordLabel = new JLabel("密码");
    private JPasswordField userPasswordField = new JPasswordField();

    private JLabel userRePasswordLabel = new JLabel("确认密码");
    private JPasswordField userRePasswordField = new JPasswordField();


    private JLabel userNameLabel = new JLabel("员工姓名");
    private JTextField userNameField = new JTextField();

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

    private JLabel userBirthdayLabel = new JLabel("生日");
    private JTextField userBirthdayField = new JTextField();

    private JLabel userAddressLabel = new JLabel("联系地址");
    private JTextField userAddressField = new JTextField();

    private JLabel userInfoLabel = new JLabel("用户简介");
    private JTextArea userInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public UserInfo(Admin admin) {
//            setTitle("新增员工");
        setSize(600, 800);
//            setLocationRelativeTo(null);
        setLayout(null);
//            setModal(true);

        userTelLabel.setBounds(40, 380, 60, 20);
        userTelField.setBounds(150, 380, 100, 20);

        userPasswordLabel.setBounds(40, 380, 60, 20);
        userPasswordField.setBounds(150, 380, 100, 20);

        userRePasswordLabel.setBounds(40, 380, 60, 20);
        userRePasswordField.setBounds(150, 380, 100, 20);

        userNameLabel.setBounds(40, 30, 60, 20);
        userNameField.setBounds(150, 30, 100, 20);

//            empdepLabel.setBounds(40, 170, 60, 20);
//            empdepBox.setBounds(150, 170, 100, 20);

        userSexLabel.setBounds(40, 240, 60, 20);
        maleButton.setBounds(150, 240, 100, 20);
        femaleButton.setBounds(250, 240, 100, 20);

        userIDNLabel.setBounds(40, 310, 60, 20);
        userIDNField.setBounds(150, 310, 100, 20);


        userEmailLabel.setBounds(40, 450, 60, 20);
        userEmailField.setBounds(150, 450, 100, 20);

        userDrivingLicenseLabel.setBounds(40, 450, 60, 20);
        userDrivingLicenseField.setBounds(150, 450, 100, 20);

        userDriveAgeLabel.setBounds(40, 450, 60, 20);
        userDriveAgeField.setBounds(150, 450, 100, 20);

        userBirthdayLabel.setBounds(40, 450, 60, 20);
        userBirthdayField.setBounds(150, 450, 100, 20);

        userAddressLabel.setBounds(40, 450, 60, 20);
        userAddressField.setBounds(150, 450, 100, 20);

        userInfoLabel.setBounds(1, 1, 1, 1);
        userInfoArea.setBounds(1, 1, 1, 1);

        resetButton.setBounds(40, 520, 60, 20);
        addButton.setBounds(190, 520, 60, 20);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        maleButton.setSelected(true);

        Chooser chooser = Chooser.getInstance();
        chooser.register(userBirthdayField);

        add(userTelLabel);
        add(userTelField);

        add(userPasswordLabel);
        add(userPasswordField);
        add(userRePasswordLabel);
        add(userRePasswordField);

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

        add(userBirthdayLabel);
        add(userBirthdayField);

        add(userAddressLabel);
        add(userAddressField);

        add(userInfoLabel);
        add(userInfoArea);

        add(resetButton);
        add(addButton);

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

                userTelField.setText("");
                userPasswordField.setText("");
                userRePasswordField.setText("");
                userNameField.setText("");
                maleButton.isSelected();
                userIDNField.setText("");
                userEmailField.setText("");
                userDrivingLicenseField.setText("");
                userDriveAgeField.setText("");
                userBirthdayField.setText("");
                userAddressField.setText("");
                userInfoArea.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userTel = userTelField.getText();
                String userPassword = userPasswordField.getText();
                String userRePassword = userRePasswordField.getText();
                if (userPassword != userRePassword) {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
                } else {
                    String userName = userNameField.getText();
                    int gender = 1;
                    if (femaleButton.isSelected()) {
                        gender = 2;
                    }

                    String userIDN = userIDNField.getText();
                    String userEmail = userEmailField.getText();
                    String userDrivingLicense = userDrivingLicenseField.getText();
                    String userDriveAge = userDriveAgeField.getText();
                    String userBirthday = userBirthdayField.getText();
                    String userAddress = userAddressField.getText();
                    String userInfo = userInfoArea.getText();


                    Connection connection = DButil.getConnection();
                    String sql = "insert into user(user_name, user_password, user_rank, user_sex, user_idn, user_tel, user_email, user_dln, user_dage, user_age, user_adderss, user_info, user_recycle_bin) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, userName);
                        ps.setObject(2, userPassword);
                        ps.setObject(3, 1);
                        ps.setObject(4, gender);
                        ps.setObject(5, userIDN);
                        ps.setObject(6, userTel);
                        ps.setObject(7, userEmail);
                        ps.setObject(8, userDrivingLicense);
                        ps.setObject(9, userDriveAge);
                        ps.setObject(10, 18);
                        ps.setObject(11, userAddress);
                        ps.setObject(12, userInfo);
                        ps.setObject(13, 0);


                        int n = ps.executeUpdate();

                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "新增成功！");
                        } else {
                            JOptionPane.showMessageDialog(null, "添加失败！");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        DButil.releaseConnection(connection);
                    }
                }

            }
        });

        setVisible(true);
    }
}
