/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Treillis;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainPane extends BorderPane {

    private Treillis Treillis;

    private Stage inStage;
    private DessinCanvas cDessin;
    
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
        this.bTerrain.setOnAction(() -> {
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
        this.baddtype.setOnAction((t) -> {
            Dialogue.DialogTB(Treillis);
        });
        
        this.at = new TextInputDialog("");
        this.at.setHeaderText("Entrez des valeurs:");
        this.at.setTitle("Ajoutez un type de barre");
        
        this.cbType = new ChoiceBox();
        this.cbType.getItems().add("1");
        this.cbType.getItems().add("2");
        this.cbType.getItems().add("3");
        this.tilePane= new TilePane();
        Scene scene = new Scene (tilePane, 300, 300);
        
            
            
        
        
        
        
        this.t = new Text("Choisir une barre : ");
        
        VBox vbGauche = new VBox(this.bTerrain, this.bAppuisDouble, this.bAppuisSimple,
        this.baddtype, this.bBarre1, this.bBarre2, this.t, this.cbType);
        Insets Inset = new Insets(10,10,0,10);
        VBox.setMargin(bTerrain, Inset);
        VBox.setMargin(bAppuisDouble, Inset);
        VBox.setMargin(bAppuisSimple, Inset);
        VBox.setMargin(baddtype, Inset);
        VBox.setMargin(bBarre1, Inset);
        VBox.setMargin(bBarre2, Inset);
        VBox.setMargin(t, Inset);
        VBox.setMargin(cbType, Inset);
      
        this.setLeft(vbGauche);
       
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

}