package view.rank;

import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class AddRank extends JPanel {
    private JLabel rankNameLabel = new JLabel("权限名称");
    private JTextField rankNameField = new JTextField();

    private JLabel rankInfoLabel = new JLabel("权限名称");
    private JTextArea rankInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddRank() {
        setSize(1350,800);
        setLayout(null);

        rankNameLabel.setBounds(80,40,80,30);
        rankNameField.setBounds(180,40,600,30);
        rankInfoLabel.setBounds(80,100,80,30);
        rankInfoArea.setBounds(180,100,600,100);

        resetButton.setBounds(500,250,120,30);
        addButton.setBounds(650,250,120,30);

        add(rankNameLabel);
        add(rankNameField);
        add(rankInfoLabel);
        add(rankInfoArea);
        add(resetButton);
        add(addButton);
        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新权限（权限编号自动加1）？",JOptionPane.YES_NO_OPTION);
                if (m == 0) {
                    String rankName = rankNameField.getText();
                    String rankInfo = rankInfoArea.getText();

                    Connection connection = DButil.getConnection();
                    String sql = "insert into rank(rank_name, rank_info, rank_recycle_bin) values(?, ?, ?)";
                    String sql1 = "select * from rank where rank_recycle_bin = 0";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, rankName);
                        ps.setObject(2, rankInfo);
                        ps.setObject(3, 0);

                        int n = ps.executeUpdate();

                        if (n>0) {
                            JOptionPane.showMessageDialog(null, "新增成功！");
                        } else {
                            JOptionPane.showMessageDialog(null, "新增失败！");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        DButil.releaseConnection(connection);
                    }
                }
            }
        });
    }
}
