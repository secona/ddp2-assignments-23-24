package assignments.assignment4.components.form;

import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TambahPesananForm extends VBox {
  private ObservableList<String> items;
  private ObservableList<String> selectedItems;

  /**
   * Method for when the user press create order
   */
  private void createOrder() {
    System.out.print("Membuat Pesanan: ");
    System.out.println(selectedItems.toString());
  }

  /**
   * Method for when the user changes restaurant
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
    ObservableList<Node> nodes = this.getChildren();

    // create header
    HeaderText header = new HeaderText("Order Food");
    nodes.add(header);

    // restaurant picker
    ComboBox<String> restoPicker = new ComboBox<>();
    restoPicker.getItems().addAll(DepeFood.getRestoNames());
    restoPicker.setOnAction(v -> changeMenu(restoPicker.getValue()));
    nodes.add(restoPicker);

    // date input
    Text dateText = new Text("Date (DD/MM/YYYY)");
    nodes.add(dateText);

    TextField dateInput = new TextField();
    nodes.add(dateInput);

    // menu list
    ListView<String> listMenu = new ListView<>();
    this.items = listMenu.getItems();
    this.selectedItems = listMenu.getSelectionModel().getSelectedItems();
    listMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    nodes.add(listMenu);

    // submit button
    Button submitButton = new Button("Buat Pesanan");
    submitButton.setOnAction(e -> createOrder());
    nodes.add(submitButton);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    nodes.add(backButton);
  }
}
