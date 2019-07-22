package view.order;

import util.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateOrder extends JDialog {
    private JLabel adminIDLabel = new JLabel("管理员编号");
    private JTextField adminIDField = new JTextField();

    private JLabel adminNameLabel = new JLabel("管理员名称");
    private JTextField adminNameField = new JTextField();

    private JLabel userIDLabel = new JLabel("用户编号");
    private JTextField userIDField = new JTextField();

    private JLabel userNameLabel = new JLabel("用户名称");
    private JTextField userNameField = new JTextField();

    private JLabel carIDLabel = new JLabel("车辆编号");
    private JTextField carIDField = new JTextField();

    private JLabel carNumberLabel = new JLabel("车牌号");
    private JTextField carNumberField = new JTextField();

    private JLabel carBrandLabel = new JLabel("车辆品牌");
    private JTextField carBrandField = new JTextField();

    private JLabel carModelLabel = new JLabel("品牌型号");
    private JTextField carModelField = new JTextField();

    private JLabel carRentLabel = new JLabel("车辆租金（元/每天）");
    private JTextField carRentField = new JTextField();

    private JLabel userMoneyLabel = new JLabel("用户余额");
    private JTextField userMoneyField = new JTextField();

    private JLabel carStateLabel = new JLabel("车辆状态");
    private JComboBox<State> carStateBox = new JComboBox<State>();

    private JLabel orderStateLabel = new JLabel("订单类型");
    private JComboBox<State> orderStateBox = new JComboBox<State>();

    private JLabel startDateLabel = new JLabel("起租日期");
    private JTextField startDateField = new JTextField();

    private JLabel endDateLabel = new JLabel("还车日期");
    private JTextField endDateField = new JTextField();

    private JLabel orderInfoLabel = new JLabel("订单备注");
    private JTextArea orderInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton confirmButton = new JButton("确认新增订单");

    public UpdateOrder(String orderID) {
        setTitle("确认用户订单");
        setSize(850, 660);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        adminIDLabel.setBounds(50, 30, 120, 30);
        adminIDField.setBounds(180, 30, 180, 30);
//        adminIDField.setText(admin.getAdminID());
        adminIDField.setEditable(false);

        adminNameLabel.setBounds(430, 30, 120, 30);
        adminNameField.setBounds(550, 30, 180, 30);
//        adminNameField.setText(admin.getAdminName());
        adminNameField.setEditable(false);

        userIDLabel.setBounds(50, 80, 120, 30);
        userIDField.setBounds(180, 80, 180, 30);
//        userIDField.setText(user.getUserID());
        userIDField.setEditable(false);

        userNameLabel.setBounds(430, 80, 80, 30);
        userNameField.setBounds(550, 80, 180, 30);
//        userNameField.setText(user.getUserName());
        userNameField.setEditable(false);

        carIDLabel.setBounds(50, 130, 120, 30);
        carIDField.setBounds(180, 130, 180, 30);
//        carIDField.setText(car.getCarID());
        carIDField.setEditable(false);

        carNumberLabel.setBounds(430, 130, 80, 30);
        carNumberField.setBounds(550, 130, 180, 30);
//        carNumberField.setText(car.getCarNumber());
        carNumberField.setEditable(false);

        carBrandLabel.setBounds(50, 180, 120, 30);
        carBrandField.setBounds(180, 180, 180, 30);
//        carBrandField.setText(car.getCarBrand());
        carBrandField.setEditable(false);

        carModelLabel.setBounds(430, 180, 80, 30);
        carModelField.setBounds(550, 180, 180, 30);
//        carModelField.setText(car.getCarModel());
        carModelField.setEditable(false);

        carRentLabel.setBounds(30, 230, 180, 30);
        carRentField.setBounds(180, 230, 180, 30);
//        carRentField.setText(car.getCarRent());
        carModelField.setEditable(false);

        userMoneyLabel.setBounds(430, 230, 80, 30);
        userMoneyField.setBounds(550, 230, 180, 30);
//        userMoneyField.setText(user.getUserMoney());
        userMoneyField.setEditable(false);

        carStateLabel.setBounds(30, 280, 180, 30);
        carStateBox.setBounds(180, 280, 180, 30);

        orderStateLabel.setBounds(430, 280, 80, 30);
        orderStateBox.setBounds(550, 280, 180, 30);

        startDateLabel.setBounds(50, 330, 120, 30);
        startDateField.setBounds(180, 330, 180, 30);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startDateField.setText(dateFormat.format(date));

        endDateLabel.setBounds(430, 330, 120, 30);
        endDateField.setBounds(550, 330, 180, 30);

        orderInfoLabel.setBounds(50, 380, 120, 30);
        orderInfoArea.setBounds(180, 380, 550, 60);

        resetButton.setBounds(50, 495, 80, 30);
        confirmButton.setBounds(560, 490, 180, 40);


        add(adminIDLabel);
        add(adminIDField);
        add(adminNameLabel);
        add(adminNameField);
        add(userIDLabel);
        add(userIDField);
        add(userNameLabel);
        add(userNameField);
        add(carIDLabel);
        add(carIDField);
        add(carNumberLabel);
        add(carNumberField);
        add(carBrandLabel);
        add(carBrandField);
        add(carModelLabel);
        add(carModelField);
        add(carRentLabel);
        add(carRentField);
        add(userMoneyLabel);
        add(userMoneyField);
        add(carStateLabel);
        add(carStateBox);
        add(orderStateLabel);
        add(orderStateBox);
        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(orderInfoLabel);
        add(orderInfoArea);
        add(resetButton);
        add(confirmButton);

        Connection connection = DButil.getConnection();
        String sql = "select * from oorder where order_id = ?";
        String sql1 = "select * from state where state_recycle_bin = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            }
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                State state = new State();
                if (rs1.getInt(3) == 1) {
                    state.setStateID(rs1.getString(1));
                    state.setStateName(rs1.getString(2));
                    carStateBox.addItem(state);
                } else {
                    state.setStateID(rs1.getString(1));
                    state.setStateName(rs1.getString(2));
                    orderStateBox.addItem(state);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startDateField.setText("");
                endDateField.setText("");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                State carState = (State) carStateBox.getSelectedItem();
                String carStateID = carState.getStateID();
                State orderState = (State) orderStateBox.getSelectedItem();
                String orderStateID = orderState.getStateID();

                String orderStartDate = startDateField.getText();

                String orderEndDate = endDateField.getText();
                if (orderEndDate.equals("")) {
                    orderEndDate = null;
                } else {
                    orderEndDate = dateFormat.format(orderEndDate);
                }
                String orderInfo = orderInfoArea.getText();
                Connection connection1 = DButil.getConnection();
                String sql = "";
                String sql1 = "update car set car_state = ? where car_id = ?";
                try {
                    PreparedStatement preparedStatement = connection1.prepareStatement(sql);

                    int n = preparedStatement.executeUpdate();
                    if (n > 0) {
                        PreparedStatement preparedStatement1 = connection1.prepareStatement(sql1);
                        preparedStatement1.setObject(1, carStateID);
//                        preparedStatement1.setObject(2, car.getCarID());

                        int n1 = preparedStatement1.executeUpdate();
                        if (n > 0) {
                            UpdateOrder.this.dispose();
//                            JOptionPane.showMessageDialog(null, "管理员：" + admin.getAdminName() + " 将用户" + user.getUserName() + "的租车订单新增成功！");
                        } else {
//                            JOptionPane.showMessageDialog(null, "管理员：" + admin.getAdminName() + " 将用户" + user.getUserName() + "的租车订单新增成功！   但车辆状态更新失败！");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "新增订单失败！请重新操作！");
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
