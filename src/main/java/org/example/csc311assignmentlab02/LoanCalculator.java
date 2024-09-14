package org.example.csc311assignmentlab02;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculator extends Application {

    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();
    private TextField tfMonthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();
    private Button btCalculate = new Button("Calculate");

    @Override
    public void start(Stage primaryStage) {
        // Create UI
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
        gridPane.add(tfAnnualInterestRate, 1, 0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(tfNumberOfYears, 1, 1);
        gridPane.add(new Label("Loan Amount:"), 0, 2);
        gridPane.add(tfLoanAmount, 1, 2);
        gridPane.add(new Label("Monthly Payment:"), 0, 3);
        gridPane.add(tfMonthlyPayment, 1, 3);
        gridPane.add(new Label("Total Payment:"), 0, 4);
        gridPane.add(tfTotalPayment, 1, 4);
        gridPane.add(btCalculate, 1, 5);

        // Set properties for UI elements
        tfMonthlyPayment.setEditable(false);
        tfTotalPayment.setEditable(false);
        gridPane.setAlignment(Pos.CENTER);

        // Process events
        btCalculate.setOnAction(e -> calculateLoanPayment());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateLoanPayment() {
        // Get values from input fields
        double interest = Double.parseDouble(tfAnnualInterestRate.getText());
        int years = Integer.parseInt(tfNumberOfYears.getText());
        double loanAmount = Double.parseDouble(tfLoanAmount.getText());

        // Calculate monthly interest rate
        double monthlyInterestRate = interest / 1200;

        // Calculate monthly payment
        double monthlyPayment = loanAmount * monthlyInterestRate / (1
                - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
        tfMonthlyPayment.setText(String.format("$%.2f", monthlyPayment));

        // Calculate total payment
        double totalPayment = monthlyPayment * years * 12;
        tfTotalPayment.setText(String.format("$%.2f", totalPayment));
    }

    public static void main(String[] args) {
        launch(args);
    }
}