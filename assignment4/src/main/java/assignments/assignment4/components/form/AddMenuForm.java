package assignments.assignment4.components.form;

import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddMenuForm extends VBox {
  private ChoiceBox<String> restoPicker;
  private TextField menuNameInput;
  private TextField priceInput;
  private MainApp mainApp;

  private void addMenu() {
    String restoName = this.restoPicker.getValue();

    if (restoName == null) {
      return;
    }

    Restaurant restoran = DepeFood.findRestaurant(restoName);

    if (restoran == null) {
      System.out.println("Restaurant not found.");
      return;
    }

    try {
      String menuName = menuNameInput.getText();
      String price = priceInput.getText();
      DepeFood.handleTambahMenuRestoran(restoran, menuName, price);

      this.mainApp.showAlert("Success!", "Berhasil menambahkan menu " + menuName, "", AlertType.INFORMATION);
      this.menuNameInput.clear();
      this.priceInput.clear();
    } catch (Exception ex) {
      this.mainApp.alertError("Error!", ex.getMessage(), "");
    }
  }

  public AddMenuForm(MainApp mainApp, ObservableList<String> restoNames) {
    super(10);
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(30, 30, 100, 30));
    ObservableList<Node> nodes = this.getChildren();

    this.mainApp = mainApp;

    // create header
    HeaderText header = new HeaderText("Add Menu");
    nodes.add(header);

    // restaurant picker
    Text restoPickerText = new Text("Restaurant:");
    this.restoPicker = new ChoiceBox<String>();
    this.restoPicker.setPrefWidth(Double.MAX_VALUE);
    this.restoPicker.getItems().setAll(restoNames);
    nodes.add(new VBox(restoPickerText, this.restoPicker));

    // add listener for when restoNames changes
    restoNames.addListener((ListChangeListener<String>) c -> {
      // update comboBox items to match with the new values
      this.restoPicker.getItems().setAll(c.getList());
    });

    // menu name input
    Text menuNameText = new Text("Menu Item Name:");
    this.menuNameInput = new TextField();
    nodes.add(new VBox(menuNameText, this.menuNameInput));

    // price text
    Text priceText = new Text("Price:");
    this.priceInput = new TextField();
    nodes.add(new VBox(priceText, this.priceInput));

    // add menu button
    Button addMenuButton = new Button();
    addMenuButton.setText("Add Menu");
    addMenuButton.setOnAction(e -> addMenu());
    addMenuButton.setPrefWidth(Double.MAX_VALUE);
    nodes.add(addMenuButton);

    // back button
    Button backButton = new Button();
    backButton.setText("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    backButton.setPrefWidth(Double.MAX_VALUE);
    nodes.add(backButton);
  }
}
