package view.order;


import util.Admin;
import util.DButil;
import util.User;
import view.Main;
import view.recharge.AddRecharge;
import view.user.UpdateUser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AddOrderUser extends JPanel {
    private JTextField searchUserName = new JTextField("用户名称关键字");
    private JTextField searchUserTel = new JTextField("联系电话关键字");
    private JTextField searchUserInfo = new JTextField("用户备注关键字");
    private JButton refreshButton = new JButton("刷新");
    private JButton searchButton = new JButton("查询");
    
    private JButton addOrderUserButton = new JButton("给所选用户下单");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public AddOrderUser(Admin admin) {
        setSize(1350, 800);
        setLayout(null);

        searchUserName.setForeground(Color.gray);
        searchUserTel.setForeground(Color.gray);
        searchUserInfo.setForeground(Color.gray);

        searchUserName.setBounds(15, 40, 150, 30);
        searchUserTel.setBounds(185, 40, 150, 30);
        searchUserInfo.setBounds(355, 40, 150, 30);
        refreshButton.setBounds(720, 40, 80, 30);
        searchButton.setBounds(820, 40, 80, 30);
        
        addOrderUserButton.setBounds(1170, 40, 150, 40);

        jScrollPane.setBounds(15, 100, 1310, 700);


        add(searchUserName);
        add(searchUserTel);
        add(searchUserInfo);
        add(refreshButton);
        add(searchButton);
        add(addOrderUserButton);
        add(jScrollPane);

        Vector<String> userTHVector = new Vector<String>();
        userTHVector.add("编号");
        userTHVector.add("姓名");
        userTHVector.add("性别");
        userTHVector.add("身份证号");
        userTHVector.add("联系电话");
        userTHVector.add("电子邮箱");
        userTHVector.add("驾照编号");
        userTHVector.add("驾驶年龄");
        userTHVector.add("年龄");
        userTHVector.add("联系地址");
        userTHVector.add("剩余金额");
        userTHVector.add("用户备注");

        Vector<Vector<String>> userDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select user_id, user_name, user_sex, user_idn, user_tel, user_email, user_dln, user_dage, user_age, user_address, user_money, user_info from user where user_recycle_bin = 0";

        try {
            PreparedStatement ps = collection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                vector.add(rs.getString(7));
                vector.add(rs.getString(8));
                vector.add(rs.getString(9));
                vector.add(rs.getString(10));
                vector.add(rs.getString(11));
                vector.add(rs.getString(12));

                userDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(userDataVector, userTHVector);
        table.setModel(defaultTableModel);
        jScrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        searchUserName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchUserName.getText().equals("用户名称关键字") == true) {
                    searchUserName.setText("");
                    searchUserName.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchUserName.getText();
                if (temp.equals("")) {
                    searchUserName.setText("用户名称关键字");
                    searchUserName.setForeground(Color.gray);
                }
            }
        });

        searchUserTel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchUserTel.getText().equals("联系电话关键字") == true) {
                    searchUserTel.setText("");
                    searchUserTel.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchUserTel.getText();
                if (temp.equals("")) {
                    searchUserTel.setText("联系电话关键字");
                    searchUserTel.setForeground(Color.gray);
                }
            }
        });

        searchUserInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchUserInfo.getText().equals("用户备注关键字") == true) {
                    searchUserInfo.setText("");
                    searchUserInfo.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchUserInfo.getText();
                if (temp.equals("")) {
                    searchUserInfo.setText("用户备注关键字");
                    searchUserInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();

                Main.main.add(new AddOrderUser(admin));
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = searchUserName.getText();
                String userTel = searchUserTel.getText();
                String userInfo = searchUserInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from user where user_recycle_bin = 0 ");

                List list = new ArrayList();

                if (userName.trim().length() > 0 && userName.equals("用户名称关键字") == false) {
                    stringBuffer.append("and user_name like ? ");
                    list.add("%" + userName + "%");
                }

                if (userTel.trim().length() > 0 && userTel.equals("联系电话关键字") == false) {
                    stringBuffer.append("and user_tel like ? ");
                    list.add("%" + userTel + "%");
                }

                if (userInfo.trim().length() > 0 && userInfo.equals("用户备注关键字") == false) {
                    stringBuffer.append("and user_info like ? ");
                    list.add("%" + userInfo + "%");
                }

                try {
                    PreparedStatement ps = connection1.prepareStatement(stringBuffer.toString());

                    for (int i = 0; i < list.size(); i++) {
                        ps.setObject(i + 1, list.get(i));
                    }

                    ResultSet rs = ps.executeQuery();
                    defaultTableModel.getDataVector().clear();
                    defaultTableModel.fireTableDataChanged();

                    while (rs.next()) {
                        Vector<String> vector = new Vector<String>();
                        vector.add(rs.getString(1));
                        vector.add(rs.getString(2));
                        vector.add(rs.getString(5));
                        vector.add(rs.getString(6));
                        vector.add(rs.getString(7));
                        vector.add(rs.getString(8));
                        vector.add(rs.getString(9));
                        vector.add(rs.getString(10));
                        vector.add(rs.getString(11));
                        vector.add(rs.getString(12));
                        vector.add(rs.getString(13));
                        vector.add(rs.getString(14));

                        userDataVector.add(vector);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection1);
                }
            }
        });

        addOrderUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要下单的用户");
                    return;
                } else {
                    String userID = (String) table.getValueAt(row, 0);
                    String userDLN = (String) table.getValueAt(row,6);
                    if (userDLN == null) {
                        int m = JOptionPane.showConfirmDialog(null, "用户信息不完善，没有驾照编号，前往到用户信息编辑界面进行添加？","驾照信息不完善",JOptionPane.YES_NO_OPTION);
                        if (m == 0) {
                            Main.main.removeAll();
                            Main.main.repaint();
                            Main.main.updateUI();

                            Main.main.add(new UpdateUser(admin, userID));
                        }
                    } else {
                        String userMoney = (String) table.getValueAt(row, 10);
                        Double lowMoney = 3000.00;
                        if (Double.parseDouble(userMoney) < lowMoney) {
                            int m = JOptionPane.showConfirmDialog(null, "押金3000元，至少还需充值" + (lowMoney - Double.parseDouble(userMoney)) + "元人们币，前往到用户界面进行充值？","押金不足",JOptionPane.YES_NO_OPTION);
                            if (m == 0) {
                                Main.main.add(new AddRecharge(admin));
                            }
                        } else {
                            User user = new User();

                            Connection connection1 = DButil.getConnection();
                            String sql1 = "select user_id, user_name, user_money from user where user_id = ?";
                            try {
                                PreparedStatement ps = connection1.prepareStatement(sql1);
                                ps.setObject(1, userID);
                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    user.setUserID(rs.getString(1));
                                    user.setUserName(rs.getString(2));
                                    user.setUserMoney(rs.getString(3));
                                    new AddOrderCar(admin, user);
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            } finally {
                                DButil.releaseConnection(connection1);
                            }
                        }
                    }
                }
            }
        });

        setVisible(true);
    }
}
