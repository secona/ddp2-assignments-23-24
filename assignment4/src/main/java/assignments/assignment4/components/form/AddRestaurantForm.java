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

  /**
   * Method ini digunakan untuk menambahkan restoran baru
   */
  private void addRestaurant() {
    try {
      // mencoba menambahkan restoran
      String name = this.restaurantInput.getText();
      String message = DepeFood.handleTambahRestoran(name);
      this.restoList.add(name);

      // show success jika berhasil
      this.mainApp.showAlert("Success!", message, "", AlertType.INFORMATION);

      // otomatis kembali ke main menu
      this.kembali();
    } catch (Exception ex) {
      this.mainApp.alertError("Error!", ex.getMessage(), "");
    }
  }

  /**
   * Method ini digunakan untuk kembali ke menu admin
   */
  private void kembali() {
    // clear inputs
    this.restaurantInput.clear();

    // kembali ke main menu
    this.mainApp.previousScene();
  }

  public AddRestaurantForm(MainApp mainApp, ObservableList<String> restoList) {
    // setting awal VBox
    super(10);
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(30, 30, 100, 30));
    ObservableList<Node> nodes = this.getChildren();

    this.restoList = restoList;
    this.mainApp = mainApp;

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
