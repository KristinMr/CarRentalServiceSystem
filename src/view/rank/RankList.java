package view.rank;

import jdk.nashorn.internal.scripts.JO;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
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

public class RankList extends JPanel {

    private JTextField searchRankID = new JTextField("编号关键字");
    private JTextField searchRankName = new JTextField("名称关键字");
    private JTextField searchRankInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchRankButton = new JButton("查询");

    private JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JLabel rankIDLabel = new JLabel("权限编号");
    private JTextField rankIDField = new JTextField();

    private JLabel rankNameLabel = new JLabel("权限名称");
    private JTextField rankNameField = new JTextField();

    private JLabel rankInfoLabel = new JLabel("权限介绍");
    private JTextArea rankInfoArea = new JTextArea();

    private JButton clearButton = new JButton("清空");
    private JButton resetButton = new JButton("重置");

//    private JButton addRankButton = new JButton("新增权限");

    private JButton editButton = new JButton("修改权限信息");

    private JButton deleteButton = new JButton("删除所选权限");

    public RankList() {
//        setTitle("权限列表");
        setSize(1350,800);
//        setLocationRelativeTo(null);
        setLayout(null);


        searchRankID.setForeground(Color.gray);
        searchRankName.setForeground(Color.gray);
        searchRankInfo.setForeground(Color.gray);

        searchRankID.setBounds(80,40,150,30);
        searchRankName.setBounds(250,40,200,30);
        searchRankInfo.setBounds(470,40,300,30);
        refreshSearchButton.setBounds(1130,40,80,30);
        searchRankButton.setBounds(1230,40,80,30);
        pane.setBounds(80,100,1230,400);

        rankIDLabel.setBounds(80,550,80,30);
        rankIDField.setBounds(180,550,480,30);
        rankNameLabel.setBounds(730,550,80,30);
        rankNameField.setBounds(830,550,480,30);
        rankInfoLabel.setBounds(80,610,80,30);
        rankInfoArea.setBounds(180,610,1130,100);
        clearButton.setBounds(80,760,80,30);
        resetButton.setBounds(180,760,80,30);
//        addRankButton.setBounds(965,640,120,30);
        editButton.setBounds(1005,760,120,30);
        deleteButton.setBounds(1145,760,120,30);

        add(searchRankID);
        add(searchRankName);
        add(searchRankInfo);
        add(refreshSearchButton);
        add(searchRankButton);
        add(pane);
        add(rankIDLabel);
        add(rankIDField);
        add(rankNameLabel);
        add(rankNameField);
        add(rankInfoLabel);
        add(rankInfoArea);
        add(clearButton);
        add(resetButton);
//        add(addRankButton);
        add(editButton);
        add(deleteButton);

        Vector<String> thVector = new Vector<String>();
        thVector.add("编号");
        thVector.add("名称");
        thVector.add("介绍");
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

        Connection connection = DButil.getConnection();

        String sql = "select * from rank where rank_recycle_bin = 0";

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

        rankIDField.setEditable(false);

        searchRankButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rankID = searchRankID.getText();
                String rankName = searchRankName.getText();
                String rankInfo = searchRankInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from rank where rank_recycle_bin = 0 ");

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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                String rankID = (String) table.getValueAt(row, 0);
                String rankName = (String) table.getValueAt(row, 1);
                String rankInfo = (String) table.getValueAt(row, 2);
                rankIDField.setText(rankID);
                rankNameField.setText(rankName);
                rankInfoArea.setText(rankInfo);

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankNameField.setText("");
                rankInfoArea.setText("");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankNameField.setText((String) table.getValueAt(table.getSelectedRow(), 1));
                rankInfoArea.setText((String)table.getValueAt(table.getSelectedRow(), 2));
            }
        });

//        addRankButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新权限（权限编号自动加1）？",JOptionPane.YES_NO_OPTION);
//                if (m == 0) {
//                    String rankName = rankNameField.getText();
//                    String rankInfo = rankInfoArea.getText();
//
//                    Connection connection = DButil.getConnection();
//                    String sql = "insert into rank(rank_name, rank_info, rank_recycle_bin) values(?, ?, ?)";
//                    String sql1 = "select * from rank where rank_recycle_bin = 0";
//                    try {
//                        PreparedStatement ps = connection.prepareStatement(sql);
//                        ps.setObject(1, rankName);
//                        ps.setObject(2, rankInfo);
//                        ps.setObject(3, 0);
//
//                        int n = ps.executeUpdate();
//
//                        if (n>0) {
//                            JOptionPane.showMessageDialog(null, "新增成功！");
//
//                            PreparedStatement ps1 = connection.prepareStatement(sql1);
//                            ResultSet rs = ps.executeQuery();
//                            defaultTableModel.getDataVector().clear();
//                            defaultTableModel.fireTableDataChanged();
//                            while (rs.next()){
//                                Vector<String> vector = new Vector<String>();
//                                vector.add(rs.getString(1));
//                                vector.add(rs.getString(2));
//                                vector.add(rs.getString(3));
//                                dataVector.add(vector);
//                            }
//                        } else {
//                            JOptionPane.showMessageDialog(null, "新增失败！");
//                        }
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    } finally {
//                        DButil.releaseConnection(connection);
//                    }
//                }
//
//            }
//        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "请先选中要修改的权限！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认修改权限信息？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String rankID = rankIDField.getText();
                        String rankName = rankNameField.getText();
                        String rankInfo = rankInfoArea.getText();

                        Connection connection1 = DButil.getConnection();
                        String sql = "update rank set rank_name = ?, rank_info = ? where rank_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1, rankName);
                            ps.setObject(2, rankInfo);
                            ps.setObject(3,rankID);
                            int n = ps.executeUpdate();

                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "权限修改成功！");
                                ((DefaultTableModel)table.getModel()).setValueAt(rankName, row,1);
                                ((DefaultTableModel)table.getModel()).setValueAt(rankInfo, row,2);
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
                    JOptionPane.showMessageDialog(null, "请先选中要删除的权限！");
                } else {
                    int m = JOptionPane.showConfirmDialog(null, "确认","确认移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        String rankID = rankIDField.getText();

                        Connection connection1 = DButil.getConnection();
                        String sql = "update rank set rank_recycle_bin = 1 where rank_id = ?";

                        try {
                            PreparedStatement ps = connection1.prepareStatement(sql);
                            ps.setObject(1, rankID);
                            int n = ps.executeUpdate();
                            if (n > 0) {
                                JOptionPane.showMessageDialog(null, "删除成功！");
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                rankIDField.setText("");
                                rankNameField.setText("");
                                rankInfoArea.setText("");
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
        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.osLookAndFeelDecorated;
            UIManager.put("RootPane.setupButtonVisible", false);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {

        }
        new RankList();
    }
}
