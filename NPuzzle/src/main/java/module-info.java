module edu.bk {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.bk to javafx.fxml;
    exports edu.bk;
}
