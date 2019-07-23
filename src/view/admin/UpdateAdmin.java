package view.admin;

import util.Admin;
import util.DButil;
import util.Admin;
import view.Main;
import view.admin.UpdateAdmin;
import view.admin.AdminList;

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

public class UpdateAdmin extends JDialog {

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
    private JButton confirmButton = new JButton("确认修改管理员信息");

    public UpdateAdmin(Admin admin, String adminID) {
        setTitle("管理员：" + admin.getAdminName() + " 正在更新管理员信息");
        setSize(1050, 750);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        ImageIcon imageIcon = new ImageIcon("C:\\Admins\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-100, -100, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

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
        confirmButton.setBounds(770, 530, 200, 40);

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
        add(confirmButton);

        add(bgLabel);


        Connection connection = DButil.getConnection();
        String sql = "select admin_name, admin_sex, admin_tel, admin_email, admin_idn, admin_address, admin_info from admin where admin_id = ?";

        Admin admin1 = new Admin();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,adminID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                admin1.setAdminName(rs.getString(1));
                admin1.setAdminSex(rs.getString(2));
                admin1.setAdminTel(rs.getString(3));
                admin1.setAdminEmail(rs.getString(4));
                admin1.setAdminIDN(rs.getString(5));
                admin1.setAdminAddress(rs.getString(6));
                admin1.setAdminInfo(rs.getString(7));

                adminNameField.setText(admin.getAdminName());
                if (admin1.getAdminSex().equals("男")) {
                    maleButton.setSelected(true);
                } else {
                    femaleButton.setSelected(true);
                }
                adminTelField.setText(admin1.getAdminTel());
                adminEmailField.setText(admin1.getAdminEmail());
                adminIDNField.setText(admin1.getAdminIDN());
                adminAddressField.setText(admin1.getAdminAddress());
                adminInfoArea.setText(admin1.getAdminInfo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
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

                adminNameField.setText(admin1.getAdminName());
                if (admin1.getAdminSex().equals("男")) {
                    maleButton.setSelected(true);
                } else {
                    femaleButton.setSelected(true);
                }
                adminTelField.setText(admin1.getAdminTel());
                adminEmailField.setText(admin1.getAdminEmail());
                adminIDNField.setText(admin1.getAdminIDN());
                adminAddressField.setText(admin1.getAdminAddress());
                adminInfoArea.setText(admin1.getAdminInfo());
            }
        });

        confirmButton.addActionListener(new ActionListener() {
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
                String adminDateUpdate = dateFormat.format(new Date());
                if (adminName.equals("")) {
                    JOptionPane.showMessageDialog(null, "管理员姓名不能为空！请重新输入。");
                } else {
                    Connection connection1 = DButil.getConnection();
                    String sql1 = "update admin set admin_name = ?, admin_sex = ?, admin_tel = ?, admin_email = ?, admin_idn = ?, admin_address = ?, admin_info = ?, admin_admin_update = ?, admin_date_update = ? where admin_id = ?";
                    try {
                        PreparedStatement ps1 = connection1.prepareStatement(sql1);
                        ps1.setObject(1, adminName);
                        ps1.setObject(2, gender);
                        ps1.setObject(3, adminTel);
                        ps1.setObject(4, adminEmail);
                        ps1.setObject(5, adminIDN);
                        ps1.setObject(6, adminAddress);
                        ps1.setObject(7, adminInfo);
                        ps1.setObject(8, admin.getAdminID());
                        ps1.setObject(9, adminDateUpdate);
                        ps1.setObject(10,adminID);

                        int n = ps1.executeUpdate();

                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "管理员信息更新成功！");
                            UpdateAdmin.this.dispose();

                            view.Main.main.removeAll();
                            view.Main.main.repaint();
                            view.Main.main.updateUI();

                            Main.main.add(new AdminList(admin));
                        } else {
                            JOptionPane.showMessageDialog(null, "管理员信息更新失败！");
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
