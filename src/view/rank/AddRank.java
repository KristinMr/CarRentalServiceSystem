package view.rank;

import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddRank extends JDialog {

    private JLabel rankNameLabel = new JLabel("权限名称");
    private JTextField rankNameField = new JTextField();

    private JLabel rankInfoLabel = new JLabel("权限介绍");
    private JTextArea rankInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddRank(){
        setTitle("新增权限");
        setSize(400,350);
        setLocationRelativeTo(null);
        setLayout(null);

        rankNameLabel.setBounds(50,30,70,30);
        rankNameField.setBounds(150,30,170,30);

        rankInfoLabel.setBounds(50,100,70,30);
        rankInfoArea.setBounds(150,100,170,100);

        resetButton.setBounds(150,230,60,30);
        addButton.setBounds(250,230,60,30);

        add(rankNameLabel);
        add(rankNameField);

        add(rankInfoLabel);
        add(rankInfoArea);

        add(resetButton);
        add(addButton);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rankNameField.setText("");
                rankInfoArea.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rankName = rankNameField.getText();
                String rankInfo = rankInfoArea.getText();

                Connection connection = DButil.getConnection();
                String sql = "insert into rank(rank_name, rank_info, rank_recycle_bin) values(?, ?, ?)";
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
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddRank();
    }
}
