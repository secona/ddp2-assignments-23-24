package assignments.assignment4.components.form;

import assignments.assignment2.Menu;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewRestaurantsForm extends VBox {
  private ListView<String> listMenu;
  private ChoiceBox<String> restoPicker;

  private void view() {
    String restoName = this.restoPicker.getValue();

    if (restoName == null) {
      System.out.println("Please input a restaurant name");
      return;
    }

    Restaurant restoran = DepeFood.findRestaurant(restoName);

    if (restoran == null) {
      System.out.println("Restaurant not found.");
      return;
    }

    this.listMenu.getItems().clear();
    for (Menu menu : restoran.getMenu()) {
      this.listMenu.getItems().add(String.format(
          "%s - Rp%.0f",
          menu.getNamaMakanan(),
          menu.getHarga()));
    }
  }

  public ViewRestaurantsForm(MainApp mainApp, ObservableList<String> restoNames) {
    super(10);
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(30, 30, 100, 30));
    ObservableList<Node> nodes = this.getChildren();

    // create header
    HeaderText header = new HeaderText("View Menu");
    nodes.add(header);

    // restaurant picker
    Text restoPickerText = new Text("Restaurant");
    this.restoPicker = new ChoiceBox<String>();
    this.restoPicker.setPrefWidth(Double.MAX_VALUE);
    this.restoPicker.getItems().setAll(restoNames);
    this.restoPicker.setOnAction(e -> view());
    nodes.add(new VBox(restoPickerText, this.restoPicker));

    // add listener for when restoNames changes
    restoNames.addListener((ListChangeListener<String>) c -> {
      // update comboBox items to match with the new values
      this.restoPicker.getItems().setAll(c.getList());
    });

    // list menu
    this.listMenu = new ListView<>();
    nodes.add(this.listMenu);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    backButton.setPrefWidth(Double.MAX_VALUE);
    nodes.add(backButton);
  }
}
