/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar{
    
    private MainPane main;
    
    public MainMenu(MainPane main) {
        this.main = main;
        Menu file = new Menu("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuNouveau(t);
        });
        MenuItem save = new MenuItem("Sauvegarder");
        save.setOnAction((t) -> {
            this.main.getControleur().menuSave(t);
        });
        MenuItem saveAs = new MenuItem("Sauvegarder sous...");
        saveAs.setOnAction((t) -> {
            this.main.getControleur().menuSaveAs(t);
        });
        MenuItem load = new MenuItem("Ouvrir");
        load.setOnAction((t) -> {
            this.main.getControleur().menuOpen(t);
        });        
        file.getItems().addAll(nouveau,save,saveAs,load);
        
        this.getMenus().add(file);
    }
    
}
