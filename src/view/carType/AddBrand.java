package view.carType;

import util.DButil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddBrand extends JDialog {

    private JLabel brandNameLabel = new JLabel("品牌名称");
    private JTextField brandNameField = new JTextField();

    private JLabel brandInfoLabel = new JLabel("品牌介绍");
    private JTextArea brandInfoArea = new JTextArea();

    private JButton clearButton = new JButton("清空");
    private JButton addBrandButton = new JButton("新增品牌");

    public AddBrand() {
        setTitle("新增品牌");
        setSize(430,350);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        brandNameLabel.setBounds(30,30,80,30);
        brandNameField.setBounds(130,30,200,30);
        brandInfoLabel.setBounds(30,80,80,30);
        brandInfoArea.setBounds(130,80,200,100);

        clearButton.setBounds(30,215,80,30);
        addBrandButton.setBounds(210,210,120,40);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-400, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

        add(brandNameLabel);
        add(brandNameField);
        add(brandInfoLabel);
        add(brandInfoArea);
        add(clearButton);
        add(addBrandButton);
        add(bgLabel);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brandNameField.setText("");
                brandInfoArea.setText("");
            }
        });

        addBrandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int m = JOptionPane.showConfirmDialog(null, "确认","确认添加新车辆品牌？",JOptionPane.YES_NO_OPTION);
                if (m == 0) {
                    String brandName = brandNameField.getText();
                    String brandInfo = brandInfoArea.getText();
                    Connection connection1 = DButil.getConnection();
                    String sql1 = "insert into brand(brand_name, brand_info, brand_recycle_bin) values(?, ?, 0)";
                    try {
                        PreparedStatement ps1 = connection1.prepareStatement(sql1);
                        ps1.setObject(1, brandName);
                        ps1.setObject(2, brandInfo);

                        int n1 = ps1.executeUpdate();
                        if (n1 > 0) {
                            JOptionPane.showMessageDialog(null, "车辆品牌新增成功！");
                            AddBrand.this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "车辆品牌新增失败！");
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                        DButil.releaseConnection(connection1);
                    }
                }
            }
        });
        setVisible(true);
    }
}
