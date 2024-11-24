package com.terapia.financeira.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert;

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

    @FXML
    public void initialize() {
        // Configurações iniciais (dados simulados para teste)
        chartGoalsProgress.getData().addAll(
            new PieChart.Data("Meta 1 (60%)", 60),
            new PieChart.Data("Meta 2 (30%)", 30),
            new PieChart.Data("Restante", 10)
        );

        // Configuração dos botões
        btnInserirMetas.setOnAction(event -> showMessage("Abrir tela de Inserir Metas"));
        btnCustosFixos.setOnAction(event -> showMessage("Abrir tela de Custos Fixos"));
        btnCustosVariaveis.setOnAction(event -> showMessage("Abrir tela de Custos Variáveis"));
        btnRendas.setOnAction(event -> showMessage("Abrir tela de Rendas"));
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ação");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
