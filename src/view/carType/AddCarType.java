package view.carType;

import javax.swing.*;

public class AddCarType extends JPanel {
    private JLabel modelBrandLabel = new JLabel("品牌");
    private JComboBox modelBrandBox = new JComboBox();

    private JLabel modelNameLabel = new JLabel("型号名称");
    private JTextField modelNameField = new JTextField();

    private JLabel modelColorLabel = new JLabel("颜色");
    private JTextField modelColorField = new JTextField();

    private JLabel modelRentLabel = new JLabel("租金");
    private JTextField modelRentField = new JTextField();

    private JLabel modelInfoLabel = new JLabel("介绍");
    private JTextArea modelInfoArea = new JTextArea();

    public AddCarType() {

    }
}
