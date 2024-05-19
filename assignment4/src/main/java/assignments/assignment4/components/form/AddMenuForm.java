package assignments.assignment4.components.form;

import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddMenuForm extends VBox {
  private ChoiceBox<String> restoPicker;
  private TextField menuNameInput;
  private TextField priceInput;

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
      double price = Double.parseDouble(priceInput.getText());
      DepeFood.handleTambahMenuRestoran(restoran, menuName, price);

      System.out.println(restoran.getMenu());
    } catch (Exception ex) {
      System.out.println("Gagal menambahkan menu");
    }
  }

  public AddMenuForm(MainApp mainApp, ObservableList<String> restoNames) {
    super();
    ObservableList<Node> nodes = this.getChildren();

    // create header
    HeaderText header = new HeaderText("Add Menu");

    nodes.add(header);

    // restaurant picker
    this.restoPicker = new ChoiceBox<String>();
    this.restoPicker.getItems().setAll(restoNames);
    
    // add listener for when restoNames changes
    restoNames.addListener((ListChangeListener<String>) c -> {
      // update comboBox items to match with the new values
      this.restoPicker.getItems().setAll(c.getList());
    });

    nodes.add(this.restoPicker);

    // menu name text
    Text menuNameText = new Text("Menu Item Name:");

    nodes.add(menuNameText);

    // menu name input
    this.menuNameInput = new TextField();

    nodes.add(this.menuNameInput);

    // price text
    Text priceText = new Text("Price:");

    nodes.add(priceText);

    // price input
    this.priceInput = new TextField();

    nodes.add(this.priceInput);

    // add menu button
    Button addMenuButton = new Button();
    addMenuButton.setText("Add Menu Button");
    addMenuButton.setOnAction(e -> addMenu());

    nodes.add(addMenuButton);

    // back button
    Button backButton = new Button();
    backButton.setText("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());

    nodes.add(backButton);
  }
}
