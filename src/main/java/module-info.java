module edu.farmingdale.csc311_m03_assignment03 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.csc311_m03_assignment03 to javafx.fxml;
    exports edu.farmingdale.csc311_m03_assignment03;
}