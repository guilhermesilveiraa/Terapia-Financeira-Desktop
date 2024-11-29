package com.terapia.financeira.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button btnInserirMetas;
    @FXML
    private Button btnCustosFixos;
    @FXML
    private Button btnCustosVariaveis;
    @FXML
    private Button btnRendas;
    @FXML
    private PieChart chartGoalsProgress;
    @FXML
    private ProgressBar progressGoal1;
    @FXML
    private ProgressBar progressGoal2;

    // Dados simulados para as metas
    private double goal1Progress = 0.6; // 60%
    private double goal2Progress = 0.3; // 30%

    @FXML
    public void initialize() {
        // Configuração inicial dos gráficos e progressos
        setupGoals();
        
        // Configuração dos botões com ações
        btnInserirMetas.setOnAction(event -> openInsertGoalsScreen());
        btnCustosFixos.setOnAction(event -> showMessage("Abrir tela de Custos Fixos"));
        btnCustosVariaveis.setOnAction(event -> showMessage("Abrir tela de Custos Variáveis"));
        btnRendas.setOnAction(event -> showMessage("Abrir tela de Rendas"));
    }

    private void openInsertGoalsScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/terapia/financeira/insertGoals.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Inserir Metas");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showMessage("Erro ao abrir tela de Inserir Metas");
        }
    }

    // Métodos restantes permanecem iguais ao código anterior
    private void setupGoals() {
        // Configuração inicial do PieChart
        chartGoalsProgress.getData().clear();
        chartGoalsProgress.getData().addAll(
                new PieChart.Data("Meta 1 (60%)", goal1Progress * 100),
                new PieChart.Data("Meta 2 (30%)", goal2Progress * 100),
                new PieChart.Data("Restante", (1.0 - (goal1Progress + goal2Progress)) * 100)
        );
        // Atualiza os ProgressBars
        progressGoal1.setProgress(goal1Progress);
        progressGoal2.setProgress(goal2Progress);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateGoals(double newGoal1Progress, double newGoal2Progress) {
        // Atualiza os valores das metas
        this.goal1Progress = Math.max(0, Math.min(newGoal1Progress, 1.0)); // Garante que o valor está entre 0 e 1
        this.goal2Progress = Math.max(0, Math.min(newGoal2Progress, 1.0)); // Garante que o valor está entre 0 e 1
        // Atualiza a interface
        setupGoals();
    }
}