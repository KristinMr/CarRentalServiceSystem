package view.order;

import util.*;
import view.Main;
import view.recharge.AddRecharge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


public class  SettleOrder extends JDialog {

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
    private JTextField carStateField = new JTextField();

    private JLabel orderStateLabel = new JLabel("订单类型");
    private JTextField orderStateField = new JTextField();

    private JLabel startDateLabel = new JLabel("起租日期");
    private JTextField startDateField = new JTextField();

    private JLabel endDateLabel = new JLabel("还车日期");
    private JTextField endDateField = new JTextField();

    private JLabel orderInfoLabel = new JLabel("新增订单备注");
    private JTextArea orderInfoArea = new JTextArea();

    private JButton resetButton = new JButton("重置");
    private JButton confirmButton = new JButton("确认结算此订单");

    String orderInfo;

    public SettleOrder(Admin admin, String orderID) {
        setTitle("确认用户订单");
        setSize(850, 660);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(-200, -200, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

        adminIDLabel.setBounds(50, 30, 120, 30);
        adminIDField.setBounds(180, 30, 180, 30);
        adminIDField.setText(admin.getAdminID());
        adminIDField.setEditable(false);

        adminNameLabel.setBounds(430, 30, 120, 30);
        adminNameField.setBounds(550, 30, 180, 30);
        adminNameField.setText(admin.getAdminName());
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
        carIDField.setEditable(false);

        carNumberLabel.setBounds(430, 130, 80, 30);
        carNumberField.setBounds(550, 130, 180, 30);
        carNumberField.setEditable(false);

        carBrandLabel.setBounds(50, 180, 120, 30);
        carBrandField.setBounds(180, 180, 180, 30);
        carBrandField.setEditable(false);

        carModelLabel.setBounds(430, 180, 80, 30);
        carModelField.setBounds(550, 180, 180, 30);
        carModelField.setEditable(false);

        carRentLabel.setBounds(30, 230, 180, 30);
        carRentField.setBounds(180, 230, 180, 30);
        carRentField.setEditable(false);

        userMoneyLabel.setBounds(430, 230, 80, 30);
        userMoneyField.setBounds(550, 230, 180, 30);
        userMoneyField.setEditable(false);

        carStateLabel.setBounds(30, 280, 180, 30);
        carStateField.setBounds(180, 280, 180, 30);
        carStateField.setEditable(false);

        orderStateLabel.setBounds(430, 280, 80, 30);
        orderStateField.setBounds(550, 280, 180, 30);
        orderStateField.setEditable(false);

        startDateLabel.setBounds(50, 330, 120, 30);
        startDateField.setBounds(180, 330, 180, 30);
        startDateField.setEditable(false);

        endDateLabel.setBounds(430, 330, 120, 30);
        endDateField.setBounds(550, 330, 180, 30);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        endDateField.setText(dateFormat.format(date));
        endDateField.setEditable(false);

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
        add(carStateField);
        add(orderStateLabel);
        add(orderStateField);
        add(startDateLabel);
        add(startDateField);
        add(endDateLabel);
        add(endDateField);
        add(orderInfoLabel);
        add(orderInfoArea);
        add(resetButton);
        add(confirmButton);
        add(bgLabel);

        Connection connection = DButil.getConnection();
        String sql = "select car.car_state, oorder.order_user, user.user_name, car.car_id, car.car_number, brand.brand_name, model.model_name, model.model_rent, user.user_money, state.state_name, oorder.order_stime, oorder.order_info from oorder, user, car, model, brand, state where oorder.order_id = ? and oorder.order_user = user.user_id and oorder.order_car = car.car_id and car.car_model = model.model_id and model.model_brand = brand.brand_id and oorder.order_state = state.state_id and oorder.order_recycle_bin = 0";
        String sql1 = "select state_name from state where state_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps.setObject(1, orderID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ps1.setObject(1, rs.getString(1));

                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    adminIDField.setText(admin.getAdminID());
                    adminNameField.setText(admin.getAdminName());
                    userIDField.setText(rs.getString(2));
                    userNameField.setText(rs.getString(3));
                    carIDField.setText(rs.getString(4));
                    carNumberField.setText(rs.getString(5));
                    carBrandField.setText(rs.getString(6));
                    carModelField.setText(rs.getString(7));
                    carRentField.setText(rs.getString(8));
                    userMoneyField.setText(rs.getString(9));
                    carStateField.setText(rs1.getString(1));
                    orderStateField.setText(rs.getString(10));
                    startDateField.setText(rs.getString(11));
                    orderInfo = rs.getString(12);
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
                orderInfoArea.setText("");
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long days = Period.between(LocalDate.parse(startDateField.getText()), LocalDate.parse(endDateField.getText())).getDays();
                if (days == 0) {
                    days = 1;
                }
                Double payMoney = Double.parseDouble(carRentField.getText()) * days;
                Double userMoney = Double.parseDouble(userMoneyField.getText()) - payMoney;
                String addOrderInfo = orderInfoArea.getText();

                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String orderInfo1 = String.format("%s结账时间：%s，结账者编号：%s，结账者名称：%s，结账金额：%s，添加备注：%s。      ", orderInfo, dateFormat1.format(new Date()), admin.getAdminID(), admin.getAdminName(), payMoney, addOrderInfo);

                if (Double.parseDouble(userMoneyField.getText()) < payMoney) {
                    int m = JOptionPane.showConfirmDialog(null, "用户要结算此订单至少还需冲值" + (payMoney - Double.parseDouble(userMoneyField.getText())) + "元人民币。确认前往用户充值界面进行充值吗？","用户余额不足",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        SettleOrder.this.dispose();
                        Main.main.removeAll();
                        Main.main.repaint();
                        Main.main.updateUI();
                        Main.main.add(new AddRecharge(admin));
                    }
                } else {
                    int m1 = JOptionPane.showConfirmDialog(null, "目前用户" + userNameField.getText() + "账户余额" + userMoneyField.getText() + "元人民币，结算此订单需支付" + payMoney + "元人民币，结算完此订单用户余额还剩" + userMoney + "元人民币。确认结算此订单吗？","结账确认",JOptionPane.YES_NO_OPTION);

                    if (m1 == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql2 = "update oorder set order_state = (select state_id from state where state_name = ?), order_settle_time = ?, order_settle_money = ?, order_settle_admin = ?, order_info = ? where order_id = ?";
                        String sql3 = "update car set car_state = (select state_id from state where state_name = ?) where car_id = ?";
                        String sql4 = "update user set user_money = ? where user_id = ?";
                        try {
                            PreparedStatement ps2 = connection1.prepareStatement(sql2);

                            ps2.setObject(1, "已结算");
                            ps2.setObject(2, LocalDate.parse(endDateField.getText()));
                            ps2.setObject(3, payMoney);
                            ps2.setObject(4, admin.getAdminID());
                            ps2.setObject(5, orderInfo1);
                            ps2.setObject(6, orderID);

                            int n2 = ps2.executeUpdate();

                            if (n2 > 0) {
                                PreparedStatement ps3 = connection1.prepareStatement(sql3);
                                ps3.setObject(1, "空闲");
                                ps3.setObject(2, carIDField.getText());

                                int n3 = ps3.executeUpdate();
                                if (n3 > 0) {
                                    PreparedStatement ps4 = connection1.prepareStatement(sql4);
                                    ps4.setObject(1, userMoney);
                                    ps4.setObject(2, userIDField.getText());

                                    int n4 = ps4.executeUpdate();
                                    if (n4 > 0) {
                                        JOptionPane.showMessageDialog(null, "管理员：" + admin.getAdminName() + " 将用户" + userNameField.getText() + "的租车订单结账成功！");
                                        SettleOrder.this.dispose();
                                        Main.main.removeAll();
                                        Main.main.repaint();
                                        Main.main.updateUI();
                                        Main.main.add(new OrderList(admin));
                                    } else {
                                        JOptionPane.showMessageDialog(null, "订单和车辆状态更新成功，付款失败！");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "订单状态更新成功，车辆状态更新失败，付款失败！");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "订单结算失败！");
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        } finally {
                            DButil.releaseConnection(connection1);
                        }
                    }
                }
            }
        });

        setVisible(true);
    }
}
