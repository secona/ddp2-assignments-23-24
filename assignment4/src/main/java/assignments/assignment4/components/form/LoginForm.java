package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment4.MainApp;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;

import java.util.function.Consumer;

public class LoginForm {
    public static Alert USER_NOT_FOUND_ALERT = new Alert(
            AlertType.ERROR,
            "User not found!",
            ButtonType.OK);

    private Stage stage;
    private MainApp mainApp; // MainApp instance
    private TextField nameInput;
    private TextField phoneNumberInput;

    public LoginForm(Stage stage, MainApp mainApp) { // Pass MainApp instance to constructor
        this.stage = stage;
        this.mainApp = mainApp; // Store MainApp instance
    }

    private Scene createLoginForm() {
        // create welcome text
        Text welcomeText = new Text();
        welcomeText.setText("Welcome to DepeFood");
        welcomeText.setFont(MainApp.TITLE_FONT);

        // create name input
        Text nameText = new Text();
        nameText.setText("Name: ");

        this.nameInput = new TextField();

        // create phone number input
        Text phoneNumberText = new Text();
        phoneNumberText.setText("Phone Number: ");

        this.phoneNumberInput = new TextField();

        // create login button
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(e -> handleLogin());

        // create grid
        GridPane grid = new GridPane();
        grid.add(welcomeText, 0, 0, 2, 1);
        grid.addRow(1, nameText, nameInput);
        grid.addRow(2, phoneNumberText, phoneNumberInput);
        grid.add(loginButton, 1, 3, 1, 1);

        // create scene
        return new Scene(grid, 400, 600);
    }

    private void handleLogin() {
        String nama = this.nameInput.getText();
        String nomorTelepon = this.phoneNumberInput.getText();
        User user = DepeFood.handleLogin(nama, nomorTelepon);

        if (user == null) {
            USER_NOT_FOUND_ALERT.showAndWait();
            return;
        }

        mainApp.login(user);
    }

    public Scene getScene() {
        return this.createLoginForm();
    }
}