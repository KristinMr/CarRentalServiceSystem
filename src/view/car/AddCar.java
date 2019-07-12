package view.car;

import util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddCar extends JDialog {

    private JLabel carModelLabel = new JLabel("车辆型号");
    private JComboBox<Brand> carBrandBox = new JComboBox<Brand>();
    private JComboBox<Model> carModelBox = new JComboBox<Model>();

    private JLabel carStateLabel = new JLabel("车辆状态");
    private JComboBox<State> carStateBox = new JComboBox<State>();

    private JLabel carLocationLabel = new JLabel("车辆地点");
    private JComboBox<Province> carProvinceBox = new JComboBox<Province>();
    private JComboBox<City> carCityBox = new JComboBox<City>();

    private JLabel carColorLabel = new JLabel("车辆颜色");
    private JTextField carColorField = new JTextField();

    private JLabel carRentLabel = new JLabel("车辆租金");
    private JTextField carRentField = new JTextField();

    private JLabel carPictureLabel = new JLabel("车辆图片");
    private JTextField carPictureField = new JTextField();

    private JLabel carInfoLabel = new JLabel("车辆备注");
    private JTextArea carInfoArea = new JTextArea();

    private JButton resetBotton = new JButton("重置");
    private JButton addBotton = new JButton("添加");

    public AddCar() {
        setTitle("新增车辆");
        setSize(600,700);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        carModelLabel.setBounds(50,20,100,30);
        carBrandBox.setBounds(170,20,150,30);
        carModelBox.setBounds(340,20,150,30);

        carStateLabel.setBounds(50,70,100,30);
        carStateBox.setBounds(170,70,320,30);

        carLocationLabel.setBounds(50,120,100,30);
        carProvinceBox.setBounds(170,120,150,30);
        carCityBox.setBounds(340,120,150,30);

        carColorLabel.setBounds(50,170,100,30);
        carColorField.setBounds(170,170,320,30);

        carRentLabel.setBounds(50,220,100,30);
        carRentField.setBounds(170,220,320,30);

        carPictureLabel.setBounds(50,280,100,30);
        carPictureField.setBounds(170,280,320,30);

        carInfoLabel.setBounds(250,340,100,30);
        carInfoArea.setBounds(80,380,420,200);

        resetBotton.setBounds(260,600,80,40);
        addBotton.setBounds(400,600,80,40);


        carColorField.setEditable(false);
        carRentField.setEditable(false);

        add(carBrandBox);
        add(carModelLabel);
        add(carModelBox);
        add(carStateLabel);
        add(carStateBox);
        add(carLocationLabel);
        add(carProvinceBox);
        add(carCityBox);
        add(carColorLabel);
        add(carColorField);
        add(carRentLabel);
        add(carRentField);
        add(carPictureLabel);
        add(carPictureField);
        add(carInfoLabel);
        add(carInfoArea);
        add(resetBotton);
        add(addBotton);

        Connection connection = DButil.getConnection();
        String sql = "select * from brand where brand_recycle_bin = 0";
        String sql1 = "select * from brand where brand_recycle_bin = 0 order by brand_id limit 1";
        String sql2 = "select * from model where model_brand = ? and model_recycle_bin = 0";
        String sql3 = "select * from state";
        String sql4 = "select * from province";
        String sql5 = "select * from province where province_recycle_bin = 0 order by province_id limit 1";
        String sql6 = "select * from city where city_province = ? and city_recycle_bin = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            PreparedStatement ps4 = connection.prepareStatement(sql4);
            PreparedStatement ps5 = connection.prepareStatement(sql5);
            PreparedStatement ps6 = connection.prepareStatement(sql6);

            ResultSet rs = ps.executeQuery();
            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs3 = ps3.executeQuery();
            ResultSet rs4 = ps4.executeQuery();
            ResultSet rs5 = ps5.executeQuery();

            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandID(rs.getInt(1));
                brand.setBrandName(rs.getString(2));
                carBrandBox.addItem(brand);
            }

            if (rs1.next()) {
                int brandID = rs1.getInt(1);
                ps2.setObject(1, brandID);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    Model carModel = new Model();
                    carModel.setModelID(rs2.getInt(1));
                    carModel.setModelName(rs2.getString(3));
                    carModel.setModelColor(rs2.getString(4));
                    carModel.setModelRent(rs2.getString(5));
                    carModelBox.addItem(carModel);
                }
            }

            while (rs3.next()){
                State state = new State();
                state.setStateID(rs3.getInt(1));
                state.setStateName(rs3.getString(2));
                carStateBox.addItem(state);
            }
            while (rs4.next()){
                Province province = new Province();
                province.setProvinceID(rs4.getInt(1));
                province.setProvinceName(rs4.getString(2));
                carProvinceBox.addItem(province);
            }

            if (rs5.next()) {
                int provinceID = rs5.getInt(1);
                ps6.setObject(1, provinceID);
                ResultSet rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    City carCity = new City();
                    carCity.setCityID(rs6.getInt(1));
                    carCity.setCityName(rs6.getString(3));
                    carCityBox.addItem(carCity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        resetBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        carBrandBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                carModelBox.removeAllItems();

                Brand carBrain = (Brand) carBrandBox.getSelectedItem();
                int carBrandID = carBrain.getBrandID();

                Connection connection1 = DButil.getConnection();
                String sql3 = "select * from model where model_brand = ? and model_recycle_bin = 0";
                try {
                    PreparedStatement ps = connection1.prepareStatement(sql3);
                    ps.setObject(1,carBrandID);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()){
                        Model carModel = new Model();
                        carModel.setModelID(rs.getInt(1));
                        carModel.setModelName(rs.getString(3));
                        carModel.setModelColor(rs.getString(4));
                        carModel.setModelRent(rs.getString(5));
                        carModelBox.addItem(carModel);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection1);
                }
            }
        });

        carProvinceBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carCityBox.removeAllItems();

                Province carProvince = (Province) carProvinceBox.getSelectedItem();
                int carProvinceID = carProvince.getProvinceID();

                Connection connection2 = DButil.getConnection();
                String sql4 = "select city_id, city_name from city where city_province = ?";

                try {
                    PreparedStatement ps = connection2.prepareStatement(sql4);
                    ps.setObject(1,carProvinceID);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        City carCity = new City();
                        carCity.setCityID(rs.getInt(1));
                        carCity.setCityName(rs.getString(2));
                        carCityBox.addItem(carCity);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection2);
                }
            }
        });

        addBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Model carModel = (Model) carModelBox.getSelectedItem();
                int carModelID = carModel.getModelID();

                State carState = (State) carStateBox.getSelectedItem();
                int carStateID = carState.getStateID();

                City carCity = (City) carCityBox.getSelectedItem();
                int carCityID = carCity.getCityID();

                String carColor = carColorField.getText();

                String carInfo = carInfoArea.getText();

                Connection connection3 = DButil.getConnection();
                String sql5 = "insert into car (car_model, car_state, car_city, car_info) values(?, ?, ?, ?)";
                try{
                    PreparedStatement ps = connection3.prepareStatement(sql5);
                    ps.setObject(1,carModelID);
                    ps.setObject(2,carStateID);
                    ps.setObject(3,carCityID);
                    ps.setObject(4,carInfo);

                    int n = ps.executeUpdate();

                    if (n>0) {
                        JOptionPane.showMessageDialog(null, "添加成功！");
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！");
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                } finally {
                    DButil.releaseConnection(connection3);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCar();
    }

}
