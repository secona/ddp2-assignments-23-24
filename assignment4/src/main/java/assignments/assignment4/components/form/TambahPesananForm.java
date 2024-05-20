package assignments.assignment4.components.form;

import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TambahPesananForm extends VBox {
  private MainApp mainApp;
  private ObservableList<String> items;
  private ObservableList<String> selectedItems;

  /**
   * Method for when the user press create order
   */
  private void createOrder(String namaResto, String date) {
    System.out.print("Membuat Pesanan: ");
    System.out.println(this.selectedItems.toString());

    try {
      String orderId = DepeFood.handleBuatPesanan(namaResto, date, selectedItems.size(), selectedItems);
      mainApp.showAlert("Success!", "Order dengan ID " + orderId + " berhasil ditambahkan", "", AlertType.INFORMATION);

      System.out.println(DepeFood.findUserOrderById(orderId));
    } catch (Exception ex) {
      mainApp.alertError("Error!", "Error Membuat Order", ex.getMessage());
    }
  }

  /**
   * Method for when the user changes restaurant
   * 
   * @param namaResto the restaurant name
   */
  private void changeMenu(String namaResto) {
    this.items.clear();

    Restaurant resto = DepeFood.getRestaurantByName(namaResto);

    for (Menu menu : resto.getMenu()) {
      this.items.add(menu.getNamaMakanan());
    }

    System.out.print("Menampilkan Restaurant: ");
    System.out.println(namaResto);
  }

  public TambahPesananForm(MainApp mainApp) {
    super(10);
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(30, 30, 100, 30));
    this.mainApp = mainApp;
    ObservableList<Node> nodes = this.getChildren();

    // create header
    HeaderText header = new HeaderText("Order Food");
    nodes.add(header);

    // restaurant picker
    Text restoText = new Text("Restaurant");
    ComboBox<String> restoPicker = new ComboBox<>();
    restoPicker.setPrefWidth(Double.MAX_VALUE);
    restoPicker.getItems().addAll(DepeFood.getRestoNames());
    restoPicker.setOnAction(v -> changeMenu(restoPicker.getValue()));
    nodes.add(new VBox(restoText, restoPicker));

    // date input
    Text dateText = new Text("Date (DD/MM/YYYY)");
    TextField dateInput = new TextField();
    nodes.add(new VBox(dateText, dateInput));

    // menu list
    ListView<String> listMenu = new ListView<>();
    this.items = listMenu.getItems();
    this.selectedItems = listMenu.getSelectionModel().getSelectedItems();
    listMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    nodes.add(listMenu);

    // submit button
    Button submitButton = new Button("Buat Pesanan");
    submitButton.setPrefWidth(Double.MAX_VALUE);
    submitButton.setOnAction(e -> createOrder(restoPicker.getValue(), dateInput.getText()));
    nodes.add(submitButton);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setPrefWidth(Double.MAX_VALUE);
    backButton.setOnAction(e -> mainApp.previousScene());
    nodes.add(backButton);
  }
}
