package view.state;

import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddState extends JPanel {
    private JLabel stateNameLabel = new JLabel("状态名称");
    private JTextField stateNameField = new JTextField();

    private JLabel stateInfoLabel = new JLabel("状态名称");
    private JTextArea stateInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddState() {
        setSize(1350,800);
        setLayout(null);

        stateNameLabel.setBounds(80,40,80,30);
        stateNameField.setBounds(180,40,600,30);
        stateInfoLabel.setBounds(80,100,80,30);
        stateInfoArea.setBounds(180,100,600,100);

        resetButton.setBounds(500,250,120,30);
        addButton.setBounds(650,250,120,30);

        add(stateNameLabel);
        add(stateNameField);
        add(stateInfoLabel);
        add(stateInfoArea);
        add(resetButton);
        add(addButton);
        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新状态（状态编号自动加1）？",JOptionPane.YES_NO_OPTION);
                if (m == 0) {
                    String stateName = stateNameField.getText();
                    String stateInfo = stateInfoArea.getText();

                    Connection connection = DButil.getConnection();
                    String sql = "insert into state(state_name, state_info, state_recycle_bin) values(?, ?, ?)";
                    String sql1 = "select * from state where state_recycle_bin = 0";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, stateName);
                        ps.setObject(2, stateInfo);
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
