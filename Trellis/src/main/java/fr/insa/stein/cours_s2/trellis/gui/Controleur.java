/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.AppuisDouble;
import fr.insa.stein.cours_s2.trellis.model.AppuisSimple;
import fr.insa.stein.cours_s2.trellis.model.Barre;
import fr.insa.stein.cours_s2.trellis.model.Noeud;
import fr.insa.stein.cours_s2.trellis.model.NoeudSimple;
import fr.insa.stein.cours_s2.trellis.model.Treillis;
import fr.insa.stein.cours_s2.trellis.model.TriangleTerrain;
import fr.insa.stein.cours_s2.trellis.model.TypeBarre;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author renaud
 */
public class Controleur {
    
    private MainPane vue;
    
    public Controleur(MainPane vue) {
        this.vue = vue;
    }
    
    public void boutonTriangleTerrain(){
        Treillis Treillis =this.vue.getTreillis();
        List<String> List = Dialogue.DialogTT(Treillis);
        int i =0;
        while(i<6){
            if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                i++;
            }else{
                System.out.println("erreur double");
                i=6;
            }
        }
        double P1x = Double.parseDouble(List.get(0));
        double P1y = Double.parseDouble(List.get(1));
        double P2x = Double.parseDouble(List.get(2));
        double P2y = Double.parseDouble(List.get(3));
        double P3x = Double.parseDouble(List.get(4));
        double P3y = Double.parseDouble(List.get(5));
        double [][] PT ={{P1x,P1y},{P2x,P2y},{P3x,P3y}};
            
        Treillis.getTT().add(new TriangleTerrain(Treillis.getNumTT(),PT));
        this.vue.redrawAll();
    }
    
    public void boutonNoeudSimple(){
        Treillis Treillis =this.vue.getTreillis();
        Color col = this.vue.getCol();
        List<String> List = Dialogue.DialogNoeud(Treillis);
        int i =0;
        while(i<2){
            if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                i++;
            }else{
                System.out.println("erreur double");
                i=3;
            }
        }   
        double Px = Double.parseDouble(List.get(0));
        double Py = Double.parseDouble(List.get(1));
        Treillis.getNoeuds().add(new NoeudSimple(Treillis.getNumN(),Px,Py, col));
        this.vue.redrawAll();
    }
    
    public void boutonAppuisDouble(){
        Treillis Treillis =this.vue.getTreillis();
        Color col = this.vue.getCol();
        List<String> List = Dialogue.DialogAD(Treillis);
        int i =0;
        while(i<2){
            if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                i++;
            }else{
                System.out.println("erreur double");
                i=2;
            }
        }
        int TT = Integer.parseInt(List.get(0));
        int pt = Integer.parseInt(List.get(1));
        double a = Double.parseDouble(List.get(2));
        Treillis.getNoeuds().add(new AppuisDouble (Treillis.getNumN(),Treillis.getNumTT().getObj(TT), pt ,a, col));
        this.vue.redrawAll();
    }
    
    public void boutonAppuisSimple(){
        Treillis Treillis =this.vue.getTreillis();
        Color col = this.vue.getCol();
        List<String> List = Dialogue.DialogAD(Treillis);
        int i =0;
        while(i<2){
            if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                i++;
            }else{
                System.out.println("erreur double");
                i=2;
            }
        }
        int TT = Integer.parseInt(List.get(0));
        int pt = Integer.parseInt(List.get(1));
        double a = Double.parseDouble(List.get(2));
        Treillis.getNoeuds().add(new AppuisSimple (Treillis.getNumN(),Treillis.getNumTT().getObj(TT), pt ,a, col));
        this.vue.redrawAll();
    }
    
    public void boutonBarre(){
        Treillis Treillis =this.vue.getTreillis();
        Color col = this.vue.getCol();
        List<String> List = Dialogue.DialogN2(Treillis);
        int i =0;
        while(i<3){
            if(List.get(0).matches("[0-9]*")){
                i++;
            }else{
                System.out.println("erreur int");
                i=3;
            }
        }
        int N1 = Integer.parseInt(List.get(0));
        int N2 = Integer.parseInt(List.get(1));
        Treillis.getBarres().add(new Barre(Treillis.getNumB(), Treillis.getCatalogue().get((int)this.vue.getCbType().getValue()-1) ,N1,N2, col));
        this.vue.redrawAll();
    }
    
    public void boutonTypeBarre(){
        Treillis Treillis =this.vue.getTreillis();
        List<String> List = Dialogue.DialogTB(Treillis);
        int i =0;
        while(i<5){
            if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                i++;
            }else{
                System.out.println("erreur double");
                i=5;
            }
        }
        double cout = Double.parseDouble(List.get(0));
        double lmin = Double.parseDouble(List.get(1));
        double lmax = Double.parseDouble(List.get(2));
        double rtract = Double.parseDouble(List.get(3));
        double rcomp = Double.parseDouble(List.get(4));
        Treillis.getCatalogue().add(new TypeBarre(Treillis.getNumTB(),cout,lmin,lmax,rtract,rcomp));
        this.vue.getCbType().getItems().add(Treillis.getCatalogue().size());
    }
    
    public void boutonCalcul(){
        Treillis Treillis =this.vue.getTreillis();
        Stage calculs = new Stage();
        calculs.initModality(Modality.APPLICATION_MODAL);
        List calcul = Treillis.calculs();
        VBox layout = new VBox(); 
        layout.setAlignment(Pos.CENTER);
        Insets Inset = new Insets(5,5,0,5);
        for(int i=0;i<calcul.size(); i++){
            Label label = new Label((String) calcul.get(i));
            layout.setMargin(label, Inset);
            layout.getChildren().add(label);
        }
        Scene scene = new Scene(layout, layout.getPrefWidth(), layout.getPrefHeight());
        calculs.setTitle("Résultats des calculs: ");
        calculs.setScene(scene);
        calculs.show();
    }
    
    public void boutonForce(){
        Treillis Treillis =this.vue.getTreillis();
        List<String> List = Dialogue.DialogForce(Treillis);
            int i =0;
            while(i<3){
            if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                i++;
            }else{
                System.out.println("erreur double");
                i=3;
            }
        }
        int id = Integer.parseInt(List.get(0));
        Noeud N = Treillis.getNumN().getObj(id);
        double Fx=Double.parseDouble(List.get(1));
        double Fy=Double.parseDouble(List.get(2));
        N.setFx(Fx);
        N.setFy(Fy);
    }
    
    private void realSave(File f) {
        try {
            this.vue.getTreillis().sauvegarde(f);
            this.vue.setCurFile(f);
            this.vue.getInStage().setTitle(f.getName());
        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        }
    }

    public void menuSave(ActionEvent t) {
        if (this.vue.getCurFile() == null) {
            this.menuSaveAs(t);
        } else {
            this.realSave(this.vue.getCurFile());
        }
    }

    public void menuSaveAs(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(this.vue.getInStage());
        if (f != null) {
            this.realSave(f);
        }
    }

    public void menuOpen(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(this.vue.getInStage());
        if (f != null) {
            try {
                Treillis lue = new Treillis();
                lue.lecture(f);
                this.vue.setTreillis(lue);
                Stage nouveau = new Stage();
                nouveau.setTitle(f.getName());
                Scene sc = new Scene(new MainPane(nouveau, lue), 1400, 600);
                nouveau.setScene(sc);
                nouveau.show();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème durant la sauvegarde");
                alert.setContentText(ex.getLocalizedMessage());

                alert.showAndWait();
            } 
        }
    }

    public void menuNouveau(ActionEvent t) {
        Stage nouveau = new Stage();
        nouveau.setTitle("Nouveau");
        Scene sc = new Scene(new MainPane(nouveau), 800, 600);
        nouveau.setScene(sc);
        nouveau.show();
    }
}
