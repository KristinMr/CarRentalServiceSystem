package view.carType;

import util.DButil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddCarType extends JPanel {
    private JLabel typeBrandLabel = new JLabel("品牌");
    private JComboBox typeBrandBox = new JComboBox();

    private JButton carBrandListButton = new JButton("品牌列表");

    private JLabel typeModelLabel = new JLabel("型号名称");
    private JTextField typeModelField = new JTextField();

    private JLabel typeColorLabel = new JLabel("颜色");
    private JTextField typeColorField = new JTextField();

    private JLabel typeRentLabel = new JLabel("租金");
    private JTextField typeRentField = new JTextField();

    private JLabel typeInfoLabel = new JLabel("备注");
    private JTextArea typelInfoArea = new JTextArea();

    private JButton clearButton = new JButton("清空");
    private JButton addCarTypeButton = new JButton("确认添加车型");

    public AddCarType() {
        setSize(1350,800);
        setLayout(null);

        typeBrandLabel.setBounds(80,40,80,30);
        typeBrandBox.setBounds(180,40,600,30);
        carBrandListButton.setBounds(180,40,600,30);

        typeModelLabel.setBounds(80,100,80,30);
        typeModelField.setBounds(180,100,200,30);

        typeColorLabel.setBounds(80,100,80,30);
        typeColorField.setBounds(180,100,200,30);

        typeRentLabel.setBounds(80,100,80,30);
        typeRentField.setBounds(180,100,200,30);

        typeInfoLabel.setBounds(80,160,80,30);
        typelInfoArea.setBounds(180,160,600,100);

        clearButton.setBounds(500,310,120,30);
        addCarTypeButton.setBounds(650,310,120,30);

        add(typeBrandLabel);
        add(typeBrandBox);
        add(carBrandListButton);
        add(typeModelLabel);
        add(typeModelField);
        add(typeColorLabel);
        add(typeColorField);
        add(typeRentLabel);
        add(typeRentField);
        add(typeInfoLabel);
        add(typelInfoArea);
        add(clearButton);
        add(addCarTypeButton);
        setVisible(true);

        addCarTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新状态（状态编号自动加1）？",JOptionPane.YES_NO_OPTION);
//                if (m == 0) {
//                    String stateName = stateNameField.getText();
//                    int stateType = 1;
//                    if (stateOrderButton.isSelected()) {
//                        stateType = 2;
//                    }
//                    String stateInfo = stateInfoArea.getText();
//
//                    Connection connection = DButil.getConnection();
//                    String sql = "insert into state(state_name, state_type, state_info, state_recycle_bin) values(?, ?, ?, ?)";
//                    String sql1 = "select * from state where state_recycle_bin = 0";
//                    try {
//                        PreparedStatement ps = connection.prepareStatement(sql);
//                        ps.setObject(1, stateName);
//                        ps.setObject(2, stateType);
//                        ps.setObject(2, stateInfo);
//                        ps.setObject(3, 0);
//
//                        int n = ps.executeUpdate();
//
//                        if (n>0) {
//                            JOptionPane.showMessageDialog(null, "新增成功！");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "新增失败！");
//                        }
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    } finally {
//                        DButil.releaseConnection(connection);
//                    }
//                }
            }
        });

    }
}
