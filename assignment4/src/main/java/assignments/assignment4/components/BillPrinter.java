package assignments.assignment4.components;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import assignments.assignment2.Order;
import assignments.assignment3.DepeFood;
import assignments.assignment3.User;
import assignments.assignment4.MainApp;

public class BillPrinter {
    @SuppressWarnings("unused")
    private Stage stage;
    private MainApp mainApp;
    private User user;

    public BillPrinter(Stage stage, MainApp mainApp, User user) {
        this.stage = stage;
        this.mainApp = mainApp;
        this.user = user;
    }

    private Scene createBillPrinterForm() {
        VBox layout = new VBox(10);
        ObservableList<Node> nodes = layout.getChildren();

        // header text
        HeaderText header = new HeaderText("Bill");
        nodes.add(header);

        // bill input
        TextField billInput = new TextField();
        nodes.add(billInput);

        // print button
        Button printButton = new Button("Print Bill");
        printButton.setOnAction(e -> printBill(billInput.getText()));
        nodes.add(printButton);

        // back button
        Button backButton = new Button("Kembali");
        backButton.setOnAction(e -> mainApp.previousScene());
        nodes.add(backButton);

        return new Scene(layout, 400, 200);
    }

    private void printBill(String orderId) {
        Order order = DepeFood.getOrderOrNull(orderId);

        if (order == null) {
            mainApp.alertError(
                    "Error!",
                    "Error printing bill.",
                    "Order dengan ID " + orderId + " tidak ditemukan.");
        }

        VBox layout = new VBox(10);
        ObservableList<Node> nodes = layout.getChildren();

        // bill
        Text bill = new Text();
        bill.setText(order.toString(user.getLokasi()));
        nodes.add(bill);

        // back button
        Button backButton = new Button("Kembali");
        backButton.setOnAction(e -> mainApp.previousScene());
        nodes.add(backButton);

        Scene scene = new Scene(layout, 400, 200);
        mainApp.setScene(scene);
    }

    public Scene getScene() {
        return this.createBillPrinterForm();
    }
}
