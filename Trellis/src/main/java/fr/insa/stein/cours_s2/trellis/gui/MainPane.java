/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Treillis;
import java.io.File;
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
    private Controleur controleur;
    private Stage inStage;
    private File curFile;
    private DessinCanvas cDessin;
    private MainMenu menu;
    
    private ColorPicker cpCouleur;
    private Color col;
    
    
    private Button bTerrain;
    private Button bAppuisDouble;
    private Button bAppuisSimple;
    
    private Button bBarre2;
    private Button bNoeud;
    private Button bCalcul;
    private Button bForce;


    private ChoiceBox cbType;
    private Button baddtype;
    private TilePane tilePane;
    private Text t;
    private TextInputDialog at;
    


    public MainPane(Stage inStage, Treillis treillis) {
        this(inStage, null, treillis);
    }
    
    public MainPane(Stage inStage) {
        this(inStage, null, new Treillis());
    }

    public MainPane(Stage inStage, File fromFile, Treillis Treillis) {
        this.inStage = inStage;
        this.Treillis = Treillis;
        this.curFile = fromFile;
        this.controleur = new Controleur(this);

        this.bTerrain = new Button("TriangleTerrain");
        this.bTerrain.setOnAction((t) -> {
            this.getControleur().boutonTriangleTerrain();
        });
        
        this.bNoeud = new Button("Noeud");
        this.bNoeud.setOnAction((t) -> {
            this.getControleur().boutonNoeudSimple();            
        });
        
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
            this.getControleur().boutonAppuisDouble();
        });
        this.bAppuisSimple = new Button("Appuis Simple");
        this.bAppuisSimple.setOnAction((t) -> {
            this.getControleur().boutonAppuisDouble();
        });   
       
        this.bBarre2 = new Button("Barre");
        this.bBarre2.setOnAction((t) -> {
            this.getControleur().boutonBarre();
        });
        
        this.baddtype = new Button("Ajouter un type de barre");
        this.baddtype.setOnAction((ActionEvent t1) -> {
            this.getControleur().boutonTypeBarre();
        });
        
        this.bCalcul = new Button("CALCULS");
        this.bCalcul.setOnAction((ActionEvent t1) -> {
            this.getControleur().boutonCalcul();
        });
        
        this.bForce = new Button("Ajouter une force");
        this.bForce.setOnAction((ActionEvent t1) -> {
            this.getControleur().boutonForce();            
         });
        
        this.t = new Text("Choisir une barre : ");
        
        this.at = new TextInputDialog("");
        this.at.setHeaderText("Entrez des valeurs:");
        this.at.setTitle("Ajoutez un type de barre");
        
        this.cbType = new ChoiceBox<Integer>();
        for (int i=0; i<this.Treillis.getCatalogue().size();i++){
            this.cbType.getItems().add(i+1);
            this.tilePane= new TilePane();
            Scene scene = new Scene (tilePane, 600, 300);
        }
       this.cbType.getSelectionModel().select(0);
        
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.setCol(this.cpCouleur.getValue());
        });
        
        this.menu = new MainMenu(this);
        this.setBottom(this.menu);
        
        HBox hb = new HBox(this.menu, this.bTerrain, this.bAppuisDouble, this.bAppuisSimple,this.bNoeud,
        this.baddtype, this.bBarre2, this.t, this.cbType, this.cpCouleur,this.bForce, this.bCalcul);
        Insets Inset = new Insets(5,5,0,5);
        HBox.setMargin(bTerrain, Inset);
        HBox.setMargin(bAppuisDouble, Inset);
        HBox.setMargin(bAppuisSimple, Inset);
        HBox.setMargin(baddtype, Inset);
        HBox.setMargin(bBarre2, Inset);
        HBox.setMargin(t, Inset);
        HBox.setMargin(cbType, Inset);
        HBox.setMargin(cpCouleur, Inset);
        HBox.setMargin(bNoeud, Inset);
        HBox.setMargin(bCalcul, Inset);
        HBox.setMargin(bForce, Inset);
        
        this.setTop(hb);
       
        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);

    }
    
    public Controleur getControleur() {
        return controleur;
    }
    
    public void redrawAll() {
        this.cDessin.redrawAll();
    }

    public Treillis getTreillis() {
        return Treillis;
    }

    public void setTreillis(Treillis Treillis) {
        this.Treillis = Treillis;
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

    public Button getbForce() {
        return bForce;
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
    
    public File getCurFile() {
        return curFile;
    }
    
    public void setCurFile(File curFile) {
        this.curFile = curFile;
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