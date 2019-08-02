package view.recharge;

import util.Admin;
import util.DButil;
import view.Main;
import view.pubilc.ShowInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RechargeList extends JPanel {
    private JTextField searchAdminName = new JTextField("管理员名称关键字");
    private JTextField searchUserName = new JTextField("用户名称关键字");
    private JTextField searchRechargeInfo = new JTextField("充值备注关键字");
    private JButton refreshButton = new JButton("刷新");
    private JButton searchButton = new JButton("查询");

    private JButton editButton = new JButton("修改所选充值记录");
    private JButton deleteButton = new JButton("删除所选充值记录");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public RechargeList(Admin admin) {
        setSize(1350,800);
        setLayout(null);

        searchAdminName.setForeground(Color.gray);
        searchUserName.setForeground(Color.gray);
        searchRechargeInfo.setForeground(Color.gray);

        searchAdminName.setBounds(15,40,150,30);
        searchUserName.setBounds(185,40,150,30);
        searchRechargeInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);

        editButton.setBounds(1000,40,150,40);
        deleteButton.setBounds(1170,40,150,40);

        jScrollPane.setBounds(15,100,1310,700);

        add(searchAdminName);
        add(searchUserName);
        add(searchRechargeInfo);
        add(refreshButton);
        add(searchButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);

        Vector<String> rechargeTHVector = new Vector<String>();
        rechargeTHVector.add("充值编号");
        rechargeTHVector.add("管理员编号");
        rechargeTHVector.add("管理员名称");
        rechargeTHVector.add("用户编号");
        rechargeTHVector.add("用户名称");
        rechargeTHVector.add("充值金额");
        rechargeTHVector.add("充值时间");
        rechargeTHVector.add("充值备注");

        Vector<Vector<String>> rechargeDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select recharge.recharge_id, admin.admin_id, admin.admin_name, user.user_id, user.user_name, recharge.recharge_num, recharge.recharge_date,recharge.recharge_info from recharge, admin, user where recharge.recharge_admin = admin.admin_id and recharge.recharge_user = user.user_id and recharge.recharge_recycle_bin = 0";

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
                vector.add(rs.getString(7).substring(0,19));
                vector.add(rs.getString(8));


                rechargeDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(rechargeDataVector, rechargeTHVector);
        table.setModel(defaultTableModel);
        jScrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String info = (String)table.getValueAt(table.getSelectedRow(), 7);
                    new ShowInfo(info);
                };
            }
        });

        searchAdminName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchAdminName.getText().equals("管理员名称关键字") == true) {
                    searchAdminName.setText("");
                    searchAdminName.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchAdminName.getText();
                if (temp.equals("")) {
                    searchAdminName.setText("管理员名称关键字");
                    searchAdminName.setForeground(Color.gray);
                }
            }
        });

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

        searchRechargeInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchRechargeInfo.getText().equals("充值备注关键字") == true) {
                    searchRechargeInfo.setText("");
                    searchRechargeInfo.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchRechargeInfo.getText();
                if (temp.equals("")) {
                    searchRechargeInfo.setText("充值备注关键字");
                    searchRechargeInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();

                Main.main.add(new RechargeList(admin));
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String AdminName = searchAdminName.getText();
                String UserName = searchUserName.getText();
                String RechargeInfo = searchRechargeInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select recharge.recharge_id, admin.admin_id, admin.admin_name, user.user_id, user.user_name, recharge.recharge_num, recharge.recharge_date,recharge.recharge_info from recharge, admin, user where recharge.recharge_admin = admin.admin_id and recharge.recharge_user = user.user_id and recharge.recharge_recycle_bin = 0 ");

                List list = new ArrayList();

                if (AdminName.trim().length() > 0 && AdminName.equals("管理员名称关键字") == false) {
                    stringBuffer.append("and admin.admin_name like ? ");
                    list.add("%" + AdminName + "%");
                }

                if (UserName.trim().length() > 0 && UserName.equals("用户名称关键字") == false) {
                    stringBuffer.append("and user.user_name like ? ");
                    list.add("%" + UserName + "%");
                }

                if (RechargeInfo.trim().length() > 0 && RechargeInfo.equals("充值备注关键字") == false) {
                    stringBuffer.append("and recharge.recharge_info like ? ");
                    list.add("%" + RechargeInfo + "%");
                }

                try {
                    PreparedStatement ps1 = connection1.prepareStatement(stringBuffer.toString());

                    for (int i = 0; i < list.size(); i++) {
                        ps1.setObject(i + 1, list.get(i));
                        System.out.println(stringBuffer);
                    }

                    ResultSet rs1 = ps1.executeQuery();
                    defaultTableModel.getDataVector().clear();
                    defaultTableModel.fireTableDataChanged();

                    while (rs1.next()) {
                        Vector<String> vector = new Vector<String>();
                        vector.add(rs1.getString(1));
                        vector.add(rs1.getString(2));
                        vector.add(rs1.getString(3));
                        vector.add(rs1.getString(4));
                        vector.add(rs1.getString(5));
                        vector.add(rs1.getString(6));
                        vector.add(rs1.getString(7).substring(0,19));
                        vector.add(rs1.getString(8));

                        rechargeDataVector.add(vector);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection1);
                }
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要修改的充值记录");
                    return;
                } else {
                    String rechargeID = (String)table.getValueAt(row,0);
                    String userID = (String)table.getValueAt(row, 3);
                    String rechargeMoney = (String)table.getValueAt(row, 5);
                    Connection connection1 = DButil.getConnection();
                    String sql1 = "select user_money from user where user_id = ?";

                    try {
                        PreparedStatement ps1 = connection1.prepareStatement(sql1);
                        ps1.setObject(1, userID);

                        ResultSet rs1 = ps1.executeQuery();
                        while (rs1.next()) {
                            if (Double.parseDouble(rs1.getString(1)) < Double.parseDouble(rechargeMoney)) {
                                JOptionPane.showMessageDialog(null, "用户余额小于充值记录中的充值金额！不能修改此记录，如果要修改，请先充值再修改。");
                            } else {
                                int m = JOptionPane.showConfirmDialog(null,"确认修改此充值记录？", "充值记录修改确认", JOptionPane.YES_NO_OPTION);
                                if (m == 0) {
                                    new UpdateRecharge(admin, rechargeID);
                                }
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        DButil.releaseConnection(connection1);
                    }

                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要删除的充值记录");
                    return;
                } else {
                    String rechargeID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选充值记录移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update recharge set recharge_recycle_bin = 1 where recharge_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, rechargeID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "充值记录删除成功");
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        } finally {
                            DButil.releaseConnection(connection1);
                        }
                    }
                }
            }
        });

        setVisible(true);
    }
}
