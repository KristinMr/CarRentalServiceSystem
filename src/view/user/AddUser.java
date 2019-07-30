package view.user;

import util.Admin;
import util.DButil;
import view.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class AddUser extends JPanel {

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
    private JButton addButton = new JButton("添加用户");

    public AddUser(Admin admin) {
        setSize(1350, 750);
        setLayout(null);

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
        addButton.setBounds(750, 600, 150, 40);

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

                userNameField.setText("");
                maleButton.setSelected(true);
                userTelField.setText("");
                userEmailField.setText("");
                userIDNField.setText("");
                userAddressField.setText("");
                userDrivingLicenseField.setText("");
                userDriveAgeField.setText("");
                userInfoArea.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
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
//                String userAge = String.valueOf(Integer.parseInt(LocalDate.now().toString().substring(0, 3)) - Integer.parseInt(userIDNField.getText().substring(6, 9)));
                long userAge = Period.between(LocalDate.parse(userIDNField.getText().substring(6, 10) + "-" + userIDNField.getText().substring(10, 12) + "-" + userIDNField.getText().substring(12, 14)), LocalDate.now()).getYears();
                String userInfo = userInfoArea.getText();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String userDate = dateFormat.format(new Date());
                if (userName.equals("")) {
                    JOptionPane.showMessageDialog(null, "用户姓名不能为空！请重新输入。");
                } else {
                    if (userIDN.equals("")) {
                        JOptionPane.showMessageDialog(null, "用户身份证号不能为空！请重新输入。");
                    } else {
                        Connection connection = DButil.getConnection();
                        String sql = "insert into user(user_name, user_password, user_rank, user_sex, user_idn, user_tel, user_email, user_dln, user_dage, user_age, user_address, user_info, user_recycle_bin, user_admin, user_date, user_money) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        try {
                            PreparedStatement ps = connection.prepareStatement(sql);
                            ps.setObject(1, userName);
                            ps.setObject(2, userPassword);
                            ps.setObject(3, 3);
                            ps.setObject(4, gender);
                            ps.setObject(5, userIDN);
                            ps.setObject(6, userTel);
                            ps.setObject(7, userEmail);
                            ps.setObject(8, userDrivingLicense);
                            ps.setObject(9, userDriveAge);
                            ps.setObject(10, userAge);
                            ps.setObject(11, userAddress);
                            ps.setObject(12, userInfo);
                            ps.setObject(13, 0);
                            ps.setObject(14, admin.getAdminID());
                            ps.setObject(15, userDate);
                            ps.setObject(16, 0);

                            int n = ps.executeUpdate();

                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "用户新增成功！");
                                Main.main.removeAll();
                                Main.main.repaint();
                                Main.main.updateUI();

                                Main.main.add(new UserList(admin));
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
            }
        });

        setVisible(true);
    }
}
