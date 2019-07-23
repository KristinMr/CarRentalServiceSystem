package view.admin;

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
import java.util.Date;

public class AddAdmin extends JPanel {


    private JLabel adminNameLabel = new JLabel("管理员姓名");
    private JTextField adminNameField = new JTextField();

    private JLabel adminTelLabel = new JLabel("联系电话");
    private JTextField adminTelField = new JTextField();

    private JLabel adminSexLabel = new JLabel("性别");
    private JRadioButton maleButton = new JRadioButton("男");
    private JRadioButton femaleButton = new JRadioButton("女");

    private JLabel adminIDNLabel = new JLabel("身份证号");
    private JTextField adminIDNField = new JTextField();

    private JLabel adminEmailLabel = new JLabel("电子邮箱");
    private JTextField adminEmailField = new JTextField();

    private JLabel adminAddressLabel = new JLabel("联系地址");
    private JTextField adminAddressField = new JTextField();

    private JLabel adminInfoLabel = new JLabel("管理员简介");
    private JTextArea adminInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddAdmin(Admin admin) {
//            setTitle("新增管理员");
        setSize(1350, 750);
//            setLocationRelativeTo(null);
        setLayout(null);
//            setModal(true);

        adminNameLabel.setBounds(80, 50, 100, 30);
        adminNameField.setBounds(200, 50, 250, 30);

        adminSexLabel.setBounds(570, 50, 100, 30);
        maleButton.setBounds(670, 50, 125, 30);
        femaleButton.setBounds(795, 50, 125, 30);

        adminTelLabel.setBounds(80, 120, 100, 30);
        adminTelField.setBounds(200, 120, 250, 30);

        adminEmailLabel.setBounds(570, 120, 100, 30);
        adminEmailField.setBounds(670, 120, 250, 30);

        adminIDNLabel.setBounds(80, 190, 100, 30);
        adminIDNField.setBounds(200, 190, 250, 30);

        adminAddressLabel.setBounds(570, 190, 100, 30);
        adminAddressField.setBounds(670, 190, 250, 30);

        adminInfoLabel.setBounds(80, 260, 100, 30);
        adminInfoArea.setBounds(200, 260, 720, 200);

        resetButton.setBounds(100, 530, 120, 30);
        addButton.setBounds(770, 530, 150, 40);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);
        maleButton.setSelected(true);

        add(adminTelLabel);
        add(adminTelField);

        add(adminNameLabel);
        add(adminNameField);

        add(adminSexLabel);
        add(maleButton);
        add(femaleButton);

        add(adminIDNLabel);
        add(adminIDNField);
        add(adminEmailLabel);
        add(adminEmailField);

        add(adminAddressLabel);
        add(adminAddressField);

        add(adminInfoLabel);
        add(adminInfoArea);

        add(resetButton);
        add(addButton);

        adminIDNField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String id = adminIDNField.getText();
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

                adminNameField.setText("");
                maleButton.setSelected(true);
                adminTelField.setText("");
                adminEmailField.setText("");
                adminIDNField.setText("");
                adminAddressField.setText("");
                adminInfoArea.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String adminName = adminNameField.getText();
                String gender = "男";
                if (femaleButton.isSelected()) {
                    gender = "女";
                }
                String adminTel = adminTelField.getText();
                String adminPassword = "123456";
                String adminEmail = adminEmailField.getText();
                String adminIDN = adminIDNField.getText();
                String adminAddress = adminAddressField.getText();
                String adminAge = "18";
                String adminInfo = adminInfoArea.getText();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String adminDate = dateFormat.format(new Date());
                if (adminName.equals("")) {
                    JOptionPane.showMessageDialog(null, "管理员姓名不能为空！请重新输入。");
                } else {
                    Connection connection = DButil.getConnection();
                    String sql = "insert into admin(admin_name, admin_password, admin_rank, admin_sex, admin_idn, admin_tel, admin_email, admin_age, admin_address, admin_info, admin_recycle_bin, admin_admin, admin_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, adminName);
                        ps.setObject(2, adminPassword);
                        ps.setObject(3, 2);
                        ps.setObject(4, gender);
                        ps.setObject(5, adminIDN);
                        ps.setObject(6, adminTel);
                        ps.setObject(7, adminEmail);
                        ps.setObject(8, adminAge);
                        ps.setObject(9, adminAddress);
                        ps.setObject(10, adminInfo);
                        ps.setObject(11, 0);
                        ps.setObject(12, admin.getAdminID());
                        ps.setObject(13, adminDate);

                        int n = ps.executeUpdate();

                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "管理员新增成功！");
                            Main.main.removeAll();
                            Main.main.repaint();
                            Main.main.updateUI();

                            Main.main.add(new AdminList(admin));
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
