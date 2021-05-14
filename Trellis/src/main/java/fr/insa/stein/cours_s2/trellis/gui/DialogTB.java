/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 *
 * @author renaud
 */
public class DialogTB {

    public DialogTB() {
        // Create the custom dialog.
        Dialog<String> res = new Dialog<>();
        res.setTitle("Login Dialog");
        res.setHeaderText("Look, a Custom Login Dialog");

        // Set the button types.
        ButtonType bValider = new ButtonType("Valider", ButtonData.OK_DONE);
        res.getDialogPane().getButtonTypes().addAll(bValider, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Cout:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Longueur min:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("Longueur max:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("Resisitance à la traction:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("Resisitance à la compression:"), 0, 1);
        grid.add(password, 1, 1);


// Enable/Disable login button depending on whether a username was entered.
Node loginButton = res.getDialogPane().lookupButton(bValider);
loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
username.textProperty().addListener((observable, oldValue, newValue) -> {
    loginButton.setDisable(newValue.trim().isEmpty());
});

res.getDialogPane().setContent(grid);

// Request focus on the username field by default.
Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
res.setResultConverter(dialogButton -> {
    if (dialogButton == bValider) {
        return new Pair<>(username.getText(), password.getText());
    }
    return null;
});

Optional<Pair<String, String>> result = res.showAndWait();

result.ifPresent(usernamePassword -> {
    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
});
    }
    
}
