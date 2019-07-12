package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private int modelID;
    private int modelBrand;
    private String modelName;
    private String modelColor;
    private String modelRent;
    private String modelInfo;
    private String model_recycle_bin;

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

    public String getModelColor() {
        return modelColor;
    }

    public void setModelColor(String modelColor) {
        this.modelColor = modelColor;
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

    public String getModel_recycle_bin() {
        return model_recycle_bin;
    }

    public void setModel_recycle_bin(String model_recycle_bin) {
        this.model_recycle_bin = model_recycle_bin;
    }

    @Override
    public String toString() {
        return modelName;
    }

    public Model searchModel(int modelID) {
        Connection connection = DButil.getConnection();
        String sql = "select * from model where model_id = ? and model_recycle_bin = 0";
        Model model = new Model();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,modelID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                model.setModelID(rs.getInt(1));
                model.setModelName(rs.getString(3));
                model.setModelColor(rs.getString(4));
                model.setModelRent(rs.getString(5));
                model.setModelInfo(rs.getString(6));
                model.setModel_recycle_bin(rs.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }
        return model;
    }
}
