/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Treillis;
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

    public static List<String> DialogTB(Treillis Trellis) {
        Dialog<List<String>> dialogTB = new Dialog<>();
        dialogTB.setTitle("ajoutez un type de barre");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogTB.getDialogPane().getButtonTypes().add(btValider);

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


        Node bValider = dialogTB.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfRcomp.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogTB.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfCout.requestFocus());

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
            return result.get();
        }
        return null;
    }
    
    public static List<String> DialogTT (Treillis Trellis) {
        Dialog<List<String>> dialogTT = new Dialog<>();
        dialogTT.setTitle("ajoutez un triangle terrain");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogTT.getDialogPane().getButtonTypes().add(btValider);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfP1x = new TextField();
        tfP1x.setPromptText("entrez un double");
        TextField tfP1y = new TextField();
        tfP1y.setPromptText("entrez un double");
        TextField tfP2x = new TextField();
        tfP2x.setPromptText("entrez un double");
        TextField tfP2y = new TextField();
        tfP2y.setPromptText("entrez un double");
        TextField tfP3x = new TextField();
        tfP3x.setPromptText("entrez un double");
        TextField tfP3y = new TextField();
        tfP3y.setPromptText("entrez un double");

        grid.add(new Label("Premier point en x:"), 0, 0);
        grid.add(tfP1x, 1, 0);
        grid.add(new Label("Premier point en y:"), 0, 1);
        grid.add(tfP1y, 1, 1);
        grid.add(new Label("Deuxieme point en x:"), 0, 2);
        grid.add(tfP2x, 1, 2);
        grid.add(new Label("Deuxieme point en y:"), 0, 3);
        grid.add(tfP2y, 1, 3);
        grid.add(new Label("Troisieme point en x:"), 0, 4);
        grid.add(tfP3x, 1, 4);
        grid.add(new Label("Troisieme point en y:"), 0, 5);
        grid.add(tfP3y, 1, 5);


        Node bValider = dialogTT.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfP3y.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogTT.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfP1x.requestFocus());

        dialogTT.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfP1x.getText());
                List.add(tfP1y.getText());
                List.add(tfP2x.getText());
                List.add(tfP2y.getText());
                List.add(tfP3x.getText());
                List.add(tfP3y.getText());
               
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogTT.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static List<String> DialogNoeud(Treillis Trellis) {
        Dialog<List<String>> dialogNoeud = new Dialog<>();
        dialogNoeud.setTitle("ajoutez un nouveau noeud");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogNoeud.getDialogPane().getButtonTypes().add(btValider);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfP1x = new TextField();
        tfP1x.setPromptText("entrez un double");
        TextField tfP1y = new TextField();
        tfP1y.setPromptText("entrez un double");
       

        grid.add(new Label("Coordonnées en x:"), 0, 0);
        grid.add(tfP1x, 1, 0);
        grid.add(new Label("Coordonnées en y:"), 0, 1);
        grid.add(tfP1y, 1, 1);
        
        Node bValider = dialogNoeud.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfP1y.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogNoeud.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfP1x.requestFocus());

        dialogNoeud.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfP1x.getText());
                List.add(tfP1y.getText());
                
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogNoeud.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static List<String> DialogN1(Treillis Trellis) {
        Dialog<List<String>> dialogN1 = new Dialog<>();
        dialogN1.setTitle("Choisissez vos 2 noeuds");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogN1.getDialogPane().getButtonTypes().add(btValider);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfP2x = new TextField();
        tfP2x.setPromptText("entrez un entier");
        TextField tfP2y = new TextField();
        tfP2y.setPromptText("entrez un double");
        TextField tfP2z = new TextField();
        tfP2z.setPromptText("entrez un double");
       
       

        grid.add(new Label("ID premier noeud:"), 0, 0);
        grid.add(tfP2x, 1, 0);
        
        grid.add(new Label("Coordonnées en x du nouveau noeud :"), 0, 1);
        grid.add(tfP2y, 1, 1);
        grid.add(new Label("Coordonnées en y du nouveau noeud :"), 0, 2);
        grid.add(tfP2z, 1, 2);
        

        Node bValider = dialogN1.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfP2y.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogN1.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfP2x.requestFocus());

        dialogN1.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfP2x.getText());
                List.add(tfP2y.getText());
                List.add(tfP2z.getText());
                
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogN1.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static List<String> DialogN2(Treillis Trellis) {
        Dialog<List<String>> dialogN2 = new Dialog<>();
        dialogN2.setTitle("Choisissez vos 2 noeuds");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogN2.getDialogPane().getButtonTypes().add(btValider);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfP2x = new TextField();
        tfP2x.setPromptText("entrez un double");
        TextField tfP2y = new TextField();
        tfP2y.setPromptText("entrez un double");
       

        grid.add(new Label("ID premier noeud:"), 0, 0);
        grid.add(tfP2x, 1, 0);
        grid.add(new Label("ID deuxième noeud:"), 0, 1);
        grid.add(tfP2y, 1, 1);
        

        Node bValider = dialogN2.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfP2y.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogN2.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfP2x.requestFocus());

        dialogN2.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfP2x.getText());
                List.add(tfP2y.getText());
                
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogN2.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static List<String> DialogAD(Treillis Trellis) {
        Dialog<List<String>> dialogAD = new Dialog<>();
        dialogAD.setTitle("Choisissez votre noeud et son terrain");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogAD.getDialogPane().getButtonTypes().add(btValider);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfTT = new TextField();
        tfTT.setPromptText("entrez un entier");
        TextField tfP2x = new TextField();
        tfP2x.setPromptText("entrez un entier");
        TextField tfP2y = new TextField();
        tfP2y.setPromptText("entrez un double");
        
       
       

        grid.add(new Label("ID du Terrain:"), 0, 0);
        grid.add(tfTT, 1, 0);
        grid.add(new Label("Numéro du premier point :"), 0, 1);
        grid.add(tfP2x, 1, 1);
        grid.add(new Label("Position 0<= α <=1 :"), 0, 2);
        grid.add(tfP2y, 1, 2);
        
        

        Node bValider = dialogAD.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfP2x.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogAD.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfTT.requestFocus());

        dialogAD.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfTT.getText());
                List.add(tfP2x.getText());
                List.add(tfP2y.getText());
                
                
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogAD.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    public static List<String> DialogForce(Treillis Trellis) {
        Dialog<List<String>> dialogForce = new Dialog<>();
        dialogForce.setTitle("ajoutez une Force");

        ButtonType btValider = new ButtonType("Valider", ButtonData.OK_DONE);
        dialogForce.getDialogPane().getButtonTypes().add(btValider);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField tfP1x = new TextField();
        tfP1x.setPromptText("entrez un entier");
        TextField tfP1y = new TextField();
        tfP1y.setPromptText("entrez un double");
        TextField tfP1z = new TextField();
        tfP1y.setPromptText("entrez un double");
       

        grid.add(new Label("ID du noeud:"), 0, 0);
        grid.add(tfP1x, 1, 0);
        grid.add(new Label("Fx:"), 0, 1);
        grid.add(tfP1y, 1, 1);
        grid.add(new Label("Fy:"), 0, 2);
        grid.add(tfP1z, 1, 2);
        

        Node bValider = dialogForce.getDialogPane().lookupButton(btValider);
        bValider.setDisable(true);

        tfP1y.textProperty().addListener((observable, oldValue, newValue) -> {
            bValider.setDisable(newValue.trim().isEmpty());
        });
        

        dialogForce.getDialogPane().setContent(grid);

        Platform.runLater(() -> tfP1x.requestFocus());

        dialogForce.setResultConverter(dialogButton -> {
            if (dialogButton == btValider) {
                List<String> List = new ArrayList<>();
                List.add(tfP1x.getText());
                List.add(tfP1y.getText());
                List.add(tfP1z.getText());
                
                return List;
            }
            return null;
        });

        Optional<List<String>> result = dialogForce.showAndWait();
        
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
}


