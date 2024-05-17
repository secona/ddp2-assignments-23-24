package assignments.assignment4.components.form;

import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AddRestaurantForm extends VBox {
  private TextField restaurantInput;

  public AddRestaurantForm(MainApp mainApp) {
    super();
    ObservableList<Node> nodes = this.getChildren();

    // create text
    HeaderText title = new HeaderText("New Restaurant");

    nodes.add(title);

    // create text
    Text text = new Text("Restaurant Name: ");

    nodes.add(text);

    // create input
    this.restaurantInput = new TextField();

    nodes.add(this.restaurantInput);

    // create submit button
    Button submitButton = new Button("Submit");

    nodes.add(submitButton);

    // create back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());

    nodes.add(backButton);
  }
}
