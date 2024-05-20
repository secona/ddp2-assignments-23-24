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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TambahPesananForm extends VBox {
  private MainApp mainApp;
  private ObservableList<MenuDisplay> items;
  private ObservableList<MenuDisplay> pickedItems;

  private ComboBox<String> restoPicker;
  private ComboBox<MenuDisplay> menuPicker;
  private TextField dateInput;

  /**
   * Method ini digunakan ketika user ingin membuat pesanan
   */
  private void createOrder(String namaResto, String date) {
    System.out.print("Membuat Pesanan: ");
    System.out.println(this.pickedItems.toString());

    if (pickedItems.size() == 0) {
      this.mainApp.alertError("Error!", "Mohon tambahkan paling tidak satu menu!", "");
      return;
    }

    try {
      String orderId = DepeFood.handleBuatPesanan(
          namaResto,
          date,
          pickedItems.size(),
          pickedItems.stream().map(e -> e.menu.getNamaMakanan()).toList());

      mainApp.showAlert("Success!", "Order dengan ID " + orderId + " berhasil ditambahkan", "", AlertType.INFORMATION);
      this.kembali();
    } catch (Exception ex) {
      mainApp.alertError("Error!", "Error Membuat Order", ex.getMessage());
    }
  }

  /**
   * Method ini digunakan ketika user ingin mengganti restoran
   * 
   * @param namaResto the restaurant name
   */
  private void changeMenu(String namaResto) {
    this.items.clear();

    Restaurant resto = DepeFood.getRestaurantByName(namaResto);

    if (resto == null) {
      return;
    }

    for (Menu menu : resto.getMenu()) {
      this.items.add(new MenuDisplay(menu));
    }

    System.out.print("Menampilkan Restaurant: ");
    System.out.println(namaResto);
  }

  /**
   * Menambahkan menu ke daftar menu yang dipilih user
   */
  private void addToPicked(MenuDisplay menu) {
    if (menu != null) {
      this.pickedItems.add(menu);
    }
  }

  /**
   * Menghilangkan menu dari daftar menu yang dipilih user
   */
  private void removeFromPicked(MenuDisplay menu) {
    this.pickedItems.remove(menu);
  }

  /**
   * Method ini digunakan ketika user ingin kembali ke main menu
   */
  private void kembali() {
    // clear input
    this.restoPicker.valueProperty().set(null);
    this.menuPicker.valueProperty().set(null);
    this.pickedItems.clear();
    this.dateInput.clear();

    // kembali ke scene sebelumnya
    this.mainApp.previousScene();
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
    this.restoPicker = new ComboBox<>();
    this.restoPicker.setPrefWidth(Double.MAX_VALUE);
    this.restoPicker.getItems().addAll(DepeFood.getRestoNames());
    this.restoPicker.setOnAction(v -> changeMenu(restoPicker.getValue()));
    nodes.add(new VBox(restoText, this.restoPicker));

    // date input
    Text dateText = new Text("Date (DD/MM/YYYY)");
    this.dateInput = new TextField();
    nodes.add(new VBox(dateText, this.dateInput));

    // menu picker
    Text menuText = new Text("Select a Menu");
    this.menuPicker = new ComboBox<>();
    this.menuPicker.setPrefWidth(Double.MAX_VALUE);
    this.items = this.menuPicker.getItems();
    nodes.add(new VBox(menuText, this.menuPicker));

    // menu button add
    Button addMenuButton = new Button("Tambahkan");
    addMenuButton.setPrefWidth(Double.MAX_VALUE);
    addMenuButton.setOnAction(e -> addToPicked(menuPicker.getValue()));
    nodes.add(addMenuButton);

    // menu list yang dipilih oleh user
    ListView<MenuDisplay> pickedList = new ListView<>();
    this.pickedItems = pickedList.getItems();
    nodes.add(pickedList);

    // remove button
    Button removeButton = new Button("Remove Selected");
    removeButton.setPrefWidth(Double.MAX_VALUE);
    removeButton.setOnAction(e -> removeFromPicked(pickedList.getSelectionModel().getSelectedItem()));
    nodes.add(removeButton);

    // submit button
    Button submitButton = new Button("Buat Pesanan");
    submitButton.setPrefWidth(Double.MAX_VALUE);
    submitButton.setOnAction(e -> createOrder(restoPicker.getValue(), dateInput.getText()));
    nodes.add(submitButton);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setPrefWidth(Double.MAX_VALUE);
    backButton.setOnAction(e -> kembali());
    nodes.add(backButton);
  }

  /**
   * Class ini berguna untuk mendisplay menu dengan method toString yang berbeda
   */
  class MenuDisplay {
    Menu menu;

    MenuDisplay(Menu menu) {
      this.menu = menu;
    }

    @Override
    public String toString() {
      return String.format("%s - Rp%.0f", this.menu.getNamaMakanan(), this.menu.getHarga());
    }
  }
}
