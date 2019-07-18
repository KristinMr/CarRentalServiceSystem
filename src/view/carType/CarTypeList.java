package view.carType;

import util.DButil;
import view.carType.UpdateCarType;

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

public class CarTypeList extends JPanel {
    private JTextField searchCarTypeID = new JTextField("编号关键字");
    private JTextField searchCarTypeName = new JTextField("型号关键字");
    private JTextField searchCarTypeInfo = new JTextField("颜色关键字");
    private JButton refreshSearchButton = new JButton("刷新");
    private JButton searchCarTypeButton = new JButton("查询");

    private JButton editButton = new JButton("修改所选车型");
    private JButton deleteButton = new JButton("删除所选车型");

    private JScrollPane jScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private JTable table = new JTable() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };


    public CarTypeList() {
//        setTitle("车型列表");
        setSize(1350,800);
//        setLocationRelativeTo(null);
        setLayout(null);

        searchCarTypeID.setForeground(Color.gray);
        searchCarTypeName.setForeground(Color.gray);
        searchCarTypeInfo.setForeground(Color.gray);

        searchCarTypeID.setBounds(15,40,150,30);
        searchCarTypeName.setBounds(185,40,150,30);
        searchCarTypeInfo.setBounds(355,40,150,30);
        refreshSearchButton.setBounds(720,40,80,30);
        searchCarTypeButton.setBounds(820,40,80,30);

        editButton.setBounds(1000,40,150,40);
        deleteButton.setBounds(1170,40,150,40);

        jScrollPane.setBounds(15,100,1310,700);


        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mrcap\\IdeaProjects\\CarRentalServiceSystem\\src\\source\\main.jpg");
        JLabel bgLabel = new JLabel(imageIcon);
//        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
//        bgLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
//        this.getContentPane().add(new JLabel());
//        ((JPanel) getContentPane()).setOpaque(false);

        add(searchCarTypeID);
        add(searchCarTypeName);
        add(searchCarTypeInfo);
        add(refreshSearchButton);
        add(searchCarTypeButton);
        add(editButton);
        add(deleteButton);
        add(jScrollPane);
        add(bgLabel);

        Vector<String> carTypeTHVector = new Vector<String>();
        carTypeTHVector.add("车型编号");
        carTypeTHVector.add("品牌");
        carTypeTHVector.add("型号");
        carTypeTHVector.add("颜色");
        carTypeTHVector.add("租金/（每天）");
        carTypeTHVector.add("备注");

        Vector<Vector<String>> carTypeDataVector = new Vector<Vector<String>>();

        Connection collection = DButil.getConnection();
        String sql = "select model.*, brand.brand_name from model, brand where model.model_brand = brand.brand_id and model.model_recycle_bin = 0";

        try {
            PreparedStatement ps = collection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(8));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                carTypeDataVector.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(collection);
        }

        DefaultTableModel defaultTableModel = new DefaultTableModel(carTypeDataVector, carTypeTHVector);
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
                    JOptionPane.showMessageDialog(null, "请先选中要修改的车型");
                    return;
                } else {
                    String carTypeID = (String)table.getValueAt(row,0);
                    new UpdateCarType(carTypeID);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(null, "请先选中要删除的车型");
                    return;
                } else {
                    String carTypeID = (String)table.getValueAt(row,0);
                    int m = JOptionPane.showConfirmDialog(null, "确认","将所选车型移入回收站？",JOptionPane.YES_NO_OPTION);
                    if (m == 0) {
                        Connection connection1 = DButil.getConnection();
                        String sql1 = "update model set model_recycle_bin = 1 where model_id = ?";
                        try{
                            PreparedStatement ps = connection1.prepareStatement(sql1);
                            ps.setObject(1, carTypeID);
                            int n = ps.executeUpdate();
                            if (n>0){
                                ((DefaultTableModel)table.getModel()).removeRow(row);
                                JOptionPane.showMessageDialog(null, "车型删除成功");
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

//    public static void main(String[] args) {
//        try {
//            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.translucencyAppleLike;
////            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        } catch (Exception e) {
//
//        }
//        new CarTypeList();
//    }
}
