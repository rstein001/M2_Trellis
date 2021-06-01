/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static fr.insa.stein.cours_s2.trellis.model.Treillis.treillisTest2;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Scene sc = new Scene(new MainPane(stage),1400,600);
        stage.setScene(sc);
        stage.setTitle("Nouveau");
          stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}