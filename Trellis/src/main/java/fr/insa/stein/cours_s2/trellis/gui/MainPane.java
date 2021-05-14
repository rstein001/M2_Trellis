/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Treillis;
import fr.insa.stein.cours_s2.trellis.model.TypeBarre;
import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainPane extends BorderPane {

    private Treillis Treillis;

    private Stage inStage;
    private DessinCanvas cDessin;
    
    private ColorPicker cpCouleur;
    private Color col;
    
    private Button bTerrain;
    private Button bAppuisDouble;
    private Button bAppuisSimple;
    private Button bBarre1;
    private Button bBarre2;

    private ChoiceBox cbType;
    private Button baddtype;
    private TilePane tilePane;
    private Text t;
    private TextInputDialog at;
    


    public MainPane(Stage inStage, Treillis Zone) {
        this(inStage, null, Zone);
    }
    
    public MainPane(Stage inStage) {
        this(inStage, null, new Treillis());
    }

    public MainPane(Stage inStage, File fromFile, Treillis Treillis) {
        this.inStage = inStage;
        this.Treillis = Treillis;

        this.bTerrain = new Button("TriangleTerrain");
        this.bTerrain.setOnAction((t) -> {
            System.out.println();
        });
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
            System.out.println();
        });
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
            System.out.println();
        });
        this.bAppuisSimple = new Button("Appuis Simple");
        this.bAppuisSimple.setOnAction((t) -> {
            System.out.println();
        });
        this.bBarre1 = new Button("Barre (1 Noeud)");
        this.bBarre1.setOnAction((t) -> {
            System.out.println();
        });
        this.bBarre2 = new Button("Barre (2 Noeud)");
        this.bBarre2.setOnAction((t) -> {
            System.out.println();
        });
        this.baddtype = new Button("Ajouter un type de barre");
        this.baddtype.setOnAction((ActionEvent t1) -> {
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
        });
        
        this.t = new Text("Choisir une barre : ");
        
        this.at = new TextInputDialog("");
        this.at.setHeaderText("Entrez des valeurs:");
        this.at.setTitle("Ajoutez un type de barre");
        
        this.cbType = new ChoiceBox();
        this.cbType.getItems().add("1");
        this.cbType.getItems().add("2");
        this.cbType.getItems().add("3");
        this.tilePane= new TilePane();
        Scene scene = new Scene (tilePane, 300, 300);
        
        
        
        
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.setCol(this.cpCouleur.getValue());
        });
        
        
        HBox hb = new HBox(this.bTerrain, this.bAppuisDouble, this.bAppuisSimple,
        this.baddtype, this.bBarre1, this.bBarre2, this.t, this.cbType, this.cpCouleur);
        Insets Inset = new Insets(5,5,0,5);
        HBox.setMargin(bTerrain, Inset);
        HBox.setMargin(bAppuisDouble, Inset);
        HBox.setMargin(bAppuisSimple, Inset);
        HBox.setMargin(baddtype, Inset);
        HBox.setMargin(bBarre1, Inset);
        HBox.setMargin(bBarre2, Inset);
        HBox.setMargin(t, Inset);
        HBox.setMargin(cbType, Inset);
        HBox.setMargin(cpCouleur, Inset);
      
        this.setTop(hb);
       
        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);

    }
/*
    public void redrawAll() {
        this.cDessin.redrawAll();
    }
*/
    public Treillis getZone() {
        return Treillis;
    }

    public Button getbTerrain() {
        return bTerrain;
    }

    public Button getbAppuisDouble() {
        return bAppuisDouble;
    }

    public Button getbAppuisSimple() {
        return bAppuisSimple;
    }

    public Button getbBarre1() {
        return bBarre1;
    }

    public Button getbBarre2() {
        return bBarre2;
    }

    public ChoiceBox getCbType() {
        return cbType;
    }

    public Button getBaddtype() {
        return baddtype;
    }

    public DessinCanvas getcDessin() {
        return cDessin;
    }

    public Stage getInStage() {
        return inStage;
    }

    public TextInputDialog getAt() {
        return at;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }

}