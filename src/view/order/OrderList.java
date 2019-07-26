package view.order;

import util.Admin;
import util.DButil;
import view.order.UpdateOrder;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class OrderList extends JPanel {
    private JTextField searchOrderUserName = new JTextField("用户名称关键字");
    private JTextField searchOrderCarNumber = new JTextField("车牌号关键字");
    private JTextField searchOrderInfo = new JTextField("订单备注关键字");
    private JButton searchOrderButton = new JButton("查询");

    private JButton editButton = new JButton("修改所选订单");
    private JButton deleteButton = new JButton("删除所选订单");
    private JButton settleButton = new JButton("结算");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public OrderList(Admin admin) {
//        setTitle("订单列表");
        setSize(1350,800);
//        setLocationRelativeTo(null);
        setLayout(null);

        searchOrderUserName.setForeground(Color.gray);
        searchOrderCarNumber.setForeground(Color.gray);
        searchOrderInfo.setForeground(Color.gray);

        searchOrderUserName.setBounds(15,40,150,30);
        searchOrderCarNumber.setBounds(185,40,150,30);
        searchOrderInfo.setBounds(355,40,150,30);
        searchOrderButton.setBounds(620,40,80,30);
        searchOrderButton.setForeground(Color.blue);

        editButton.setBounds(800,40,150,40);
        deleteButton.setBounds(970,40,150,40);
        settleButton.setBounds(1170,40,150,50);
        settleButton.setFont(new java.awt.Font("楷体",1,18));
        settleButton.setForeground(Color.red);

        jScrollPane.setBounds(15,100,1310,700);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
//        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
//        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//        this.getContentPane().add(new JLabel());
//        ((JPanel) getContentPane()).setOpaque(false);

        add(searchOrderUserName);
        add(searchOrderCarNumber);
        add(searchOrderInfo);
        add(searchOrderButton);
        add(editButton);
        add(deleteButton);
        add(settleButton);
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
                if (!rs.getString(12).equals("已结算")) {
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


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        searchOrderUserName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchOrderUserName.getText().equals("用户名称关键字") == true) {
                    searchOrderUserName.setText("");
                    searchOrderUserName.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchOrderUserName.getText();
                if (temp.equals("")) {
                    searchOrderUserName.setText("用户名称关键字");
                    searchOrderUserName.setForeground(Color.gray);
                }
            }
        });

        searchOrderCarNumber.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchOrderCarNumber.getText().equals("车牌号关键字") == true) {
                    searchOrderCarNumber.setText("");
                    searchOrderCarNumber.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchOrderCarNumber.getText();
                if (temp.equals("")) {
                    searchOrderCarNumber.setText("车牌号关键字");
                    searchOrderCarNumber.setForeground(Color.gray);
                }
            }
        });

        searchOrderInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchOrderInfo.getText().equals("订单备注关键字") == true) {
                    searchOrderInfo.setText("");
                    searchOrderInfo.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchOrderInfo.getText();
                if (temp.equals("")) {
                    searchOrderInfo.setText("订单备注关键字");
                    searchOrderInfo.setForeground(Color.gray);
                }
            }
        });

        searchOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = searchOrderUserName.getText();
                String carNumber = searchOrderCarNumber.getText();
                String orderInfo = searchOrderInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select oorder.order_id, admin.admin_id, admin.admin_name, user.user_id, user.user_name, car.car_number, brand.brand_name, model.model_name, oorder.order_time, oorder.order_stime, oorder.order_etime, state.state_name, oorder.order_info from oorder, admin, user, car, model, brand, state where oorder.order_admin = admin.admin_id and oorder.order_user = user.user_id and oorder.order_car = car.car_id and car.car_model = model.model_id and model.model_brand = brand.brand_id and oorder.order_state = state.state_id and oorder.order_recycle_bin = 0 ");

                List list = new ArrayList();

                if (userName.trim().length() > 0 && userName.equals("用户名称关键字") == false) {
                    stringBuffer.append("and user.user_name like ? ");
                    list.add("%" + userName + "%");
                }

                if (carNumber.trim().length() > 0 && carNumber.equals("车牌号关键字") == false) {
                    stringBuffer.append("and car.car_number like ? ");
                    list.add("%" + carNumber + "%");
                }

                if (orderInfo.trim().length() > 0 && orderInfo.equals("订单备注关键字") == false) {
                    stringBuffer.append("and oorder.order_info like ? ");
                    list.add("%" + orderInfo + "%");
                }

                try {
                    PreparedStatement ps = connection1.prepareStatement(stringBuffer.toString());

                    for (int i = 0; i < list.size(); i++) {
                        ps.setObject(i + 1, list.get(i));
                        System.out.println(stringBuffer);
                    }

                    ResultSet rs = ps.executeQuery();
                    defaultTableModel.getDataVector().clear();
                    defaultTableModel.fireTableDataChanged();

                    System.out.println(stringBuffer);
                    while (rs.next()) {
                        if (list.size() == 0) {
                            if (!rs.getString(12).equals("已结算")) {
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
                        } else {
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
                    JOptionPane.showMessageDialog(null, "请先选中要修改的订单");
                    return;
                } else {
                    String orderID = (String)table.getValueAt(row,0);
                    new UpdateOrder(admin, orderID);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要删除的订单");
                    return;
                } else {
                    String orderID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "删除订单后，订单将会被移入回收站，您将会被记录成为此订单的删除者，同时订单中的车辆将会恢复成空闲状态，确认删除所选订单？","删除所选订单？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update oorder set order_delete_admin = ?, order_delete_time = ?, order_recycle_bin = 1 where order_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, admin.getAdminID());
                            ps.setObject(2, dateFormat.format(new Date()));
                            ps.setObject(3, orderID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "订单删除成功");
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

        settleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要结算的订单");
                    return;
                } else {
                    if ((table.getValueAt(row, 10)).equals("已结算")) {
                        JOptionPane.showMessageDialog(null, "此订单为已结算订单。");
                    } else {
                        String orderID = (String)table.getValueAt(row,0);
                        int m = JOptionPane.showConfirmDialog(null, "确认","结算所选订单？",JOptionPane.YES_NO_OPTION);
                        if (m == 0) {
                            new SettleOrder(admin, orderID);
                        }
                    }
                }
            }
        });

        setVisible(true);
    }

//    public static void main(String[] args) {
//        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
////            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        } catch (Exception e) {
//
//        }
//        new OrderList();
//    }
}
