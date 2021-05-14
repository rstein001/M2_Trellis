/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Treillis;
import fr.insa.stein.cours_s2.trellis.model.TypeBarre;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author renaud
 */
public class Dialogue {

    public static void DialogTB(Treillis Trellis) {
        // Create the custom dialog.
        Dialog<List<String>> dialogTB = new Dialog<>();
        dialogTB.setTitle("ajoutez un type de barre");

        // Set the button types.
        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogTB.getDialogPane().getButtonTypes().add(btValider);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfCout = new TextField();
        tfCout.setPromptText("entrez un double");
        TextField tfLmin = new TextField();
        tfLmin.setPromptText("entrez un double");
        TextField tfLmax = new TextField();
        tfLmax.setPromptText("entrez un double");
        TextField tfRtract = new TextField();
        tfRtract.setPromptText("entrez un double");
        TextField tfRcomp = new TextField();
        tfRcomp.setPromptText("entrez un double");

        grid.add(new Label("Cout /m:"), 0, 0);
        grid.add(tfCout, 1, 0);
        grid.add(new Label("Longueur min:"), 0, 1);
        grid.add(tfLmin, 1, 1);
        grid.add(new Label("Longueur max:"), 0, 2);
        grid.add(tfLmax, 1, 2);
        grid.add(new Label("Resisitance à la traction:"), 0, 3);
        grid.add(tfRtract, 1, 3);
        grid.add(new Label("Resisitance à la compression:"), 0, 4);
        grid.add(tfRcomp, 1, 4);


        // Enable/Disable login button depending on whether a username was entered.
        Node bValider = dialogTB.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        tfCout.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogTB.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> tfCout.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialogTB.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfCout.getText());
                List.add(tfLmin.getText());
                List.add(tfLmax.getText());
                List.add(tfRtract.getText());
                List.add(tfRcomp.getText());
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogTB.showAndWait();
        
        if (result.isPresent()) {
            System.out.println("Result: " + result.get());
        }
    }
    
}
