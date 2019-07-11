package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Province {
    private int provinceID;
    private String provinceName;
    private String provinceInfo;

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceInfo() {
        return provinceInfo;
    }

    public void setProvinceInfo(String provinceInfo) {
        this.provinceInfo = provinceInfo;
    }

    @Override
    public String toString() {
        return provinceName;
    }
    public Province searchProvince(int provinceID) {
        Connection connection = DButil.getConnection();
        String sql = "select * from province where province_id = ?";
        Province province = new Province();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,provinceID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                province.setProvinceID(rs.getInt(1));
                province.setProvinceName(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        return province;
    }
}
