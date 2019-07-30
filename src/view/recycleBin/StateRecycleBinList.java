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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class StateRecycleBinList extends JPanel {
    private JTextField searchStateID = new JTextField("编号关键字");
    private JTextField searchStateName = new JTextField("名称关键字");
    private JTextField searchStateInfo = new JTextField("介绍关键字");
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

    public StateRecycleBinList() {
        setSize(1350,800);
        setLayout(null);


        searchStateID.setForeground(Color.gray);
        searchStateName.setForeground(Color.gray);
        searchStateInfo.setForeground(Color.gray);

        searchStateID.setBounds(15,40,150,30);
        searchStateName.setBounds(185,40,150,30);
        searchStateInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);
        searchButton.setForeground(Color.blue);

        editButton.setBounds(1000,40,150,40);
        editButton.setForeground(Color.green);
        deleteButton.setBounds(1170,40,150,50);
        deleteButton.setFont(new java.awt.Font("楷体",1,18));
        deleteButton.setForeground(Color.red);

        pane.setBounds(15,100,1310,700);

        Vector<String> thVector = new Vector<String>();
        thVector.add("编号");
        thVector.add("名称");
        thVector.add("类型");
        thVector.add("介绍");
        Vector<Vector<String>> stateDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection connection = DButil.getConnection();

        String sql = "select state_id, state_name, state_type, state_info from state where state_recycle_bin = 1";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                if (rs.getString(3).equals("1")) {
                    vector.add("车辆");
                } else {
                    vector.add("订单");
                }
                vector.add(rs.getString(4));
                stateDataVector.add(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(stateDataVector, thVector);
        table.setModel(defaultTableModel);
        pane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        add(searchStateID);
        add(searchStateName);
        add(searchStateInfo);
        add(refreshButton);
        add(searchButton);
        add(editButton);
        add(deleteButton);
        add(pane);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String info = (String)table.getValueAt(table.getSelectedRow(), 3);
                    new ShowInfo(info);
                };
            }
        });

        searchStateID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchStateID.getText().equals("编号关键字") == true) {
                    searchStateID.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchStateID.getText();
                if (temp.equals("")) {
                    searchStateID.setText("编号关键字");
                    searchStateID.setForeground(Color.gray);
                }
            }
        });

        searchStateName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchStateName.getText().equals("名称关键字") == true) {
                    searchStateName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchStateName.getText();
                if (temp.equals("")) {
                    searchStateName.setText("名称关键字");
                    searchStateName.setForeground(Color.gray);
                }
            }
        });

        searchStateInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchStateInfo.getText().equals("介绍关键字") == true) {
                    searchStateInfo.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchStateInfo.getText();
                if (temp.equals("")) {
                    searchStateInfo.setText("介绍关键字");
                    searchStateInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();

                Main.main.add(new StateRecycleBinList());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stateID = searchStateID.getText();
                String stateName = searchStateName.getText();
                String stateInfo = searchStateInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from state where state_recycle_bin = 1 ");

                List list = new ArrayList();

                if (stateID.trim().length() > 0 && stateID.equals("编号关键字") == false) {
                    stringBuffer.append("and state_id like ? ");
                    list.add("%" + stateID + "%");
                }

                if (stateName.trim().length() > 0 && stateName.equals("名称关键字") == false) {
                    stringBuffer.append("and state_name like ? ");
                    list.add("%" + stateName + "%");
                }

                if (stateInfo.trim().length() > 0 && stateInfo.equals("介绍关键字") == false) {
                    stringBuffer.append("and state_info like ? ");
                    list.add("%" + stateInfo + "%");
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
                        stateDataVector.add(vector);
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
                    JOptionPane.showMessageDialog(null, "请先选中要移出回收站的状态！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认要还原所选状态？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String stateID = (String)table.getValueAt(row, 0);
                        Connection connection1 = DButil.getConnection();
                        String sql = "update state set state_recycle_bin = 0 where state_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1,stateID);
                            int n = ps.executeUpdate();

                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "状态还原成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
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
                    JOptionPane.showMessageDialog(null, "请先选中要彻底删除的状态！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选状态彻底删除？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String stateID = (String)table.getValueAt(row, 0);

                        Connection connection1 = DButil.getConnection();
                        String sql = "delete from state where state_recycle_bin = 1 and state_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1, stateID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "状态彻底删除成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                            } else {
                                JOptionPane.showMessageDialog(null, "状态彻底删除失败！");
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
