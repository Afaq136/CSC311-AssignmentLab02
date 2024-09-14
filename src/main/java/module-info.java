module org.example.csc311assignmentlab02 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.csc311assignmentlab02 to javafx.fxml;
    exports org.example.csc311assignmentlab02;
}