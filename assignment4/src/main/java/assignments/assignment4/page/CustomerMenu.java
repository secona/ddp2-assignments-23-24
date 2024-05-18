package assignments.assignment4.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;
import assignments.assignment4.components.HeaderText;
import assignments.assignment4.components.form.BayarBillForm;
import assignments.assignment4.components.form.CekSaldo;
import assignments.assignment4.components.form.TambahPesananForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMenu extends MemberMenu {
    private Stage stage;

    private Scene scene;
    private Scene addOrderScene;
    private Scene printBillScene;
    private Scene payBillScene;
    private Scene cekSaldoScene;

    private MainApp mainApp;
    private User user;

    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private BillPrinter billPrinter; // Instance of BillPrinter

    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.billPrinter = new BillPrinter(stage, mainApp, this.user); // Pass user to BillPrinter constructor
        this.scene = createBaseMenu();
        this.addOrderScene = createTambahPesananForm();
        this.printBillScene = createBillPrinter();
        this.payBillScene = createBayarBillForm();
        this.cekSaldoScene = createCekSaldoScene();
    }

    @Override
    public Scene createBaseMenu() {
        if (this.scene != null)
            return this.scene;

        VBox menuLayout = new VBox(10);
        ObservableList<Node> nodes = menuLayout.getChildren();

        // create text
        Text title = new HeaderText("Customer");

        nodes.add(title);

        // create buat pesanan button
        Button buatPesananButton = new Button();
        buatPesananButton.setText("Buat Pesanan");
        buatPesananButton.setOnAction(e -> {
            mainApp.setScene(this.addOrderScene);
        });

        nodes.add(buatPesananButton);

        // create cetak bill button
        Button cetakBillButton = new Button();
        cetakBillButton.setText("Cetak Bill");
        cetakBillButton.setOnAction(e -> {
            mainApp.setScene(this.printBillScene);
        });

        nodes.add(cetakBillButton);

        // create bayar bill button
        Button bayarBillButton = new Button();
        bayarBillButton.setText("Bayar Bill");
        bayarBillButton.setOnAction(e -> {
            mainApp.setScene(this.payBillScene);
        });

        nodes.add(bayarBillButton);

        // create cek saldo button
        Button cekSaldoButton = new Button();
        cekSaldoButton.setText("Cek Saldo");
        cekSaldoButton.setOnAction(e -> {
            mainApp.setScene(this.cekSaldoScene);
        });

        nodes.add(cekSaldoButton);

        // create logout button
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setOnAction(e -> mainApp.logout());

        nodes.add(logoutButton);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createTambahPesananForm() {
        TambahPesananForm menuLayout = new TambahPesananForm(mainApp);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBillPrinter() {
        // TODO: Implementasikan method ini untuk menampilkan page cetak bill

        return null;
    }

    private Scene createBayarBillForm() {
        BayarBillForm menuLayout = new BayarBillForm(mainApp);
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createCekSaldoScene() {
        CekSaldo menuLayout = new CekSaldo(mainApp, user);
        return new Scene(menuLayout, 400, 600);
    }
}