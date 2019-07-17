package view.location;

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

public class LocationList extends JPanel {
    private JTextField searchLocationID = new JTextField("编号关键字");
    private JTextField searchLocationName = new JTextField("名称关键字");
    private JTextField searchLocationInfo = new JTextField("介绍关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchLocationButton = new JButton("查询");

    private JButton editButton = new JButton("修改所选地点");
    private JButton deleteButton = new JButton("删除所选地点");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public LocationList() {
//        setTitle("地点列表");
        setSize(1350,800);
//        setLocationRelativeTo(null);
        setLayout(null);

        searchLocationID.setForeground(Color.gray);
        searchLocationName.setForeground(Color.gray);
        searchLocationInfo.setForeground(Color.gray);

        searchLocationID.setBounds(15,40,150,30);
        searchLocationName.setBounds(185,40,150,30);
        searchLocationInfo.setBounds(355,40,150,30);
        refreshSearchButton.setBounds(720,40,80,30);
        searchLocationButton.setBounds(820,40,80,30);

        editButton.setBounds(1000,40,150,40);
        deleteButton.setBounds(1170,40,150,40);

        jScrollPane.setBounds(15,100,1310,700);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\LocationRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
//        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
//        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//        this.getContentPane().add(new JLabel());
//        ((JPanel) getContentPane()).setOpaque(false);

        add(searchLocationID);
        add(searchLocationName);
        add(searchLocationInfo);
        add(refreshSearchButton);
        add(searchLocationButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);
        add(bgLabel);

        Vector<String> locationTHVector = new Vector<String>();
        locationTHVector.add("编号");
        locationTHVector.add("省份");
        locationTHVector.add("城市");
        locationTHVector.add("备注");

        Vector<Vector<String>> locationDataVector = new Vector<Vector<String>>();

        Connection collection = DButil.getConnection();
        String sql = "select city.city_id, province.province_name, city.city_name, city.city_info, province.province_info from city, province where city.city_province = province.province_id and city.city_recycle_bin = 0";

        try {
            PreparedStatement ps = collection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add("省份备注：（" + rs.getString(4) + "）。城市备注：（" + rs.getString(5) + "）。");

                locationDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(locationDataVector, locationTHVector);
        table.setModel(defaultTableModel);
        jScrollPane.getViewport().add(table);

        table.getTableHeader().setReorderingAllowed(false);

        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(110);
        table.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getTableHeader().getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(800);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cellRenderer);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要修改的地点");
                    return;
                } else {
                    String locationID = (String)table.getValueAt(row,0);
                    new UpdateLocation();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要删除的地点");
                    return;
                } else {
                    String locationID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选地点移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update city set city_recycle_bin = 1 where city_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, locationID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "地点删除成功");
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
