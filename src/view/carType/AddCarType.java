package view.carType;

import util.Brand;
import util.DButil;
import view.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddCarType extends JPanel {
    private JLabel typeBrandLabel = new JLabel("品牌");
    private JComboBox<Brand> typeBrandBox = new JComboBox<Brand>();

    private JButton carBrandListButton = new JButton("品牌列表");

    private JLabel typeModelLabel = new JLabel("型号名称");
    private JTextField typeModelField = new JTextField();

    private JLabel typeColorLabel = new JLabel("颜色");
    private JTextField typeColorField = new JTextField();

    private JLabel typeRentLabel = new JLabel("租金");
    private JTextField typeRentField = new JTextField();

    private JLabel typeInfoLabel = new JLabel("备注");
    private JTextArea typeInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton addButton = new JButton("确认添加车型");

    public AddCarType() {
        setSize(1350,800);
        setLayout(null);

        typeBrandLabel.setBounds(80,50,80,30);
        typeBrandBox.setBounds(180,50,200,30);
        carBrandListButton.setBounds(900,40,150,40);

        typeModelLabel.setBounds(500,50,80,30);
        typeModelField.setBounds(600,50,200,30);

        typeColorLabel.setBounds(80,120,80,30);
        typeColorField.setBounds(180,120,200,30);

        typeRentLabel.setBounds(500,120,80,30);
        typeRentField.setBounds(600,120,200,30);

        typeInfoLabel.setBounds(80,190,80,30);
        typeInfoArea.setBounds(180,190,620,100);

        resetButton.setBounds(80,350,80,30);
        addButton.setBounds(680,350,120,40);

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
        add(typeInfoArea);
        add(resetButton);
        add(addButton);

        Connection connection = DButil.getConnection();
        String sql = "select brand_id, brand_name from brand where brand_recycle_bin = 0";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandID(rs.getString(1));
                brand.setBrandName(rs.getString(2));
                typeBrandBox.addItem(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        carBrandListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BrandList();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.removeAll();
                Main.main.repaint();
                Main.main.updateUI();

                Main.main.add(new AddCarType());
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新车型？",JOptionPane.YES_NO_OPTION);
                if (m == 0) {
                    Brand typeBrain = (Brand) typeBrandBox.getSelectedItem();
                    String typeBrandID = typeBrain.getBrandID();
                    String typeModelName = typeModelField.getText();
                    String typeColor = typeColorField.getText();
                    String typeRent = typeRentField.getText();
                    String typeInfo = typeInfoArea.getText();

                    Connection connection = DButil.getConnection();
                    String sql = "insert into model(model_brand, model_name, model_color, model_rent, model_info, model_recycle_bin) values(?, ?, ?, ?, ? , 0)";
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setObject(1, typeBrandID);
                        ps.setObject(2, typeModelName);
                        ps.setObject(3, typeColor);
                        ps.setObject(4, typeRent);
                        ps.setObject(5, typeInfo);

                        int n = ps.executeUpdate();

                        if (n>0) {
                            JOptionPane.showMessageDialog(null, "车型新增成功！");
                            Main.main.removeAll();
                            Main.main.repaint();
                            Main.main.updateUI();

                            Main.main.add(new CarTypeList());
                        } else {
                            JOptionPane.showMessageDialog(null, "车型新增失败！");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        DButil.releaseConnection(connection);
                    }
                }
            }
        });

        setVisible(true);
    }
}
