module com.terapia.financeira {
    requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;

    opens com.terapia.financeira to javafx.fxml;
    exports com.terapia.financeira;
}
