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

public class RankRecycleBinList extends JPanel {
    private JTextField searchRankID = new JTextField("编号关键字");
    private JTextField searchRankName = new JTextField("名称关键字");
    private JTextField searchRankInfo = new JTextField("介绍关键字");
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

    public RankRecycleBinList() {
        setSize(1350,800);
        setLayout(null);


        searchRankID.setForeground(Color.gray);
        searchRankName.setForeground(Color.gray);
        searchRankInfo.setForeground(Color.gray);

        searchRankID.setBounds(15,40,150,30);
        searchRankName.setBounds(185,40,150,30);
        searchRankInfo.setBounds(355,40,150,30);
        refreshButton.setBounds(720,40,80,30);
        searchButton.setBounds(820,40,80,30);
        searchButton.setForeground(Color.blue);

        editButton.setBounds(1000,40,150,40);
        editButton.setForeground(Color.green);
        deleteButton.setBounds(1170,40,150,50);
        deleteButton.setFont(new java.awt.Font("楷体",1,18));
        deleteButton.setForeground(Color.red);

        pane.setBounds(15,100,1310,700);


        add(searchRankID);
        add(searchRankName);
        add(searchRankInfo);
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

        Connection connection = DButil.getConnection();

        String sql = "select * from rank where rank_recycle_bin = 1";

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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String info = (String)table.getValueAt(table.getSelectedRow(), 2);
                    new ShowInfo(info);
                };
            }
        });

        searchRankID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchRankID.getText().equals("编号关键字") == true) {
                    searchRankID.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchRankID.getText();
                if (temp.equals("")) {
                    searchRankID.setText("编号关键字");
                    searchRankID.setForeground(Color.gray);
                }
            }
        });

        searchRankName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchRankName.getText().equals("名称关键字") == true) {
                    searchRankName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchRankName.getText();
                if (temp.equals("")) {
                    searchRankName.setText("名称关键字");
                    searchRankName.setForeground(Color.gray);
                }
            }
        });

        searchRankInfo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchRankInfo.getText().equals("介绍关键字") == true) {
                    searchRankInfo.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp = searchRankInfo.getText();
                if (temp.equals("")) {
                    searchRankInfo.setText("介绍关键字");
                    searchRankInfo.setForeground(Color.gray);
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.Main.main.removeAll();
                view.Main.main.repaint();
                view.Main.main.updateUI();
                Main.main.add(new RankRecycleBinList());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rankID = searchRankID.getText();
                String rankName = searchRankName.getText();
                String rankInfo = searchRankInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from rank where rank_recycle_bin = 1 ");

                List list = new ArrayList();

                if (rankID.trim().length() > 0 && rankID.equals("编号关键字") == false) {
                    stringBuffer.append("and rank_id like ? ");
                    list.add("%" + rankID + "%");
                }

                if (rankName.trim().length() > 0 && rankName.equals("名称关键字") == false) {
                    stringBuffer.append("and rank_name like ? ");
                    list.add("%" + rankName + "%");
                }

                if (rankInfo.trim().length() > 0 && rankInfo.equals("介绍关键字") == false) {
                    stringBuffer.append("and rank_info like ? ");
                    list.add("%" + rankInfo + "%");
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
                    JOptionPane.showMessageDialog(null, "请先选中要移出回收站的权限！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认要还原所选权限？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String rankID = (String) table.getValueAt(row, 0);
                        Connection connection1 = DButil.getConnection();
                        String sql = "update rank set rank_recycle_bin = 0 where rank_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1,rankID);
                            int n = ps.executeUpdate();

                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "权限还原成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                            } else {
                                JOptionPane.showMessageDialog(null, "权限还原失败！");
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
                    JOptionPane.showMessageDialog(null, "请先选中要彻底删除的权限！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选权限彻底删除？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String rankID = (String) table.getValueAt(row, 0);

                        Connection connection1 = DButil.getConnection();
                        String sql = "delete from rank where rank_recycle_bin = 1 and rank_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1, rankID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "权限彻底删除成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                            } else {
                                JOptionPane.showMessageDialog(null, "权限彻底删除失败！");
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
