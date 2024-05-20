package assignments.assignment4.page;

import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import assignments.assignment4.components.form.AddMenuForm;
import assignments.assignment4.components.form.AddRestaurantForm;
import assignments.assignment4.components.form.ViewRestaurantsForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenu extends MemberMenu {
    @SuppressWarnings("unused")
    private Stage stage;
    @SuppressWarnings("unused")
    private User user;

    @SuppressWarnings("unused")
    private Scene baseScene;
    private Scene addRestaurantScene;
    private Scene addMenuScene;
    private Scene viewRestaurantsScene;

    private MainApp mainApp; // Reference to MainApp instance

    private ObservableList<String> restoNames;

    public AdminMenu(Stage stage, MainApp mainApp, User user) {
        this.restoNames = FXCollections.observableArrayList(DepeFood.getRestoNames());
        System.out.println(this.restoNames);

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
        this.viewRestaurantsScene = createViewRestaurantsForm();
    }

    @Override
    public Scene createBaseMenu() {
        VBox menuLayout = new VBox(10);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(30, 30, 100, 30));
        ObservableList<Node> nodes = menuLayout.getChildren();

        // create text
        HeaderText title = new HeaderText("Admin");
        nodes.add(title);

        // create tambah restoran button
        Button tambahRestoButton = new Button();
        tambahRestoButton.setText("Tambah Restoran");
        tambahRestoButton.setOnAction(e -> mainApp.setScene(this.addRestaurantScene));
        tambahRestoButton.setPrefWidth(Double.MAX_VALUE);
        nodes.add(tambahRestoButton);

        // create tambah menu restoran button
        Button tambahMenuRestoButton = new Button();
        tambahMenuRestoButton.setText("Tambah Menu Restoran");
        tambahMenuRestoButton.setOnAction(e -> mainApp.setScene(this.addMenuScene));
        tambahMenuRestoButton.setPrefWidth(Double.MAX_VALUE);
        nodes.add(tambahMenuRestoButton);

        // create lihat daftar restoran button
        Button lihatMenuButton = new Button();
        lihatMenuButton.setText("Lihat Daftar Restoran");
        lihatMenuButton.setOnAction(e -> mainApp.setScene(this.viewRestaurantsScene));
        lihatMenuButton.setPrefWidth(Double.MAX_VALUE);
        nodes.add(lihatMenuButton);

        // create logout button
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setOnAction(e -> mainApp.logout());
        logoutButton.setPrefWidth(Double.MAX_VALUE);
        nodes.add(logoutButton);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createAddRestaurantForm() {
        AddRestaurantForm layout = new AddRestaurantForm(mainApp, restoNames);
        return new Scene(layout, 400, 600);
    }

    private Scene createAddMenuForm() {
        AddMenuForm layout = new AddMenuForm(mainApp, restoNames);
        return new Scene(layout, 400, 600);
    }

    private Scene createViewRestaurantsForm() {
        ViewRestaurantsForm layout = new ViewRestaurantsForm(mainApp, restoNames);
        return new Scene(layout, 400, 600);
    }
}
