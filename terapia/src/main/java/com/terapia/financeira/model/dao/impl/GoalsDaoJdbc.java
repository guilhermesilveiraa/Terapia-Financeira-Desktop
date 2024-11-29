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
    private final Connection conn;

    public GoalsDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Goals obj) {
        String sql = "INSERT INTO goals (title, type, goalTotalValue, valueAchieved) VALUES (?, ?, ?, ?)";
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
            throw new RuntimeException("Error while inserting goals: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Goals obj) {
        String sql = "UPDATE goals SET title = ?, type = ?, goalTotalValue = ?, valueAchieved = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getTitle());
            st.setInt(2, obj.getType());
            st.setDouble(3, obj.getGoalTotalValue());
            st.setDouble(4, obj.getValueAchieved());
            st.setInt(5, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating goals: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM goals WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting goals: " + e.getMessage(), e);
        }
    }

    @Override
    public Goals findByTitle(String key) {
        String sql = "SELECT * FROM goals WHERE title = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, key);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return instantiateGoal(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding goals by title: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Goals> findByType(Integer type) {
        String sql = "SELECT * FROM goals WHERE type = ?";
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
        Goals goals = new Goals();
        goals.setId(rs.getInt("id"));
        goals.setTitle(rs.getString("title"));
        goals.setType(rs.getInt("type"));
        goals.setGoalTotalValue(rs.getDouble("goalTotalValue"));
        goals.setValueAchieved(rs.getDouble("valueAchieved"));
        return goals;
    }
}
