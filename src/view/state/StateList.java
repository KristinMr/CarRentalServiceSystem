package view.state;

import util.DButil;

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

public class StateList extends JPanel {
    private JTextField searchStateID = new JTextField("编号关键字");
    private JTextField searchStateName = new JTextField("名称关键字");
    private JTextField searchStateInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchStateButton = new JButton("查询");

    private JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JLabel stateIDLabel = new JLabel("状态编号");
    private JTextField stateIDField = new JTextField();

    private JLabel stateNameLabel = new JLabel("状态名称");
    private JTextField stateNameField = new JTextField();

    private JLabel stateTypeLabel = new JLabel("状态类型");
    private JRadioButton stateCarButton = new JRadioButton("车辆");
    private JRadioButton stateOrderButton = new JRadioButton("订单");

    private JLabel stateInfoLabel = new JLabel("状态介绍");
    private JTextArea stateInfoArea = new JTextArea();

    private JButton clearButton = new JButton("清空");
    private JButton resetButton = new JButton("重置");

    private JButton addStateButton = new JButton("新增状态");

    private JButton editButton = new JButton("修改状态信息");

    private JButton deleteButton = new JButton("删除所选状态");

    public StateList() {
//        setTitle("状态列表");
        setSize(1350,800);
//        setLocationRelativeTo(null);
        setLayout(null);


        searchStateID.setForeground(Color.gray);
        searchStateName.setForeground(Color.gray);
        searchStateInfo.setForeground(Color.gray);

        searchStateID.setBounds(80,40,150,30);
        searchStateName.setBounds(250,40,200,30);
        searchStateInfo.setBounds(470,40,300,30);
        refreshSearchButton.setBounds(1130,40,80,30);
        searchStateButton.setBounds(1230,40,80,30);
        pane.setBounds(80,100,1230,400);

        stateIDLabel.setBounds(80,550,80,30);
        stateIDField.setBounds(180,550,250,30);
        stateNameLabel.setBounds(530,550,80,30);
        stateNameField.setBounds(630,550,250,30);
        stateTypeLabel.setBounds(980,550,80,30);
        stateCarButton.setBounds(1080,550,100,30);
        stateOrderButton.setBounds(1180,550,100,30);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(stateCarButton);
        buttonGroup.add(stateOrderButton);
        stateInfoLabel.setBounds(80,610,80,30);
        stateInfoArea.setBounds(180,610,1130,100);
        clearButton.setBounds(80,760,80,30);
        resetButton.setBounds(180,760,80,30);
//        addStateButton.setBounds(965,640,120,30);
        editButton.setBounds(1005,760,120,30);
        deleteButton.setBounds(1145,760,120,30);

        Vector<String> thVector = new Vector<String>();
        thVector.add("编号");
        thVector.add("名称");
        thVector.add("类型");
        thVector.add("介绍");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        Connection connection = DButil.getConnection();

        String sql = "select * from state where state_recycle_bin = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
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

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        stateIDField.setEditable(false);

        add(searchStateID);
        add(searchStateName);
        add(searchStateInfo);
        add(refreshSearchButton);
        add(searchStateButton);
        add(pane);
        add(stateIDLabel);
        add(stateIDField);
        add(stateNameLabel);
        add(stateNameField);
        add(stateTypeLabel);
        add(stateCarButton);
        add(stateOrderButton);
        add(stateInfoLabel);
        add(stateInfoArea);
        add(clearButton);
        add(resetButton);
        add(addStateButton);
        add(editButton);
        add(deleteButton);

        searchStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stateID = searchStateID.getText();
                String stateName = searchStateName.getText();
                String stateInfo = searchStateInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from state where state_recycle_bin = 0 ");

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
                        dataVector.add(vector);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection1);
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String stateID = (String) table.getValueAt(row, 0);
                String stateName = (String) table.getValueAt(row, 1);
                String stateType = (String) table.getValueAt(row, 2);
                String stateInfo = (String) table.getValueAt(row, 3);
                stateIDField.setText(stateID);
                stateNameField.setText(stateName);
                if (stateType == "车辆") {
                    stateCarButton.setSelected(true);
                } else {
                    stateOrderButton.setSelected(true);
                }
                stateInfoArea.setText(stateInfo);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateNameField.setText("");
                stateCarButton.setSelected(false);
                stateOrderButton.setSelected(false);
                stateInfoArea.setText("");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                stateNameField.setText((String) table.getValueAt(table.getSelectedRow(), 1));
                stateInfoArea.setText((String)table.getValueAt(table.getSelectedRow(), 2));
                String stateType = (String) table.getValueAt(row, 2);
                if (stateType.equals("车辆")) {
                    stateCarButton.setSelected(true);
                } else {
                    stateOrderButton.setSelected(true);
                }
            }
        });

        addStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stateName = stateNameField.getText();
                int stateType;
                if (stateCarButton.isSelected()) {
                    stateType = 1;
                } else {
                    stateType = 2;
                }
                String stateInfo = stateInfoArea.getText();

                Connection connection = DButil.getConnection();
                String sql = "insert into state(state_name, state_type, state_info, state_recycle_bin) values(?, ?, ?, ?)";
                String sql1 = "select * from state where state_recycle_bin = 0";
                try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, stateName);
                    ps.setObject(2, stateType);
                    ps.setObject(3, stateInfo);
                    ps.setObject(4, 0);

                    int n = ps.executeUpdate();

                    if (n>0) {
                        JOptionPane.showMessageDialog(null, "新增成功！");

                        PreparedStatement ps1 = connection.prepareStatement(sql1);
                        ResultSet rs = ps1.executeQuery();
                        defaultTableModel.getDataVector().clear();
                        defaultTableModel.fireTableDataChanged();
                            while (rs.next()){
                                Vector<String> vector = new Vector<String>();
                                vector.add(rs.getString(1));
                                vector.add(rs.getString(2));
                                vector.add(rs.getString(3));
                                vector.add(rs.getString(4));
                                dataVector.add(vector);
                            }
                    } else {
                        JOptionPane.showMessageDialog(null, "新增失败！");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要修改的状态！");
                } else {
                    String stateID = stateIDField.getText();
                    String stateName = stateNameField.getText();
                    int stateType;
                    if (stateCarButton.isSelected()) {
                        stateType = 1;
                    } else {
                        stateType = 2;
                    }
                    String stateInfo = stateInfoArea.getText();

                    Connection connection1 = DButil.getConnection();
                    String sql = "update state set state_name = ?, state_type = ?, state_info = ? where state_id = ?";

                    try {
                        PreparedStatement ps = connection1.prepareStatement(sql);
                        ps.setObject(1, stateName);
                        ps.setObject(1, stateType);
                        ps.setObject(2, stateInfo);
                        ps.setObject(3,stateID);
                        int n = ps.executeUpdate();

                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "状态修改成功！");
                            ((DefaultTableModel)table.getModel()).setValueAt(stateName, row,1);
                            ((DefaultTableModel)table.getModel()).setValueAt(stateType, row,2);
                            ((DefaultTableModel)table.getModel()).setValueAt(stateInfo, row,3);
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
                    JOptionPane.showMessageDialog(null, "请先选中要删除的状态！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String stateID = stateIDField.getText();

                        Connection connection1 = DButil.getConnection();
                        String sql = "update state set state_recycle_bin = 1 where state_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1, stateID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "删除成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                stateIDField.setText("");
                                stateNameField.setText("");
                                stateCarButton.setSelected(false);
                                stateOrderButton.setSelected(false);
                                stateInfoArea.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "删除失败！");
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

    public static void main(String[] args) {
        new StateList();
    }
}
