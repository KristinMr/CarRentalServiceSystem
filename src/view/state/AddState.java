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

    private JLabel stateTypeLabel = new JLabel("状态类型");
    private JRadioButton stateCarButton = new JRadioButton("车辆状态");
    private JRadioButton stateOrderButton = new JRadioButton("订单状态");

    private JLabel stateInfoLabel = new JLabel("状态备注");
    private JTextArea stateInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddState() {
        setSize(1350,800);
        setLayout(null);

        stateNameLabel.setBounds(80,40,80,30);
        stateNameField.setBounds(180,40,600,30);

        stateTypeLabel.setBounds(80,100,80,30);
        stateCarButton.setBounds(180,100,200,30);
        stateOrderButton.setBounds(400,100,200,30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(stateCarButton);
        buttonGroup.add(stateOrderButton);
        stateCarButton.setSelected(true);

        stateInfoLabel.setBounds(80,160,80,30);
        stateInfoArea.setBounds(180,160,600,100);

        resetButton.setBounds(500,310,120,30);
        addButton.setBounds(650,310,120,30);

        add(stateNameLabel);
        add(stateNameField);
        add(stateTypeLabel);
        add(stateCarButton);
        add(stateOrderButton);
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
                    int stateType = 1;
                    if (stateOrderButton.isSelected()) {
                        stateType = 2;
                    }
                    String stateInfo = stateInfoArea.getText();

                    Connection connection = DButil.getConnection();
                    String sql = "insert into state(state_name, state_type, state_info, state_recycle_bin) values(?, ?, ?, 0)";
                    String sql1 = "select * from state where state_recycle_bin = 0";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, stateName);
                        ps.setObject(2, stateType);
                        ps.setObject(3, stateInfo);

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
