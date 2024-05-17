package assignments.assignment4;

import java.util.HashMap;
import java.util.Map;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.components.form.LoginForm;
import assignments.assignment4.page.AdminMenu;
import assignments.assignment4.page.CustomerMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static Font TITLE_FONT = Font.font("Arial", FontWeight.BOLD, 30);
    public static Font BODY_FONT = Font.font("Arial", 12);

    private Stage window;
    private Map<String, Scene> allScenes = new HashMap<>();
    private Scene currentScene;
    private static User user;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("DepeFood Ordering System");

        // Initialize users
        DepeFood.initUser();

        // Initialize all scenes
        Scene loginScene = new LoginForm(window, this).getScene();

        // Populate all scenes map
        allScenes.put("Login", loginScene);

        // Set the initial scene of the application to the login scene
        setScene(loginScene);
        window.show();
    }

    // Method untuk set user
    public void setUser(User newUser) {
        user = newUser;
    }

    // Method to set a scene
    public void setScene(Scene scene) {
        window.setScene(scene);
        currentScene = scene;
    }

    // Method to get a scene by name
    public Scene getScene(String sceneName) {
        return allScenes.get(sceneName);
    }

    public void addScene(String sceneName, Scene scene) {
        allScenes.put(sceneName, scene);
    }

    // Method to login
    public void login(User user) {
        if (user.getRole().equals("Admin")) {
            Scene scene = new AdminMenu(window, this, user).createBaseMenu();
            setScene(scene);
        } else {
            Scene scene = new CustomerMenu(window, this, user).createBaseMenu();
            setScene(scene);
        }
    }

    // Method to logout
    public void logout() {
        setUser(null); // Clear the current user
        setScene(getScene("Login")); // Switch to the login scene
    }

    // Method to show an alert
    public void showAlert(String title, String header, String content, AlertType c) {
        Alert alert = new Alert(c);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.setResult(ButtonType.OK);

        alert.showAndWait();
    }

    public void alertError(String title, String header, String content) {
        this.showAlert(title, header, content, AlertType.ERROR);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
