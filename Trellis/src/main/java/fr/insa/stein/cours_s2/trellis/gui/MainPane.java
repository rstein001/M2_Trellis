/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.AppuisDouble;
import fr.insa.stein.cours_s2.trellis.model.Barre;
import fr.insa.stein.cours_s2.trellis.model.NoeudSimple;
import fr.insa.stein.cours_s2.trellis.model.Treillis;
import fr.insa.stein.cours_s2.trellis.model.TypeBarre;
import fr.insa.stein.cours_s2.trellis.model.TriangleTerrain;
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
import javax.swing.event.ChangeListener;


public class MainPane extends BorderPane {

    private Treillis Treillis;

    private Stage inStage;
    private DessinCanvas cDessin;
    
    private ColorPicker cpCouleur;
    private Color col;
    
    private TriangleTerrain TriangleTerrain;
    
    private Button bTerrain;
    private Button bAppuisDouble;
    private Button bAppuisSimple;
    private Button bBarre1;
    private Button bBarre2;
    private Button bNoeud;

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
            this.redrawAll();
            
        });
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
            System.out.println();
        });
        this.bNoeud = new Button("Noeud");
        this.bNoeud.setOnAction((t) -> {
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
            Treillis.getNoeuds().add(new NoeudSimple(Treillis.getNumN(),Px,Py, getCol()));
            this.redrawAll();
            
            
        });
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
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
            double A = Double.parseDouble(List.get(2));
            
            
            
            this.redrawAll();
        });
        this.bAppuisSimple = new Button("Appuis Simple");
        this.bAppuisSimple.setOnAction((t) -> {
            System.out.println();
        });
        this.bBarre1 = new Button("Barre (1 seul noeud construit)");
        this.bBarre1.setOnAction((t) -> {
            List<String> List = Dialogue.DialogN1(Treillis);
            int i =0;
            while(i<5){
                if(List.get(0).matches("[+-]?\\d*(\\.\\d+)?")){
                    i++;
                }else{
                    System.out.println("erreur double");
                    i=5;
                }
            }
            int N1 = Integer.parseInt(List.get(0));
            int N2 = Integer.parseInt(List.get(1));
            double Px = Double.parseDouble(List.get(2));
            double Py = Double.parseDouble(List.get(3));
            Treillis.getNoeuds().add(new NoeudSimple(Treillis.getNumN(),Px,Py, getCol()));
            
            Treillis.getBarres().add(new Barre(Treillis.getNumB(), Treillis.getCatalogue().get((int)this.cbType.getValue()-1) ,N1,N2, this.getCol()));
            this.redrawAll();
            
            
        });
        this.bBarre2 = new Button("Barre (2 noeuds construits)");
        this.bBarre2.setOnAction((t) -> {
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
            Treillis.getBarres().add(new Barre(Treillis.getNumB(), Treillis.getCatalogue().get((int)this.cbType.getValue()-1) ,N1,N2, this.getCol()));
            this.redrawAll();
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
            this.cbType.getItems().add(this.Treillis.getCatalogue().size());
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
        
        
        HBox hb = new HBox(this.bTerrain, this.bAppuisDouble, this.bAppuisSimple,this.bNoeud,
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
        HBox.setMargin(bNoeud, Inset);
      
        this.setTop(hb);
       
        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);

    }

  public void redrawAll() {
      this.cDessin.redrawAll();
  }

    public Treillis getTreillis() {
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