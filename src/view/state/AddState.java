package view.state;

import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddState extends JDialog {
    private JLabel stateNameLabel = new JLabel("状态名称");
    private JTextField stateNameField = new JTextField();

    private JLabel stateInfoLabel = new JLabel("状态介绍");
    private JTextArea stateInfoArea = new JTextArea();

    private JButton clearButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddState(){
        setTitle("新增状态");
        setSize(400,350);
        setLocationRelativeTo(null);
        setLayout(null);

        stateNameLabel.setBounds(50,30,70,30);
        stateNameField.setBounds(150,30,170,30);

        stateInfoLabel.setBounds(50,100,70,30);
        stateInfoArea.setBounds(150,100,170,100);

        clearButton.setBounds(150,230,60,30);
        addButton.setBounds(250,230,60,30);

        add(stateNameLabel);
        add(stateNameField);

        add(stateInfoLabel);
        add(stateInfoArea);

        add(clearButton);
        add(addButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateNameField.setText("");
                stateInfoArea.setText("");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stateName = stateNameField.getText();
                String stateInfo = stateInfoArea.getText();

                Connection connection = DButil.getConnection();
                String sql = "insert into state(state_name, state_info, state_recycle_bin) values(?, ?, ?)";
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
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddState();
    }
}
