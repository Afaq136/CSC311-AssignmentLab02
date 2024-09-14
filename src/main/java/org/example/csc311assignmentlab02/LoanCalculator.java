
package org.example.csc311assignmentlab02;


/*
 * LoanCalculator.java
 * Written by Afaq Waris for CSC311 Assignment 2
 * This JavaFX application calculates monthly and total loan payments.
 */

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

    // Input fields for the user to enter loan details
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfLoanAmount = new TextField();

    // Output fields for displaying calculated results
    private TextField tfMonthlyPayment = new TextField();
    private TextField tfTotalPayment = new TextField();

    // Button to trigger calculation
    private Button btCalculate = new Button("Calculate");

    @Override
    public void start(Stage primaryStage) {
        // Setup the user interface using a GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);  // Set horizontal spacing between columns
        gridPane.setVgap(10);  // Set vertical spacing between rows
        gridPane.setPadding(new Insets(10));  // Add padding around the grid

        // Add labels and input fields to the grid
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

        // Make the output fields (monthly/total payment) read-only
        tfMonthlyPayment.setEditable(false);
        tfTotalPayment.setEditable(false);

        // Align the grid in the center of the window
        gridPane.setAlignment(Pos.CENTER);

        // Set the button event handler to calculate loan payment
        btCalculate.setOnAction(e -> calculateLoanPayment());

        // Setup the stage and scene
        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateLoanPayment() {
        // Convert user input into numbers for calculations
        double interest = Double.parseDouble(tfAnnualInterestRate.getText());
        int years = Integer.parseInt(tfNumberOfYears.getText());
        double loanAmount = Double.parseDouble(tfLoanAmount.getText());

        // Calculate monthly interest rate
        double monthlyInterestRate = interest / 1200;

        // Formula to calculate monthly payment
        double monthlyPayment = loanAmount * monthlyInterestRate /
                (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));

        // Display the result in the monthly payment field
        tfMonthlyPayment.setText(String.format("$%.2f", monthlyPayment));

        // Calculate and display total payment over the loan period
        double totalPayment = monthlyPayment * years * 12;
        tfTotalPayment.setText(String.format("$%.2f", totalPayment));
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}