package assignments.assignment4.page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import assignments.assignment4.components.form.AddMenuForm;
import assignments.assignment4.components.form.AddRestaurantForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu {
    private Stage stage;
    private User user;

    private Scene baseScene;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;
    
    private MainApp mainApp; // Reference to MainApp instance
    private String[] restoNames;

    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.baseScene = createBaseMenu();
        this.addRestaurantScene = createAddRestaurantForm();
        this.addMenuScene = createAddMenuForm();
        this.viewRestaurantsScene = createViewRestaurantsForm();
    }

    @Override
    protected void refresh() {
        this.addMenuScene = createAddMenuForm();
    }

    @Override
    public Scene createBaseMenu() {
        if (this.baseScene != null) {
            return baseScene;
        }

        VBox menuLayout = new VBox(10);
        ObservableList<Node> nodes = menuLayout.getChildren();

        // create text
        HeaderText title = new HeaderText("Admin");

        nodes.add(title);

        // create tambah restoran button
        Button tambahRestoButton = new Button();
        tambahRestoButton.setText("Tambah Restoran");
        tambahRestoButton.setOnAction(e -> {
            this.refresh();
            mainApp.setScene(this.addRestaurantScene);
        });

        nodes.add(tambahRestoButton);

        // create tambah menu restoran button
        Button tambahMenuRestoButton = new Button();
        tambahMenuRestoButton.setText("Tambah Menu Restoran");
        tambahMenuRestoButton.setOnAction(e -> {
            this.refresh();
            mainApp.setScene(this.addMenuScene);
        });

        nodes.add(tambahMenuRestoButton);

        // create lihat daftar restoran button
        Button lihatMenuButton = new Button();
        lihatMenuButton.setText("Lihat Daftar Restoran");
        lihatMenuButton.setOnAction(e -> System.out.println("Lihat Menu Restoran"));

        nodes.add(lihatMenuButton);

        // create logout button
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setOnAction(e -> mainApp.logout());

        nodes.add(logoutButton);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createAddRestaurantForm() {
        AddRestaurantForm layout = new AddRestaurantForm(mainApp);
        return new Scene(layout, 400, 600);
    }

    private Scene createAddMenuForm() {
        AddMenuForm layout = new AddMenuForm(mainApp);
        return new Scene(layout, 400, 600);
    }

    private Scene createViewRestaurantsForm() {
        // TODO: Implementasikan method ini untuk menampilkan page daftar restoran
        VBox layout = new VBox(10);

        return new Scene(layout, 400, 600);
    }

    private void handleTambahRestoran(String nama) {
        // TODO: Implementasi validasi isian nama Restoran
        String validName = DepeFood.getValidRestaurantName(nama);
        if (true) {

        } else {

        }
    }

    private void handleTambahMenuRestoran(Restaurant restaurant, String itemName, double price) {
        // TODO: Implementasi validasi isian menu Restoran
        if (true) {

        } else {

        }
    }
}
