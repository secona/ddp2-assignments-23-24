package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        // setting VBox
        VBox layout = new VBox(30);
        layout.setPadding(new Insets(30, 30, 100, 30));
        layout.setAlignment(Pos.CENTER);

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

        // create a VBox for input
        VBox input = new VBox(5,
                new VBox(nameText, this.nameInput),
                new VBox(phoneNumberText, this.phoneNumberInput));

        // create login button
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setPrefWidth(Double.MAX_VALUE);
        loginButton.setOnAction(e -> handleLogin());

        // add elements
        layout.getChildren().addAll(welcomeText, input, loginButton);

        // create scene
        return new Scene(layout, 400, 600);
    }

    private void handleLogin() {
        // mencoba login
        String nama = this.nameInput.getText();
        String nomorTelepon = this.phoneNumberInput.getText();
        User user = DepeFood.handleLogin(nama, nomorTelepon);

        if (nama.equals("") || nomorTelepon.equals("")) {
            mainApp.alertError("Error!", "Mohon masukkan nama dan nomor telepon!", "");
            return;
        }

        // jika user tidak ditemukan
        if (user == null) {
            // show error
            mainApp.alertError(
                    "Login Failed",
                    "User not found!",
                    String.format("User bernama \"%s\" dengan nomor telepon \"%s\" tidak ditemukan",
                            nama,
                            nomorTelepon));
            return;
        }

        // clear input
        this.nameInput.clear();
        this.phoneNumberInput.clear();

        // jika user ditemukan
        mainApp.login(user);
    }

    public Scene getScene() {
        return this.createLoginForm();
    }
}