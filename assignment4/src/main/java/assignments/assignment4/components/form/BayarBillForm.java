package assignments.assignment4.components.form;

import assignments.assignment3.DepeFood;
import assignments.assignment4.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BayarBillForm extends VBox {
  private void bayar(String orderId, String opsiPembayaran) {
    System.out.println("Membayar " + orderId + " dengan " + opsiPembayaran);
  }

  public BayarBillForm(MainApp mainApp) {
    super(10);
    ObservableList<Node> nodes = this.getChildren();

    // create input for orderid
    TextField orderIdInput = new TextField();
    nodes.add(orderIdInput);

    // opsi pembayaran picker
    ComboBox<String> opsiPicker = new ComboBox<String>();
    opsiPicker.setItems(FXCollections.observableArrayList("Credit Card", "Debit"));
    nodes.add(opsiPicker);

    // bayar button
    Button bayarButton = new Button("Bayar");
    bayarButton.setOnAction(e -> bayar(orderIdInput.getText(), opsiPicker.getValue()));
    nodes.add(bayarButton);

    // kembali button
    Button backButton = new Button("Kembali");
    backButton.setOnAction(e -> mainApp.previousScene());
    nodes.add(backButton);
  }
}
