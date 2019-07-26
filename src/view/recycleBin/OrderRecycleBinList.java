package view.recycleBin;

import util.DButil;
import view.order.UpdateOrder;

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

public class OrderRecycleBinList extends JPanel {

    private JTextField searchOrderID = new JTextField("编号关键字");
    private JTextField searchOrderName = new JTextField("名称关键字");
    private JTextField searchOrderInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchOrderButton = new JButton("查询");

    private JButton editButton = new JButton("移出回收站");
    private JButton deleteButton = new JButton("彻底删除所选订单");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public OrderRecycleBinList() {
//        setTitle("订单列表");
        setSize(1350, 800);
//        setLocationRelativeTo(null);
        setLayout(null);

        searchOrderID.setForeground(Color.gray);
        searchOrderName.setForeground(Color.gray);
        searchOrderInfo.setForeground(Color.gray);

        searchOrderID.setBounds(15, 40, 150, 30);
        searchOrderName.setBounds(185, 40, 150, 30);
        searchOrderInfo.setBounds(355, 40, 150, 30);
        refreshSearchButton.setBounds(720, 40, 80, 30);
        searchOrderButton.setBounds(820, 40, 80, 30);

        editButton.setBounds(1000, 40, 150, 40);
        deleteButton.setBounds(1170, 40, 150, 40);

        jScrollPane.setBounds(15, 100, 1310, 700);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
//        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
//        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//        this.getContentPane().add(new JLabel());
//        ((JPanel) getContentPane()).setOpaque(false);

        add(searchOrderID);
        add(searchOrderName);
        add(searchOrderInfo);
        add(refreshSearchButton);
        add(searchOrderButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);
        add(bgLabel);

        Vector<String> orderTHVector = new Vector<String>();
        orderTHVector.add("订单编号");
        orderTHVector.add("管理员编号");
        orderTHVector.add("管理员名称");
        orderTHVector.add("用户编号");
        orderTHVector.add("用户名称");
        orderTHVector.add("车牌号");
        orderTHVector.add("车辆型号");
        orderTHVector.add("订单时间");
        orderTHVector.add("起租时间");
        orderTHVector.add("还车时间");
        orderTHVector.add("订单状态");
        orderTHVector.add("订单备注");

        Vector<Vector<String>> orderDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select oorder.order_id, admin.admin_id, admin.admin_name, user.user_id, user.user_name, car.car_number, brand.brand_name, model.model_name, oorder.order_time, oorder.order_stime, oorder.order_etime, state.state_name, oorder.order_info from oorder, admin, user, car, model, brand, state where oorder.order_admin = admin.admin_id and oorder.order_user = user.user_id and oorder.order_car = car.car_id and car.car_model = model.model_id and model.model_brand = brand.brand_id and oorder.order_state = state.state_id and oorder.order_recycle_bin = 0";

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
                vector.add(rs.getString(7) + rs.getString(8));
                vector.add(rs.getString(9));
                vector.add(rs.getString(10));
                vector.add(rs.getString(11));
                vector.add(rs.getString(12));
                vector.add(rs.getString(13));

                orderDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(orderDataVector, orderTHVector);
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
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要移出回收站的订单");
                    return;
                } else {
                    String orderID = (String) table.getValueAt(row, 0);
                    Connection connection1 = DButil.getConnection();
                    String sql1 = "update oorder set order_recycle_bin = 0 where order_id = ?";
                    try {
                        PreparedStatement ps = connection1.prepareStatement(sql1);
                        ps.setObject(1, orderID);
                        int n = ps.executeUpdate();
                        if (n > 0) {
                            ((DefaultTableModel) table.getModel()).removeRow(row);
                            JOptionPane.showMessageDialog(null, "订单还原成功");
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
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要彻底删除的订单");
                    return;
                } else {
                    String orderID = (String) table.getValueAt(row, 0);
                    int m = JOptionPane.showConfirmDialog(null, "确认", "将所选订单彻底删除？", JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "delete from oorder where order_recycle_bin = 1 where order_id = ?";
                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, orderID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                ((DefaultTableModel) table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "订单彻底删除成功");
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
