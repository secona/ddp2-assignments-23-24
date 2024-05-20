package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import assignments.assignment4.components.HeaderText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BayarBillForm extends VBox {
  private MainApp mainApp;

  private void bayar(String orderId, String opsiPembayaran) {
    System.out.println("Membayar " + orderId + " dengan " + opsiPembayaran);
    try {
      String message = DepeFood.handleBayarBill(orderId, opsiPembayaran);
      mainApp.showAlert("Success!", message, "", AlertType.INFORMATION);
      mainApp.previousScene();
    } catch (Exception ex) {
      mainApp.alertError("Error!", "Error membayar bill", ex.getMessage());
    }
  }

  public BayarBillForm(MainApp mainApp) {
    super(10);
    this.setPadding(new Insets(30, 30, 100, 30));
    this.setAlignment(Pos.CENTER);
    this.mainApp = mainApp;
    ObservableList<Node> nodes = this.getChildren();

    // header
    HeaderText header = new HeaderText("Bayar Bill");
    nodes.add(header);

    // create input for orderid
    Text orderIdText = new Text("Order ID");
    TextField orderIdInput = new TextField();
    nodes.add(new VBox(orderIdText, orderIdInput));

    // opsi pembayaran picker
    Text opsiText = new Text("Metode Pembayaran");
    ComboBox<String> opsiPicker = new ComboBox<String>();
    opsiPicker.setPrefWidth(Double.MAX_VALUE);
    opsiPicker.setItems(FXCollections.observableArrayList("Credit Card", "Debit"));
    nodes.add(new VBox(opsiText, opsiPicker));

    // bayar button
    Button bayarButton = new Button("Bayar");
    bayarButton.setPrefWidth(Double.MAX_VALUE);
    bayarButton.setOnAction(e -> bayar(orderIdInput.getText(), opsiPicker.getValue()));
    nodes.add(bayarButton);

    // kembali button
    Button backButton = new Button("Kembali");
    backButton.setPrefWidth(Double.MAX_VALUE);
    backButton.setOnAction(e -> mainApp.previousScene());
    nodes.add(backButton);
  }
}
