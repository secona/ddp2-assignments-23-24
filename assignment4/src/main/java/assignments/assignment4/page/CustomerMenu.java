package assignments.assignment4.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.BillPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMenu extends MemberMenu{
    private Stage stage;
    private Scene scene;
    private Scene addOrderScene;
    private Scene printBillScene;
    private Scene payBillScene;
    private Scene cekSaldoScene;
    private BillPrinter billPrinter; // Instance of BillPrinter
    private ComboBox<String> restaurantComboBox = new ComboBox<>();
    private static Label label = new Label();
    private MainApp mainApp;
    private List<Restaurant> restoList = new ArrayList<>();
    private User user;


    public CustomerMenu(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user; // Store the user
        this.scene = createBaseMenu();
        this.addOrderScene = createTambahPesananForm();
        this.billPrinter = new BillPrinter(stage, mainApp, this.user); // Pass user to BillPrinter constructor
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
        Text title = new Text();
        title.setText("Customer");
        title.setFont(MainApp.TITLE_FONT);

        nodes.add(title);

        // create buat pesanan button
        Button buatPesananButton = new Button();
        buatPesananButton.setText("Buat Pesanan");
        buatPesananButton.setOnAction(e -> System.out.println("Buat Pesanan"));

        nodes.add(buatPesananButton);

        // create cetak bill button
        Button cetakBillButton = new Button();
        cetakBillButton.setText("Cetak Bill");
        cetakBillButton.setOnAction(e -> System.out.println("Cetak Bill"));

        nodes.add(cetakBillButton);

        // create bayar bill button
        Button bayarBillButton = new Button();
        bayarBillButton.setText("Bayar Bill");
        bayarBillButton.setOnAction(e -> System.out.println("Bayar Bill"));

        nodes.add(bayarBillButton);

        // create cek saldo button
        Button cekSaldoButton = new Button();
        cekSaldoButton.setText("Cek Saldo");
        cekSaldoButton.setOnAction(e -> System.out.println("Cek Saldo"));

        nodes.add(cekSaldoButton);

        // create logout button
        Button logoutButton = new Button();
        logoutButton.setText("Logout");
        logoutButton.setOnAction(e -> mainApp.logout());

        nodes.add(logoutButton);

        return new Scene(menuLayout, 400, 600);
    }

    private Scene createTambahPesananForm() {
        // TODO: Implementasikan method ini untuk menampilkan page tambah pesanan
        VBox menuLayout = new VBox(10);
    
        return new Scene(menuLayout, 400, 600);
    }

    private Scene createBillPrinter(){
        // TODO: Implementasikan method ini untuk menampilkan page cetak bill

        return null;
    }

    private Scene createBayarBillForm() {
        // TODO: Implementasikan method ini untuk menampilkan page bayar bill
        VBox menuLayout = new VBox(10);

        return new Scene(menuLayout, 400,600);
    }


    private Scene createCekSaldoScene() {
        // TODO: Implementasikan method ini untuk menampilkan page cetak saldo
        VBox menuLayout = new VBox(10);

        return new Scene(menuLayout, 400,600);
    }

    private void handleBuatPesanan(String namaRestoran, String tanggalPemesanan, List<String> menuItems) {
        //TODO: Implementasi validasi isian pesanan
        try {

        } catch (Exception e) {

        }
    }

    private void handleBayarBill(String orderID, int pilihanPembayaran) {
        //TODO: Implementasi validasi pembayaran
        try {

        } catch (Exception e) {

        }
    }
}