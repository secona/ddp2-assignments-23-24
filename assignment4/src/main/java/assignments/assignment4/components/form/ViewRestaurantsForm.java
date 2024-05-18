package assignments.assignment4.components.form;

import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ViewRestaurantsForm extends VBox {
  private ListView<String> listMenu;
  private ChoiceBox<String> comboBox;

  private void view() {
    String restoName = this.comboBox.getValue();

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
      this.listMenu.getItems().add(menu.toString());
    }
  }

  public ViewRestaurantsForm(MainApp mainApp, ObservableList<String> restoNames) {
    ObservableList<Node> nodes = this.getChildren();

    // create header
    HeaderText header = new HeaderText("View Menu");

    nodes.add(header);

    // restaurant picker
    this.comboBox = new ChoiceBox<String>();
    
    // add listener for when restoNames change values
    restoNames.addListener((ListChangeListener<String>) c -> {
      // update comboBox items to match with the new values
      this.comboBox.getItems().clear();
      for (String name : c.getList())
        this.comboBox.getItems().add(name);
    });

    nodes.add(this.comboBox);

    // view button
    Button viewButton = new Button("Search");
    viewButton.setOnAction(e -> view());

    nodes.add(viewButton);

    // list menu
    this.listMenu = new ListView<>();

    nodes.add(this.listMenu);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());

    nodes.add(backButton);
  }
}
