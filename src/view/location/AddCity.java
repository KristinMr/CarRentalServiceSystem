package view.location;

import util.DButil;
import util.Province;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddCity extends JPanel {
    private JMenuItem addProvince = new JMenuItem("新增省份");
    
    private JLabel provinceLabel = new JLabel("所属省份");
    private JComboBox<Province> provinceBox = new JComboBox<Province>();

    private JLabel cityNameLabel = new JLabel("城市名称");
    private JTextField cityNameField = new JTextField();

    private JLabel cityInfoLabel = new JLabel("城市名称");
    private JTextArea cityInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("添加");

    public AddCity() {
        setSize(1350,800);
        setLayout(null);

        provinceLabel.setBounds(80,40,80,30);
        provinceBox.setBounds(180,40,600,30);
        cityNameLabel.setBounds(80,100,80,30);
        cityNameField.setBounds(180,100,600,30);
        cityInfoLabel.setBounds(80,160,80,30);
        cityInfoArea.setBounds(180,160,600,100);

        resetButton.setBounds(500,310,120,30);
        addButton.setBounds(650,310,120,30);

        add(provinceLabel);
        add(provinceBox);
        add(cityNameLabel);
        add(cityNameField);
        add(cityInfoLabel);
        add(cityInfoArea);
        add(resetButton);
        add(addButton);
        setVisible(true);


        Connection connection = DButil.getConnection();
        String sql = "select * from province where province_recycle_bin = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Province province = new Province();
                province.setProvinceID(rs.getInt(1));
                province.setProvinceName(rs.getString(2));
                provinceBox.addItem(province);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新城市（城市编号自动加1）？",JOptionPane.YES_NO_OPTION);
                if (m == 0) {
                    Province province = (Province) provinceBox.getSelectedItem();

                    int cityProvince = ((Province) provinceBox.getSelectedItem()).getProvinceID();
                    String cityName = cityNameField.getText();
                    String cityInfo = cityInfoArea.getText();

                    Connection connection = DButil.getConnection();
                    String sql = "insert into city(city_province, city_name, city_info, city_recycle_bin) values(?, ?, ?, ?)";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, cityProvince);
                        ps.setObject(1, cityName);
                        ps.setObject(2, cityInfo);
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
