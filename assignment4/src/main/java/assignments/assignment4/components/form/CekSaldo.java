package assignments.assignment4.components.form;

import assignments.assignment3.User;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CekSaldo extends VBox {
  public CekSaldo(MainApp mainApp, User user) {
    super(10);
    ObservableList<Node> nodes = this.getChildren();

    // header text
    HeaderText header = new HeaderText("Cek Saldo");
    nodes.add(header);

    // name
    Text nameText = new Text(user.getName());
    nodes.add(nameText);

    // saldo
    Text saldoText = new Text(String.format("Saldo: Rp%d", user.getSaldo()));
    nodes.add(saldoText);

    // back button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    nodes.add(backButton);
  }
}
