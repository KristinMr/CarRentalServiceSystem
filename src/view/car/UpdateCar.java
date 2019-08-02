package view.car;

import jdk.nashorn.internal.scripts.JO;
import util.*;
import view.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateCar extends JDialog {

    private JLabel carNumberLabel = new JLabel("车牌号");
    private JTextField carNumberField = new JTextField();

    private JLabel carTypeLabel = new JLabel("车辆类型");
    private JComboBox<Brand> carBrandBox = new JComboBox<Brand>();
    private JComboBox<Model> carModelBox = new JComboBox<Model>();

    private JLabel carStateLabel = new JLabel("车辆状态");
    private JComboBox<State> carStateBox = new JComboBox<State>();

    private JLabel carColorLabel = new JLabel("车辆颜色");
    private JTextField carColorField = new JTextField();

    private JLabel carRentLabel = new JLabel("车辆租金");
    private JTextField carRentField = new JTextField();

    private JLabel carInfoLabel = new JLabel("车辆备注");
    private JTextArea carInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton confirmButton = new JButton("确认修改");

    public UpdateCar(Admin admin, String carID) {

        setTitle("更新车辆");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

        carNumberLabel.setBounds(50,20,100,30);
        carNumberField.setBounds(170,20,320,30);
        
        carTypeLabel.setBounds(50, 70, 100, 30);
        carBrandBox.setBounds(170, 70, 150, 30);
        carModelBox.setBounds(340, 70, 150, 30);

        carStateLabel.setBounds(50, 120, 100, 30);
        carStateBox.setBounds(170, 120, 320, 30);

        carColorLabel.setBounds(50, 170, 100, 30);
        carColorField.setBounds(170, 170, 320, 30);

        carRentLabel.setBounds(50, 230, 100, 30);
        carRentField.setBounds(170, 230, 320, 30);

        carInfoLabel.setBounds(250, 290, 100, 30);
        carInfoArea.setBounds(100, 340, 400, 200);

        resetButton.setBounds(260, 580, 80, 40);
        confirmButton.setBounds(380, 580, 120, 40);

        carColorField.setEditable(false);
        carRentField.setEditable(false);

        add(carNumberLabel);
        add(carNumberField);
        add(carBrandBox);
        add(carTypeLabel);
        add(carModelBox);
        add(carStateLabel);
        add(carStateBox);
        add(carColorLabel);
        add(carColorField);
        add(carRentLabel);
        add(carRentField);
        add(carInfoLabel);
        add(carInfoArea);
        add(resetButton);
        add(confirmButton);

        add(bgLabel);

        Connection connection = DButil.getConnection();
        String sql = "select car.car_id, car.car_number, car.car_model, car.car_state, car.car_info, model.model_brand from car, model where car.car_id = ? and model.model_id = car.car_model and car.car_recycle_bin = 0";
        String sql1 = "select brand_id, brand_name from brand where brand_recycle_bin = 0";
        String sql2 = "select model_id, model_name, model_color, model_rent, model_Info from model where model_brand = ? and model_recycle_bin = 0";
        String sql3 = "select state_id, state_name from state where state_type = 1 and state_recycle_bin = 0";

        try {
            Car car = new Car();

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, carID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                car.setCarID(rs.getString(1));
                car.setCarNumber(rs.getString(2));
                car.setCarModel(rs.getString(3));
                car.setCarState(rs.getString(4));
                car.setCarInfo(rs.getString(5));
                car.setCarBrand(rs.getString(6));
            }
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setObject(1, car.getCarBrand());
            PreparedStatement ps3 = connection.prepareStatement(sql3);

            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();
            ResultSet rs3 = ps3.executeQuery();


            carNumberField.setText(car.getCarNumber());
            while (rs1.next()) {
                Brand brand = new Brand();
                brand.setBrandID(rs1.getString(1));
                brand.setBrandName(rs1.getString(2));
                carBrandBox.addItem(brand);

//                if(brand.getBrandID() == brand1.getBrandID()){
//                    carBrandBox.setSelectedItem(brand1);
//                }

            }

            Brand brand1 = new Brand();
            brand1 = brand1.searchBrand(car.getCarBrand());
            for (int j = 0; j < carBrandBox.getItemCount(); j++) {
                Brand brand = carBrandBox.getItemAt(j);
                if (brand.getBrandID().equals(brand1.getBrandID())) {
                    carBrandBox.setSelectedItem(brand);
                }
            }

            while (rs2.next()) {
                Model model = new Model();
                model.setModelID(rs2.getString(1));
                model.setModelName(rs2.getString(2));
                model.setModelColor(rs2.getString(3));
                model.setModelRent(rs2.getString(4));
                model.setModelInfo(rs2.getString(5));
                carModelBox.addItem(model);
            }
            Model model1 = new Model();
            model1 = model1.searchModel(car.getCarModel());
            for (int k = 0; k < carModelBox.getItemCount(); k++) {
                Model model = carModelBox.getItemAt(k);
                if (model.getModelID().equals(model1.getModelID())) {
                    carModelBox.setSelectedItem(model);
                    carColorField.setText(model.getModelColor());
                    carRentField.setText(model.getModelRent());
                }
            }

            while (rs3.next()) {
                State state = new State();
                state.setStateID(rs3.getString(1));
                state.setStateName(rs3.getString(2));
                carStateBox.addItem(state);
            }
            State state1 = new State();
            state1 = state1.searchState(car.getCarState());
            for (int p = 0; p < carStateBox.getItemCount(); p++) {
                State state = carStateBox.getItemAt(p);
                if (state.getStateID().equals(state1.getStateID())) {
                    carStateBox.setSelectedItem(state);
                }
            }

            carInfoArea.setText(car.getCarInfo());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }


        carBrandBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carModelBox.removeAllItems();

                Brand carBrand = (Brand) carBrandBox.getSelectedItem();
                String carBrandID = carBrand.getBrandID();

                Connection connection1 = DButil.getConnection();
                String sql6 = "select model_id, model_name, model_color, model_rent, model_info from model where model_brand = ? and model_recycle_bin = 0";
                try {
                    PreparedStatement ps = connection1.prepareStatement(sql6);
                    ps.setObject(1, carBrandID);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        Model carModel = new Model();
                        carModel.setModelID(rs.getString(1));
                        carModel.setModelName(rs.getString(2));
                        carModel.setModelColor(rs.getString(3));
                        carModel.setModelRent(rs.getString(4));
                        carModel.setModelInfo(rs.getString(5));
                        carModelBox.addItem(carModel);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection1);
                }
            }
        });

        carModelBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Model carModel = (Model) carModelBox.getSelectedItem();

                if (carModel != null) {
                    carColorField.setText(carModel.getModelColor());
                    carRentField.setText(carModel.getModelRent());
                }
            }
        });


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String car_number = carNumberField.getText();
                Model model = (Model) carModelBox.getSelectedItem();
                State state = (State) carStateBox.getSelectedItem();

                String carModelID = model.getModelID();
                String carStateID = state.getStateID();
                String carInfo = carInfoArea.getText();

                Connection connection1 = DButil.getConnection();
                String sql = "update car set car_number = ?, car_model = ?, car_state = ?, car_info = ? where car_id = ?";
                try {
                    PreparedStatement ps = connection1.prepareStatement(sql);
                    ps.setObject(1, car_number);
                    ps.setObject(2, carModelID);
                    ps.setObject(3, carStateID);
                    ps.setObject(4, carInfo);
                    ps.setObject(5, carID);
                    int n = ps.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "车辆信息修改成功！");
                        UpdateCar.this.dispose();
                        Main.main.removeAll();
                        Main.main.repaint();
                        Main.main.updateUI();

                        Main.main.add(new CarList(admin));
                    } else {
                        JOptionPane.showMessageDialog(null, "车辆信息修改失败！");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection1);
                }


            }
        });

        setVisible(true);
    }

}
