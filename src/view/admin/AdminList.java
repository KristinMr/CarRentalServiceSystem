package view.admin;

import util.Admin;
import util.DButil;
import view.Main;
import view.admin.UpdateAdmin;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AdminList extends JPanel {
    private JTextField searchAdminID = new JTextField("管理员编号关键字");
    private JTextField searchAdminName = new JTextField("管理员名称关键字");
    private JTextField searchAdminInfo = new JTextField("管理员备注关键字");
    private JButton refreshButton = new JButton("刷新");
    private JButton searchButton = new JButton("查询");

    private JButton editButton = new JButton("修改所选管理员");
    private JButton deleteButton = new JButton("删除所选管理员");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public AdminList(Admin admin) {
        setSize(1350,800);
        setLayout(null);

        searchAdminID.setForeground(Color.gray);
        searchAdminName.setForeground(Color.gray);
        searchAdminInfo.setForeground(Color.gray);

        searchAdminID.setBounds(15,40,150,30);
        searchAdminName.setBounds(185,40,150,30);
        searchAdminInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);

        editButton.setBounds(1000,40,150,40);
        deleteButton.setBounds(1170,40,150,40);

        jScrollPane.setBounds(15,100,1310,700);
        

        add(searchAdminID);
        add(searchAdminName);
        add(searchAdminInfo);
        add(refreshButton);
        add(searchButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);

        Vector<String> adminTHVector = new Vector<String>();
        adminTHVector.add("编号");
        adminTHVector.add("姓名");
        adminTHVector.add("性别");
        adminTHVector.add("身份证号");
        adminTHVector.add("年龄");
        adminTHVector.add("联系电话");
        adminTHVector.add("电子邮箱");
        adminTHVector.add("联系地址");
        adminTHVector.add("管理员等级");
        adminTHVector.add("管理员备注");

        Vector<Vector<String>> adminDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select admin.admin_id, admin.admin_name, admin.admin_sex, admin.admin_idn, admin.admin_age, admin.admin_tel, admin.admin_email, admin.admin_address, rank.rank_name, admin.admin_info from admin, rank where admin.admin_rank = rank.rank_id and admin_recycle_bin = 0";

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

                adminDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(adminDataVector, adminTHVector);
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
                    String info = (String)table.getValueAt(table.getSelectedRow(), 9);
                    new ShowInfo(info);
                };
            }
        });

        searchAdminID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchAdminID.getText().equals("管理员编号关键字") == true) {
                    searchAdminID.setText("");
                    searchAdminID.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchAdminID.getText();
                if (temp.equals("")) {
                    searchAdminID.setText("管理员编号关键字");
                    searchAdminID.setForeground(Color.gray);
                }
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

        searchAdminInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchAdminInfo.getText().equals("管理员备注关键字") == true) {
                    searchAdminInfo.setText("");
                    searchAdminInfo.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchAdminInfo.getText();
                if (temp.equals("")) {
                    searchAdminInfo.setText("管理员备注关键字");
                    searchAdminInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();

                Main.main.add(new AdminList(admin));
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminID = searchAdminID.getText();
                String adminName = searchAdminName.getText();
                String adminInfo = searchAdminInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select admin.admin_id, admin.admin_name, admin.admin_sex, admin.admin_idn, admin.admin_age, admin.admin_tel, admin.admin_email, admin.admin_address, rank.rank_name, admin.admin_info from admin, rank where admin.admin_rank = rank.rank_id and admin_recycle_bin = 0 ");

                List list = new ArrayList();

                if (adminID.trim().length() > 0 && adminID.equals("管理员编号关键字") == false) {
                    stringBuffer.append("and admin.admin_id like ? ");
                    list.add("%" + adminID + "%");
                }

                if (adminName.trim().length() > 0 && adminName.equals("管理员名称关键字") == false) {
                    stringBuffer.append("and admin.admin_name like ? ");
                    list.add("%" + adminName + "%");
                }

                if (adminInfo.trim().length() > 0 && adminInfo.equals("管理员备注关键字") == false) {
                    stringBuffer.append("and admin.admin_info like ? ");
                    list.add("%" + adminInfo + "%");
                }

                try {
                    PreparedStatement ps = connection1.prepareStatement(stringBuffer.toString());

                    for (int i = 0; i < list.size(); i++) {
                        ps.setObject(i + 1, list.get(i));
                    }

                    ResultSet rs = ps.executeQuery();
                    defaultTableModel.getDataVector().clear();
                    defaultTableModel.fireTableDataChanged();

                    System.out.println(stringBuffer);
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

                        adminDataVector.add(vector);
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
                    JOptionPane.showMessageDialog(null, "请先选中要修改的管理员");
                    return;
                } else {
                    String adminID = (String)table.getValueAt(row,0);
                    new UpdateAdmin(admin, adminID);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要删除的管理员");
                    return;
                } else {
                    String adminID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选管理员移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update admin set admin_recycle_bin = 1 where admin_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, adminID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "管理员删除成功");
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
