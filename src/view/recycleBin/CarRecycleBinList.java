package view.recycleBin;

import util.DButil;
import view.Main;
import view.car.UpdateCar;
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

public class CarRecycleBinList extends JPanel {

    private JTextField searchCarNumber = new JTextField("车牌号关键字");
    private JTextField searchCarColor = new JTextField("颜色关键字");
    private JTextField searchCarInfo = new JTextField("备注关键字");
    private JButton refreshButton = new JButton("刷新");
    private JButton searchButton = new JButton("查询");

    private JButton editButton = new JButton("移出回收站");
    private JButton deleteButton = new JButton("彻底删除");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public CarRecycleBinList() {
        setSize(1350,800);
        setLayout(null);

        searchCarNumber.setForeground(Color.gray);
        searchCarColor.setForeground(Color.gray);
        searchCarInfo.setForeground(Color.gray);

        searchCarNumber.setBounds(15,40,150,30);
        searchCarColor.setBounds(185,40,150,30);
        searchCarInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);
        searchButton.setForeground(Color.blue);

        editButton.setBounds(1000,40,150,40);
        editButton.setForeground(Color.green);
        deleteButton.setBounds(1170,40,150,50);
        deleteButton.setFont(new java.awt.Font("楷体",1,18));
        deleteButton.setForeground(Color.red);

        jScrollPane.setBounds(15,100,1310,700);


        add(searchCarNumber);
        add(searchCarColor);
        add(searchCarInfo);
        add(refreshButton);
        add(searchButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);

        Vector<String> carTHVector = new Vector<String>();
        carTHVector.add("编号");
        carTHVector.add("车牌号");
        carTHVector.add("品牌");
        carTHVector.add("型号");
        carTHVector.add("颜色");
        carTHVector.add("租金/（每天）");
        carTHVector.add("状态");
        carTHVector.add("备注");

        Vector<Vector<String>> carDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select car.car_id, car.car_number, brand.brand_name, model.model_name, model.model_color, model.model_rent, state.state_name, car.car_info from car, model, brand, state where car.car_model = model.model_id and model.model_brand = brand.brand_id and car.car_state = state.state_id  and car.car_recycle_bin = 1";

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

                carDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(carDataVector, carTHVector);
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

        searchCarNumber.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCarNumber.getText().equals("车牌号关键字") == true) {
                    searchCarNumber.setText("");
                    searchCarNumber.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchCarNumber.getText();
                if (temp.equals("")) {
                    searchCarNumber.setText("车牌号关键字");
                    searchCarNumber.setForeground(Color.gray);
                }
            }
        });

        searchCarColor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCarColor.getText().equals("颜色关键字") == true) {
                    searchCarColor.setText("");
                    searchCarColor.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchCarColor.getText();
                if (temp.equals("")) {
                    searchCarColor.setText("颜色关键字");
                    searchCarColor.setForeground(Color.gray);
                }
            }
        });

        searchCarInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCarInfo.getText().equals("备注关键字") == true) {
                    searchCarInfo.setText("");
                    searchCarInfo.setForeground(Color.magenta);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchCarInfo.getText();
                if (temp.equals("")) {
                    searchCarInfo.setText("备注关键字");
                    searchCarInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();
                Main.main.add(new CarRecycleBinList());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carNumber = searchCarNumber.getText();
                String carColor = searchCarColor.getText();
                String carInfo = searchCarInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select car.car_id, car.car_number, brand.brand_name, model.model_name, model.model_color, model.model_rent, state.state_name, car.car_info from car, model, brand, state where car.car_model = model.model_id and model.model_brand = brand.brand_id and car.car_state = state.state_id  and car.car_recycle_bin = 1 ");

                List list = new ArrayList();

                if (carNumber.trim().length() > 0 && carNumber.equals("车牌号关键字") == false) {
                    stringBuffer.append("and car.car_number like ? ");
                    list.add("%" + carNumber + "%");
                }

                if (carColor.trim().length() > 0 && carColor.equals("颜色关键字") == false) {
                    stringBuffer.append("and car.car_color like ? ");
                    list.add("%" + carColor + "%");
                }

                if (carInfo.trim().length() > 0 && carInfo.equals("备注关键字") == false) {
                    stringBuffer.append("and car.car_info like ? ");
                    list.add("%" + carInfo + "%");
                }

                try {
                    PreparedStatement ps1 = connection1.prepareStatement(stringBuffer.toString());

                    for (int i = 0; i < list.size(); i++) {
                        ps1.setObject(i + 1, list.get(i));
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
                        vector.add(rs1.getString(7));
                        vector.add(rs1.getString(8));

                        carDataVector.add(vector);
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
                    JOptionPane.showMessageDialog(null, "请先选中要移出回收站的车辆！");
                    return;
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认要还原所选车辆？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String carID = (String)table.getValueAt(row,0);
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update car set car_recycle_bin = 0 where car_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "车辆还原成功");
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要彻底删除的车辆");
                    return;
                } else {
                    String carID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选车辆彻底删除？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "delete from car where car_recycle_bin = 1 and car_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                JOptionPane.showMessageDialog(null, "车辆彻底删除成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                            } else {
                                JOptionPane.showMessageDialog(null, "车辆彻底删除失败！");
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
