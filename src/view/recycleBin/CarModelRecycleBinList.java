package view.recycleBin;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CarModelRecycleBinList extends JPanel {
    private JTextField searchCarTypeBrand = new JTextField("品牌关键字");
    private JTextField searchCarTypeColor = new JTextField("颜色关键字");
    private JTextField searchCarTypeInfo = new JTextField("备注关键字");
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


    public CarModelRecycleBinList() {
        setSize(1350,800);
        setLayout(null);

        searchCarTypeBrand.setForeground(Color.gray);
        searchCarTypeColor.setForeground(Color.gray);
        searchCarTypeInfo.setForeground(Color.gray);

        searchCarTypeBrand.setBounds(15,40,150,30);
        searchCarTypeColor.setBounds(185,40,150,30);
        searchCarTypeInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);
        searchButton.setForeground(Color.blue);

        editButton.setBounds(1000,40,150,40);
        editButton.setForeground(Color.green);
        deleteButton.setBounds(1170,40,150,50);
        deleteButton.setFont(new java.awt.Font("楷体",1,18));
        deleteButton.setForeground(Color.red);

        jScrollPane.setBounds(15,100,1310,700);


        add(searchCarTypeBrand);
        add(searchCarTypeColor);
        add(searchCarTypeInfo);
        add(refreshButton);
        add(searchButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);

        Vector<String> carTypeTHVector = new Vector<String>();
        carTypeTHVector.add("车型编号");
        carTypeTHVector.add("品牌");
        carTypeTHVector.add("型号");
        carTypeTHVector.add("颜色");
        carTypeTHVector.add("租金/（每天）");
        carTypeTHVector.add("备注");

        Vector<Vector<String>> carTypeDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select model.model_id, brand.brand_name, model.model_name, model.model_color, model.model_rent, model.model_Info from model, brand where model.model_brand = brand.brand_id and model.model_recycle_bin = 1";

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
                carTypeDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(carTypeDataVector, carTypeTHVector);
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
                    String info = (String)table.getValueAt(table.getSelectedRow(), 5);
                    new ShowInfo(info);
                };
            }
        });

        searchCarTypeBrand.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCarTypeBrand.getText().equals("品牌关键字") == true) {
                    searchCarTypeBrand.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchCarTypeBrand.getText();
                if (temp.equals("")) {
                    searchCarTypeBrand.setText("品牌关键字");
                    searchCarTypeBrand.setForeground(Color.gray);
                }
            }
        });

        searchCarTypeColor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCarTypeColor.getText().equals("颜色关键字") == true) {
                    searchCarTypeColor.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchCarTypeColor.getText();
                if (temp.equals("")) {
                    searchCarTypeColor.setText("颜色关键字");
                    searchCarTypeColor.setForeground(Color.gray);
                }
            }
        });

        searchCarTypeInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchCarTypeInfo.getText().equals("备注关键字") == true) {
                    searchCarTypeInfo.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchCarTypeInfo.getText();
                if (temp.equals("")) {
                    searchCarTypeInfo.setText("备注关键字");
                    searchCarTypeInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();

                Main.main.add(new CarModelRecycleBinList());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carTypeBrand = searchCarTypeBrand.getText();
                String carTypeColor = searchCarTypeColor.getText();
                String carTypeInfo = searchCarTypeInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select model.model_id, brand.brand_name, model.model_name, model.model_color, model.model_rent, brand.brand_info, model.model_Info from model, brand where model.model_brand = brand.brand_id and model.model_recycle_bin = 1 ");

                List list = new ArrayList();

                if (carTypeBrand.trim().length() > 0 && carTypeBrand.equals("品牌关键字") == false) {
                    stringBuffer.append("and brand.brand_name like ? ");
                    list.add("%" + carTypeBrand + "%");
                }

                if (carTypeColor.trim().length() > 0 && carTypeColor.equals("颜色关键字") == false) {
                    stringBuffer.append("and model.model_color like ? ");
                    list.add("%" + carTypeColor + "%");
                }

                if (carTypeInfo.trim().length() > 0 && carTypeInfo.equals("备注关键字") == false) {
                    stringBuffer.append("and model.model_info like ? ");
                    list.add("%" + carTypeInfo + "%");
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
                        Vector<String> vector = new Vector<String>();
                        vector.add(rs.getString(1));
                        vector.add(rs.getString(2));
                        vector.add(rs.getString(3));
                        vector.add(rs.getString(4));
                        vector.add(rs.getString(5));
                        vector.add(rs.getString(6));
                        carTypeDataVector.add(vector);
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
                    JOptionPane.showMessageDialog(null, "请先选中要移出回收站的车型！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认要还原所选车型？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String carTypeID = (String)table.getValueAt(row,0);
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update model set model_recycle_bin = 0 where model_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carTypeID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "车型还原成功！");
                            } else {
                                JOptionPane.showMessageDialog(null, "车型还原失败！");
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
                    JOptionPane.showMessageDialog(null, "请先选中要彻底删除的车型");
                    return;
                } else {
                    String carTypeID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选车型彻底删除？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "delete from model where model_recycle_bin = 1 and model_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carTypeID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                JOptionPane.showMessageDialog(null, "车型彻底删除成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                            } else {
                                JOptionPane.showMessageDialog(null, "车型彻底删除失败！");
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
