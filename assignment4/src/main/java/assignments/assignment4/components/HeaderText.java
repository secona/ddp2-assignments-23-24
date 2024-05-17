package assignments.assignment4.components;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HeaderText extends Text {
  public static Font font = Font.font("Arial", FontWeight.BOLD, 30);

  public HeaderText(String text) {
    super();
    this.setText(text);
    this.setFont(HeaderText.font);
  }
}
