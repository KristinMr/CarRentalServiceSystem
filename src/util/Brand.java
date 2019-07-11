package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Brand {
    private int brandID;
    private String brandName;
//    private String brandInfo;

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
//
//    public String getBrandInfo() {
//        return brandInfo;
//    }
//
//    public void setBrandInfo(String brandInfo) {
//        this.brandInfo = brandInfo;
//    }

    @Override
    public String toString() {
        return brandName;
    }

//    public Brand SearchBrand() {
//        Connection connection = DButil.getConnection();
//        String sql = "select * from brand";
//        Brand brand = new Brand();
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                brand.setBrandID(rs.getInt(1));
//                brand.setBrandName(rs.getString(2));
//                brand.setBrandInfo(rs.getString(3));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DButil.releaseConnection(connection);
//        }
//
//        return brand;
//    }
    public Brand searchBrand(int brandID) {
        Connection connection = DButil.getConnection();
        String sql = "select * from brand where brand_id = ?";
        Brand brand = new Brand();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,brandID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                brand.setBrandID(rs.getInt(1));
                brand.setBrandName(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        return brand;
    }

}


