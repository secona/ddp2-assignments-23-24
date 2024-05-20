package assignments.assignment4.components.form;

import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CekSaldo extends VBox {
  public CekSaldo(MainApp mainApp, User user) {
    // setting VBox
    super(10);
    this.setAlignment(Pos.CENTER);
    this.setPadding(new Insets(30, 30, 100, 30));
    ObservableList<Node> nodes = this.getChildren();

    // header text
    HeaderText header = new HeaderText("Cek Saldo");
    nodes.add(header);

    // user details
    GridPane grid = new GridPane(5, 0);
    grid.addRow(0, new DetailField("Name"), new Text(user.getName()));
    grid.addRow(1, new DetailField("Saldo"), new Text("Rp" + user.getSaldo()));
    nodes.add(grid);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    nodes.add(backButton);
  }

  /** Class ini digunakan sebagai extension dari Text */
  class DetailField extends Text {
    public static Font FONT = Font.font("Arial", FontWeight.BOLD, 12);

    public DetailField(String text) {
      super(text);
      this.setFont(DetailField.FONT);
    }
  }
}
