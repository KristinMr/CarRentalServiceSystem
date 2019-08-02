package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class State {
    private String stateID;
    private String stateName;
    private String stateType;
    private String stateInfo;

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateType() {
        return stateType;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    @Override
    public String toString() {
        return stateName;
    }

    public State searchState(String stateID) {
        Connection connection = DButil.getConnection();
        String sql = "select state_id, state_name from state where state_id = ?";
        State state = new State();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,stateID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                state.setStateID(rs.getString(1));
                state.setStateName(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.releaseConnection(connection);
        }

        return state;
    }
}
