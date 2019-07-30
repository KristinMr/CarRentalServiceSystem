package view.admin;

import util.Admin;
import util.DButil;
import view.admin.UpdateAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AdminList extends JPanel {
    private JTextField searchAdminID = new JTextField("编号关键字");
    private JTextField searchAdminName = new JTextField("名称关键字");
    private JTextField searchAdminInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
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
        refreshSearchButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);

        editButton.setBounds(1000,40,150,40);
        deleteButton.setBounds(1170,40,150,40);

        jScrollPane.setBounds(15,100,1310,700);
        

        add(searchAdminID);
        add(searchAdminName);
        add(searchAdminInfo);
        add(refreshSearchButton);
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
        String sql = "select admin.admin_id, admin.admin_name, admin.admin_sex, admin.admin_idn, admin.admin_age, admin.admin_tel, admin.admin_email, admin.admin_address, rank.rank_name, admin.admin_info from admin, rank where admin_recycle_bin = 0 and admin.admin_rank = rank.rank_id";

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
