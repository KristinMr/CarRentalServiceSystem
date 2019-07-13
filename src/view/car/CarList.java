package view.car;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.DButil;

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

public class CarList extends JFrame {

    private JTextField searchCarID = new JTextField("编号关键字");
    private JTextField searchCarName = new JTextField("名称关键字");
    private JTextField searchCarInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchCarButton = new JButton("查询");

    private JButton editButton = new JButton("修改所选车辆");
    private JButton deleteButton = new JButton("删除所选车辆");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public CarList() {
        setTitle("车辆列表");
        setSize(1500,1000);
        setLocationRelativeTo(null);
        setLayout(null);

        searchCarID.setForeground(Color.gray);
        searchCarName.setForeground(Color.gray);
        searchCarInfo.setForeground(Color.gray);

        searchCarID.setBounds(50,30,150,30);
        searchCarName.setBounds(220,30,150,30);
        searchCarInfo.setBounds(390,30,150,30);
        refreshSearchButton.setBounds(720,30,80,30);
        searchCarButton.setBounds(820,30,80,30);

        editButton.setBounds(1100,30,150,40);
        deleteButton.setBounds(1300,30,150,40);

        jScrollPane.setBounds(15,100,1460,800);
        
        
        add(searchCarID);
        add(searchCarName);
        add(searchCarInfo);
        add(refreshSearchButton);
        add(searchCarButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);

        Vector<String> carTHVector = new Vector<String>();
        carTHVector.add("编号");
        carTHVector.add("品牌");
        carTHVector.add("型号");
        carTHVector.add("颜色");
        carTHVector.add("租金/（每天）");
        carTHVector.add("图片");
        carTHVector.add("状态");
        carTHVector.add("所在省份");
        carTHVector.add("所在城市");
        carTHVector.add("备注");

        Vector<Vector<String>> carDataVector = new Vector<Vector<String>>();

        Connection collection = DButil.getConnection();
        String sql = "select car.car_id, brand.brand_name, model.model_name, model.model_color, model.model_rent, car.car_picture, state.state_name, province.province_name, city.city_name, car.car_info from car, model, brand, state, city, province where car.car_model = model.model_id and model.model_brand = brand.brand_id and car.car_state = state.state_id and car.car_city = city.city_id and city.city_province = province.province_id and car.car_recycle_bin = 0";

        try {
            PreparedStatement ps = collection.prepareStatement(sql);
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
                vector.add(rs.getString(9));
                vector.add(rs.getString(10));

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

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要修改的车辆");
                    return;
                } else {
                    String carID = (String)table.getValueAt(row,0);
                    new UpdateCar(carID);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要删除的车辆");
                    return;
                } else {
                    String carID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选车辆移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update car set car_recycle_bin = 1 where car_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "车辆删除成功");
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

    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {

        }
        new CarList();
    }
}
