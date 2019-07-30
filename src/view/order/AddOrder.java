package view.order;

import util.*;
import view.Login;
import view.Main;
import view.recharge.AddRecharge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class AddOrder extends JDialog {

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
    //    private JComboBox<State> carStateBox = new JComboBox<State>();
    private JTextField carStateField = new JTextField();

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

    public AddOrder(Admin admin, User user, Car car, AddOrderCar addOrderCar) {
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
        userIDField.setText(user.getUserID());
        userIDField.setEditable(false);

        userNameLabel.setBounds(430, 80, 80, 30);
        userNameField.setBounds(550, 80, 180, 30);
        userNameField.setText(user.getUserName());
        userNameField.setEditable(false);

        carIDLabel.setBounds(50, 130, 120, 30);
        carIDField.setBounds(180, 130, 180, 30);
        carIDField.setText(car.getCarID());
        carIDField.setEditable(false);

        carNumberLabel.setBounds(430, 130, 80, 30);
        carNumberField.setBounds(550, 130, 180, 30);
        carNumberField.setText(car.getCarNumber());
        carNumberField.setEditable(false);

        carBrandLabel.setBounds(50, 180, 120, 30);
        carBrandField.setBounds(180, 180, 180, 30);
        carBrandField.setText(car.getCarBrand());
        carBrandField.setEditable(false);

        carModelLabel.setBounds(430, 180, 80, 30);
        carModelField.setBounds(550, 180, 180, 30);
        carModelField.setText(car.getCarModel());
        carModelField.setEditable(false);

        carRentLabel.setBounds(30, 230, 180, 30);
        carRentField.setBounds(180, 230, 180, 30);
        carRentField.setText(car.getCarRent());
        carRentField.setEditable(false);

        userMoneyLabel.setBounds(430, 230, 80, 30);
        userMoneyField.setBounds(550, 230, 180, 30);
        userMoneyField.setText(user.getUserMoney());
        userMoneyField.setEditable(false);

        carStateLabel.setBounds(30, 280, 180, 30);
        carStateField.setBounds(180, 280, 180, 30);
        carStateField.setText(car.getCarState());
        carStateField.setEditable(false);

        orderStateLabel.setBounds(430, 280, 80, 30);
        orderStateBox.setBounds(550, 280, 180, 30);

        startDateLabel.setBounds(50, 330, 120, 30);
        startDateField.setBounds(180, 330, 180, 30);
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        startDateField.setText(dateFormat.format(date));

        Chooser chooser = Chooser.getInstance();
        chooser.register(startDateField);

        endDateLabel.setBounds(430, 330, 120, 30);
        endDateField.setBounds(550, 330, 180, 30);
        Chooser chooser1 = Chooser.getInstance();
        chooser1.register(endDateField);

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
        add(orderStateBox);
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
        String sql = "select state_id, state_name, state_type from state where state_recycle_bin = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                State state = new State();
                if ((rs.getInt(3) == 2) && (!rs.getString(2).equals("已结算"))) {
//                    state.setStateID(rs.getString(1));
//                    state.setStateName(rs.getString(2));
//                    carStateBox.addItem(state);
                    state.setStateID(rs.getString(1));
                    state.setStateName(rs.getString(2));
                    orderStateBox.addItem(state);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        startDateField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                long days = Period.between(LocalDate.parse(startDateField.getText()), LocalDate.now()).getDays();
                if (days > 0) {
                    JOptionPane.showMessageDialog(null, "起租日期应在今天或今天之后！请重新输入。");
                }
            }
        });

        endDateField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                long days = Period.between(LocalDate.parse(endDateField.getText()), LocalDate.now()).getDays();
                if (days > 0) {
                    JOptionPane.showMessageDialog(null, "还车日期应在今天或今天之后！请重新输入。");
                }
            }
        });

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

                long days = Period.between(LocalDate.parse(startDateField.getText()), LocalDate.now()).getDays();
                if (days > 0) {
                    JOptionPane.showMessageDialog(null, "起租日期应在今天或今天之后！请重新输入。");
                } else {
                    long days1 = Period.between(LocalDate.parse(endDateField.getText()), LocalDate.now()).getDays();
                    if (days1 > 0) {
                        JOptionPane.showMessageDialog(null, "还车日期应在今天或今天之后！请重新输入。");
                    } else {
                        long days2 = Period.between(LocalDate.parse(startDateField.getText()), LocalDate.parse(endDateField.getText())).getDays();
                        if (days2 < 0) {
                            JOptionPane.showMessageDialog(null, "起租日期应在还车日期之前！请重新输入。");
                        } else {
                            String carStateID = "";
                            String carStateName = "";
                            State orderState = (State) orderStateBox.getSelectedItem();
                            String orderStateID = orderState.getStateID();
                            String orderStateName = orderState.getStateName();
                            if (orderStateName.equals("预约")) {
                                carStateName = "被预约";
                            } else if (orderStateName.equals("租赁")) {
                                carStateName = "租赁中";
                            }

                            String orderStartDate = startDateField.getText();

                            System.out.println(orderStartDate);
                            String orderEndDate = endDateField.getText();
                            if (orderEndDate.equals("")) {
                                orderEndDate = null;
                            }
                            String orderInfo = orderInfoArea.getText();
                            Connection connection1 = DButil.getConnection();
                            String sql = "select state_id from state where state_name = ?";
                            String sql1 = "insert into oorder (order_admin, order_user, order_car, order_time, order_stime, order_state, order_info, order_recycle_bin) values (?,?,?,?,?,?,?,0)";
                            String sql2 = "update car set car_state = ? where car_id = ?";
                            try {
                                PreparedStatement ps = connection1.prepareStatement(sql);
                                ps.setObject(1, carStateName);

                                ResultSet rs = ps.executeQuery();
                                while (rs.next()) {
                                    carStateID = rs.getString(1);
                                    PreparedStatement ps1 = connection1.prepareStatement(sql1);
                                    ps1.setObject(1, admin.getAdminID());
                                    ps1.setObject(2, user.getUserID());
                                    ps1.setObject(3, car.getCarID());
                                    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm;ss");
                                    ps1.setObject(4, dateFormat1.format(new Date()));
                                    ps1.setObject(5, orderStartDate);
                                    ps1.setObject(6, orderStateID);
                                    if (orderEndDate == null) {
                                        ps1.setObject(7, orderInfo);
                                    } else {
                                        ps1.setObject(7, "预计" + orderEndDate + "还车。" + orderInfo);
                                    }

                                    int n1 = ps1.executeUpdate();
                                    if (n1 > 0) {
                                        PreparedStatement ps2 = connection1.prepareStatement(sql2);
                                        ps2.setObject(1, carStateID);
                                        ps2.setObject(2, car.getCarID());

                                        int n2 = ps2.executeUpdate();
                                        if (n2 > 0) {
                                            JOptionPane.showMessageDialog(null, "管理员：" + admin.getAdminName() + " 将用户" + user.getUserName() + "的租车订单新增成功！");

                                            AddOrder.this.dispose();
                                            addOrderCar.dispose();

                                            Main.main.removeAll();
                                            Main.main.repaint();
                                            Main.main.updateUI();

                                            Main.main.add(new OrderList(admin));
                                        } else {
                                            JOptionPane.showMessageDialog(null, "管理员：" + admin.getAdminName() + " 将用户" + user.getUserName() + "的租车订单新增成功！   但车辆状态更新失败！");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "新增订单失败！请重新操作！");
                                    }
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            } finally {
                                DButil.releaseConnection(connection1);
                            }
                        }
                    }
                }
            }
        });

        setVisible(true);
    }

}
