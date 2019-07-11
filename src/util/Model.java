package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private int modelID;
    private int modelBrand;
    private String modelName;
    private String modelRent;
    private String modelInfo;

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public int getModelBrand() {
        return modelBrand;
    }

    public void setModelBrand(int modelBrand) {
        this.modelBrand = modelBrand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelRent() {
        return modelRent;
    }

    public void setModelRent(String modelRent) {
        this.modelRent = modelRent;
    }

    public String getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(String modelInfo) {
        this.modelInfo = modelInfo;
    }

    @Override
    public String toString() {
        return modelName;
    }

    public Model searchModel(int modelID) {
        Connection connection = DButil.getConnection();
        String sql = "select * from model where model_id = ?";
        Model model = new Model();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,modelID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                model.setModelID(rs.getInt(1));
                model.setModelName(rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        return model;
    }
}
