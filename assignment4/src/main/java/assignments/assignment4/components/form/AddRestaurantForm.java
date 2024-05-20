package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddRestaurantForm extends VBox {
  private TextField restaurantInput;
  private ObservableList<String> restoList;
  private MainApp mainApp;

  private void addRestaurant() {
    try {
      String name = this.restaurantInput.getText();
      String message = DepeFood.handleTambahRestoran(name);
      this.restoList.add(name);

      this.mainApp.showAlert("Success!", message, "", AlertType.INFORMATION);
      this.mainApp.previousScene();
    } catch (Exception ex) {
      this.mainApp.alertError("Error!", ex.getMessage(), "");
    }
  }

  public AddRestaurantForm(MainApp mainApp, ObservableList<String> restoList) {
    super(10);
    this.restoList = restoList;
    this.mainApp = mainApp;
    ObservableList<Node> nodes = this.getChildren();

    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(30, 30, 100, 30));

    // create text
    HeaderText title = new HeaderText("New Restaurant");
    nodes.add(title);

    // create input
    Text text = new Text("Restaurant Name: ");
    this.restaurantInput = new TextField();
    VBox input = new VBox(text, this.restaurantInput);
    nodes.add(input);

    // create submit button
    Button submitButton = new Button("Submit");
    submitButton.setOnAction(e -> addRestaurant());
    submitButton.setPrefWidth(Double.MAX_VALUE);
    nodes.add(submitButton);

    // create back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    backButton.setPrefWidth(Double.MAX_VALUE);
    nodes.add(backButton);
  }
}
