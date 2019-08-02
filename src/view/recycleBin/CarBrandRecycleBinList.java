package view.recycleBin;

import util.DButil;
import view.Main;
import view.carType.AddBrand;
import view.pubilc.ShowInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CarBrandRecycleBinList extends JPanel {
    private JTextField searchBrandID = new JTextField("编号关键字");
    private JTextField searchBrandName = new JTextField("名称关键字");
    private JTextField searchBrandInfo = new JTextField("备注关键字");
    private JButton refreshButton = new JButton("刷新");
    private JButton searchButton = new JButton("查询");

    private JButton editButton = new JButton("移出回收站");
    private JButton deleteButton = new JButton("彻底删除");

    private JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public CarBrandRecycleBinList() {
        setSize(1350,800);
        setLayout(null);


        searchBrandID.setForeground(Color.gray);
        searchBrandName.setForeground(Color.gray);
        searchBrandInfo.setForeground(Color.gray);

        searchBrandID.setBounds(15,40,150,30);
        searchBrandName.setBounds(185,40,150,30);
        searchBrandInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);
        searchButton.setForeground(Color.blue);

        editButton.setBounds(1000,40,150,40);
        editButton.setForeground(Color.green);
        deleteButton.setBounds(1170,40,150,50);
        deleteButton.setFont(new java.awt.Font("楷体",1,18));
        deleteButton.setForeground(Color.red);


        pane.setBounds(15,100,1310,700);


        add(searchBrandID);
        add(searchBrandName);
        add(searchBrandInfo);
        add(refreshButton);
        add(searchButton);
        add(editButton);
        add(deleteButton);
        add(pane);

        Vector<String> thVector = new Vector<String>();
        thVector.add("编号");
        thVector.add("名称");
        thVector.add("介绍");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String info = (String)table.getValueAt(table.getSelectedRow(), 2);
                    new ShowInfo(info);
                };
            }
        });

        searchBrandID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBrandID.getText().equals("编号关键字") == true) {
                    searchBrandID.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchBrandID.getText();
                if (temp.equals("")) {
                    searchBrandID.setText("编号关键字");
                    searchBrandID.setForeground(Color.gray);
                }
            }
        });

        searchBrandName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBrandName.getText().equals("名称关键字") == true) {
                    searchBrandName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchBrandName.getText();
                if (temp.equals("")) {
                    searchBrandName.setText("名称关键字");
                    searchBrandName.setForeground(Color.gray);
                }
            }
        });

        searchBrandInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBrandInfo.getText().equals("备注关键字") == true) {
                    searchBrandInfo.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchBrandInfo.getText();
                if (temp.equals("")) {
                    searchBrandInfo.setText("备注关键字");
                    searchBrandInfo.setForeground(Color.gray);
                }
            }
        });

        Connection connection = DButil.getConnection();

        String sql = "select * from brand where brand_recycle_bin = 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                dataVector.add(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
        table.setModel(defaultTableModel);
        pane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);

        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(450);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();

                Main.main.add(new CarBrandRecycleBinList());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String brandID = searchBrandID.getText();
                String brandName = searchBrandName.getText();
                String brandInfo = searchBrandInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from brand where brand_recycle_bin = 1 ");

                List list = new ArrayList();

                if (brandID.trim().length() > 0 && brandID.equals("编号关键字") == false) {
                    stringBuffer.append("and brand_id like ? ");
                    list.add("%" + brandID + "%");
                }

                if (brandName.trim().length() > 0 && brandName.equals("名称关键字") == false) {
                    stringBuffer.append("and brand_name like ? ");
                    list.add("%" + brandName + "%");
                }

                if (brandInfo.trim().length() > 0 && brandInfo.equals("备注关键字") == false) {
                    stringBuffer.append("and brand_info like ? ");
                    list.add("%" + brandInfo + "%");
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
                        dataVector.add(vector);
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
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要移出回收站的车辆品牌！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认还原所选车辆品牌？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String brandID = (String)table.getValueAt(row, 0);
                        Connection connection1 = DButil.getConnection();
                        String sql = "update brand set brand_recycle_bin = 0 where brand_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1,brandID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "车辆品牌还原成功！");
                            } else {
                                JOptionPane.showMessageDialog(null, "车辆品牌还原失败！");
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
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要彻底删除的品牌！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认彻底删除？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String brandID = (String)table.getValueAt(row, 0);
                        Connection connection1 = DButil.getConnection();
                        String sql = "delete from brand where brand_recycle_bin = 1 and brand_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1, brandID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "车辆品牌彻底删除成功！");
                            } else {
                                JOptionPane.showMessageDialog(null, "车辆品牌彻底删除失败！");
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
