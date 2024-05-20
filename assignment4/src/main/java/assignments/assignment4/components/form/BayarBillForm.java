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
  private TextField orderIdInput;
  private ComboBox<String> opsiPicker;
  private MainApp mainApp;

  /**
   * Method ini digunakan untuk membayar bill sesuai dengan input yang ada
   */
  private void bayar() {
    try {
      // mencoba membayar bill
      String orderId = this.orderIdInput.getText();
      String opsiPembayaran = this.opsiPicker.getValue();

      // jika tidak ada opsi pembayaran
      if (opsiPembayaran == null) {
        mainApp.alertError("Error!", "Mohon pilih opsi pembayaran!", "");
      }

      String message = DepeFood.handleBayarBill(orderId, opsiPembayaran);

      // show success jika berhasil
      mainApp.showAlert("Success!", message, "", AlertType.INFORMATION);

      // otomatis kembali ke main menu
      this.kembali();
    } catch (Exception ex) {
      mainApp.alertError("Error!", "Error membayar bill", ex.getMessage());
    }
  }

  /**
   * Method untuk kembali ke main menu
   */
  private void kembali() {
    // clear inputs
    this.orderIdInput.clear();
    this.opsiPicker.valueProperty().set(null);

    // kembali ke main menu
    this.mainApp.previousScene();
  }

  public BayarBillForm(MainApp mainApp) {
    // setting VBox
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
    this.orderIdInput = new TextField();
    nodes.add(new VBox(orderIdText, this.orderIdInput));

    // opsi pembayaran picker
    Text opsiText = new Text("Metode Pembayaran");
    this.opsiPicker = new ComboBox<String>();
    this.opsiPicker.setPrefWidth(Double.MAX_VALUE);
    this.opsiPicker.setItems(FXCollections.observableArrayList("Credit Card", "Debit"));
    nodes.add(new VBox(opsiText, this.opsiPicker));

    // bayar button
    Button bayarButton = new Button("Bayar");
    bayarButton.setPrefWidth(Double.MAX_VALUE);
    bayarButton.setOnAction(e -> bayar());
    nodes.add(bayarButton);

    // kembali button
    Button backButton = new Button("Kembali");
    backButton.setPrefWidth(Double.MAX_VALUE);
    backButton.setOnAction(e -> kembali());
    nodes.add(backButton);
  }
}
