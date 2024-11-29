package com.terapia.financeira.controller;

import com.terapia.financeira.model.dao.DaoFactory;
import com.terapia.financeira.model.dao.GoalsDao;
import com.terapia.financeira.model.entities.Goals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GoalsController {

    @FXML
    private TextField txtTitle;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TextField txtGoalTotalValue;

    @FXML
    private TextField txtValueAchieved;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    private GoalsDao goalsDao;

    public GoalsController() {
        // Obtém a instância do GoalsDao via DaoFactory
        this.goalsDao = DaoFactory.createGoalsDao();
    }

    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        cmbType.getItems().addAll("Curto Prazo", "Médio Prazo", "Longo Prazo");
    }

    @FXML
    @SuppressWarnings({"CallToPrintStackTrace", "unused"})
    private void handleSave() {
        try {
            String title = txtTitle.getText();
            Integer type = cmbType.getSelectionModel().getSelectedIndex();
            double goalTotalValue = Double.parseDouble(txtGoalTotalValue.getText());
            double valueAchieved = Double.parseDouble(txtValueAchieved.getText());

            Goals goal = new Goals(null, title, type, goalTotalValue, valueAchieved);

            goalsDao.insert(goal);

            System.out.println("Meta inserida com sucesso!");
            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao salvar a meta: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
