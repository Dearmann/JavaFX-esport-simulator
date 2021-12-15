module javalab.esport {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens javalab.esport to javafx.fxml;
    exports javalab.esport;
}
