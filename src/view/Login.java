package view;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.Admin;
import util.DButil;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

public class Login extends JFrame {
    private JLabel adminIDLabel = new JLabel("用户编号");
    private JTextField adminIDField = new JTextField("1");

    private JLabel passwordLabel = new JLabel("密码");
    private JTextField passwordField = new JPasswordField("123456");

    private JButton clearButton = new JButton("清空");
    private JButton loginButton = new JButton("登录");

    public Login() {
        setTitle("欢迎登录租车管理系统");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-450, -370, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);


        adminIDLabel.setBounds(40, 50, 100, 30);
        adminIDField.setBounds(150, 50, 200, 30);

        passwordLabel.setBounds(40, 120, 100, 30);
        passwordField.setBounds(150, 120, 200, 30);

        clearButton.setBounds(120, 210, 80, 30);
        loginButton.setBounds(270, 210, 80, 30);

        add(adminIDLabel);
        add(adminIDField);
        add(passwordLabel);
        add(passwordField);
        add(clearButton);
        add(loginButton);
        add(bgLabel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "退出", "真的要退出吗？", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminIDField.setText("");
                passwordField.setText("");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminID = adminIDField.getText();
                String password = passwordField.getText();

                Connection connection = DButil.getConnection();

                String sql = "select * from admin where admin_id =? and admin_password=? and admin_recycle_bin = 0";

                try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, adminID);
                    ps.setObject(2, password);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        Login.this.dispose();
                        Admin admin = new Admin();
                        admin.setAdminID(rs.getString(1));
                        admin.setAdminName(rs.getString(2));
                        admin.setAdminPassword(rs.getString(3));
                        admin.setAdminSex(rs.getString(4));
                        admin.setAdminIDN(rs.getString(5));
                        admin.setAdminAge(rs.getString(6));
                        admin.setAdminTel(rs.getString(7));
                        admin.setAdminEmail(rs.getString(8));
                        admin.setAdminAddress(rs.getString(9));
                        admin.setAdminInfo(rs.getString(10));
                        admin.setAdminRank(rs.getInt(11));
                        new Main(admin);
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

    /**
     * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
     */
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys();
             keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    public static void main(String[] args) {
        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            InitGlobalFont(new Font("楷体", 1, 16));
        } catch (Exception e) {

        }
        new Login();
    }
}
