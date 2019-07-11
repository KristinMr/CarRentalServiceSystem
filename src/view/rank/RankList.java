package view.rank;

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

public class RankList extends JDialog {

    private JTextField searchRankID = new JTextField("编号关键字");
    private JTextField searchRankName = new JTextField("名称关键字");
    private JTextField searchRankInfo = new JTextField("介绍关键字");

    private JButton searchBotton = new JButton("查询");

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

    private JButton resetBotton = new JButton("重置");
    private JButton clearBotton = new JButton("清空");

    private JButton editBotton = new JButton("修改权限信息");

    private JButton deleteBotton = new JButton("删除所选权限");

    public RankList() {
        setTitle("权限列表");
        setSize(800,800);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);


        searchRankID.setForeground(Color.gray);
        searchRankName.setForeground(Color.gray);
        searchRankInfo.setForeground(Color.gray);

        searchRankID.setBounds(50,30,150,30);
        searchRankName.setBounds(220,30,150,30);
        searchRankInfo.setBounds(390,30,150,30);
        searchBotton.setBounds(600,30,150,30);
        pane.setBounds(50,100,700,300);

        rankIDLabel.setBounds(70,430,80,30);
        rankIDField.setBounds(170,430,170,30);
        rankNameLabel.setBounds(420,430,80,30);
        rankNameField.setBounds(520,430,170,30);
        rankInfoLabel.setBounds(70,490,80,30);
        rankInfoArea.setBounds(170,490,520,100);
        editBotton.setBounds(300,640,150,30);
        deleteBotton.setBounds(500,640,150,30);

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

        String sql = "select * from rank";

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

        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(400);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        add(searchRankID);
        add(searchRankName);
        add(searchRankInfo);
        add(searchBotton);
        add(pane);
        add(rankIDLabel);
        add(rankIDField);
        add(rankNameLabel);
        add(rankNameField);
        add(rankInfoLabel);
        add(rankInfoArea);
        add(editBotton);
        add(deleteBotton);

        searchBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rankID = searchRankID.getText();
                String rankName = searchRankName.getText();
                String rankInfo = searchRankInfo.getText();

                Connection connection1 = DButil.getConnection();
                StringBuffer stringBuffer = new StringBuffer("select * from rank where 1 = 1 ");

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
                        System.out.println(123456);
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

        setVisible(true);
    }

    public static void main(String[] args) {
        new RankList();
    }
}
