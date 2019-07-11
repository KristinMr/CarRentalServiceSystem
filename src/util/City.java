package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class City {
    private int cityID;
    private int cityProvince;
    private String cityName;
    private String cityInfo;

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getCityProvince() {
        return cityProvince;
    }

    public void setCityProvince(int cityProvince) {
        this.cityProvince = cityProvince;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
    }

    @Override
    public String toString() {
        return cityName;
    }

    public City searchCity(int cityID) {
        Connection connection = DButil.getConnection();
        String sql = "select * from city where city_id = ?";
        City city = new City();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,cityID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                city.setCityID(rs.getInt(1));
                city.setCityName(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        return city;
    }
}
