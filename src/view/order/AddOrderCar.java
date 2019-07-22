package view.order;

import util.Admin;
import util.Car;
import util.DButil;
import util.User;
import view.car.UpdateCar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AddOrderCar extends JDialog {

    private JTextField searchCarID = new JTextField("编号关键字");
    private JTextField searchCarName = new JTextField("名称关键字");
    private JTextField searchCarInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchCarButton = new JButton("查询");
    
    private JButton addOrderCarButton = new JButton("将所选车辆下单");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public AddOrderCar(Admin admin, User user) {
        setTitle("选中车辆给" + user.getUserName() + "下单");
        setSize(1400,800);
        setLocationRelativeTo(null);
        setLayout(null);
        setModal(true);

        searchCarID.setForeground(Color.gray);
        searchCarName.setForeground(Color.gray);
        searchCarInfo.setForeground(Color.gray);

        searchCarID.setBounds(15,40,150,30);
        searchCarName.setBounds(185,40,150,30);
        searchCarInfo.setBounds(355,40,150,30);
        refreshSearchButton.setBounds(720,40,80,30);
        searchCarButton.setBounds(820,40,80,30);
        
        addOrderCarButton.setBounds(1170,40,150,40);

        jScrollPane.setBounds(15,100,1310,600);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.getContentPane().add(new JLabel());
        ((JPanel) getContentPane()).setOpaque(false);

        add(searchCarID);
        add(searchCarName);
        add(searchCarInfo);
        add(refreshSearchButton);
        add(searchCarButton);
        add(addOrderCarButton);
        add(jScrollPane);
        add(bgLabel);

        Vector<String> carTHVector = new Vector<String>();
        carTHVector.add("编号");
        carTHVector.add("车牌号");
        carTHVector.add("品牌");
        carTHVector.add("型号");
        carTHVector.add("颜色");
        carTHVector.add("租金/（每天）");
//        carTHVector.add("图片");
        carTHVector.add("状态");
        carTHVector.add("备注");

        Vector<Vector<String>> carDataVector = new Vector<Vector<String>>();

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Connection collection = DButil.getConnection();
        String sql = "select car.car_id, car.car_number, brand.brand_name, model.model_name, model.model_color, model.model_rent, state.state_name, car.car_info from car, model, brand, state where car.car_model = model.model_id and model.model_brand = brand.brand_id and car.car_state = state.state_id  and car.car_recycle_bin = 0 and state.state_name = ?";

        try {
            PreparedStatement ps = collection.prepareStatement(sql);
            ps.setObject(1, "空闲");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                vector.add(rs.getString(7));
                vector.add(rs.getString(8));
//                vector.add(rs.getString(9));

                carDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(carDataVector, carTHVector);
        table.setModel(defaultTableModel);
        jScrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        addOrderCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要下单的车辆");
                    return;
                } else {
                    String carID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选车辆加入订单？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "select car.car_number, car.car_state, brand.brand_name, model.model_name, model.model_rent from car, brand, model where car.car_model = model.model_id and model.model_brand = brand.brand_id and car.car_id = ?";
                        try{
                            Car car = new Car();
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carID);
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                car.setCarID(carID);
                                car.setCarNumber(rs.getString(1));
                                car.setCarState(rs.getString(2));
                                car.setCarBrand(rs.getString(3));
                                car.setCarModel(rs.getString(4));
                                car.setCarRent(rs.getString(5));
                                new AddOrder(admin, user, car);
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
