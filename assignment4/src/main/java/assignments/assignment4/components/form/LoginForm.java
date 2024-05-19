package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;

public class LoginForm {
    @SuppressWarnings("unused")
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
        HeaderText welcomeText = new HeaderText("Welcome to DepeFood");

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
            mainApp.alertError(
                    "Login Failed",
                    "User not found!",
                    String.format("User bernama \"%s\" dengan nomor telepon \"%s\" tidak ditemukan",
                            nama,
                            nomorTelepon));
            return;
        }

        mainApp.login(user);
    }

    public Scene getScene() {
        return this.createLoginForm();
    }
}