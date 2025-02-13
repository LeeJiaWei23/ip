package clippy.gui;

import clippy.Clippy;
import clippy.ui.UI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Clippy clippy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image clippyImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String greeting = UI.getGreeting();
        dialogContainer.getChildren().add(
                DialogBox.getClippyDialog(greeting, clippyImage, "Greeting")
        );
    }

    public void setClippy(Clippy c) {
        clippy = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Clippy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String clippyText = clippy.getResponse(userInput.getText());
        String commandType = clippy.getCommandType();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getClippyDialog(clippyText, clippyImage, commandType));
        userInput.clear();
    }
}
