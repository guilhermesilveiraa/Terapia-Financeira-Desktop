package com.terapia.financeira.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.terapia.financeira.model.dao.GoalsDao;
import com.terapia.financeira.model.entities.Goals;

public class GoalsDaoJdbc implements GoalsDao {
    private Connection conn;

    public GoalsDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Goals obj) {
        String sql = "INSERT INTO goal (title, type, goalTotalValue, valueAchieved) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getTitle());
            st.setInt(2, obj.getType());
            st.setDouble(3, obj.getGoalTotalValue());
            st.setDouble(4, obj.getValueAchieved());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        obj.setId(rs.getInt(1)); // Assume que há um campo "id" autoincrementado
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting goal: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Goals obj) {
        String sql = "UPDATE goal SET title = ?, type = ?, goalTotalValue = ?, valueAchieved = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getTitle());
            st.setInt(2, obj.getType());
            st.setDouble(3, obj.getGoalTotalValue());
            st.setDouble(4, obj.getValueAchieved());
            st.setInt(5, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating goal: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM goal WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting goal: " + e.getMessage(), e);
        }
    }

    @Override
    public Goals findByTitle(String key) {
        String sql = "SELECT * FROM goal WHERE title = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, key);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return instantiateGoal(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding goal by title: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Goals> findByType(Integer type) {
        String sql = "SELECT * FROM goal WHERE type = ?";
        List<Goals> list = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, type);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(instantiateGoal(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding goals by type: " + e.getMessage(), e);
        }
        return list;
    }

    private Goals instantiateGoal(ResultSet rs) throws SQLException {
        Goals goal = new Goals();
        goal.setId(rs.getInt("id"));
        goal.setTitle(rs.getString("title"));
        goal.setType(rs.getInt("type"));
        goal.setGoalTotalValue(rs.getDouble("goalTotalValue"));
        goal.setValueAchieved(rs.getDouble("valueAchieved"));
        return goal;
    }
}
