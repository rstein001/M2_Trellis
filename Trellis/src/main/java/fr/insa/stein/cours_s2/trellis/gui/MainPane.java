/*
Copyright 2000- Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.stein.cours_s2.trellis.gui;

import fr.insa.stein.cours_s2.trellis.model.Treillis;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author francois
 */
public class MainPane extends BorderPane {

    private Treillis Treillis;
    private Controleur controleur;

    private Stage inStage;
    private File curFile;

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
    
    private DessinCanvas cDessin;

    public MainPane(Stage inStage, Treillis Zone) {
        this(inStage, null, Zone);
    }
    
    public MainPane(Stage inStage) {
        this(inStage, null, new Treillis());
    }

    public MainPane(Stage inStage, File fromFile, Treillis Treillis) {
        this.inStage = inStage;
        this.curFile = fromFile;
        this.Treillis = Treillis;
        this.controleur = new Controleur(this);

        this.bTerrain = new Button("TriangleTerrain");
        this.bTerrain.setOnAction((t) -> {
            this.controleur.boutonTerrain(t);
        });
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
            this.controleur.boutonAppuisDouble(t);
        });
        this.bAppuisDouble = new Button("Appuis Double");
        this.bAppuisDouble.setOnAction((t) -> {
            this.controleur.boutonAppuisDouble(t);
        });
        this.bAppuisSimple = new Button("Appuis Simple");
        this.bAppuisSimple.setOnAction((t) -> {
            this.controleur.boutonAppuisSimple(t);
        });
        this.bBarre1 = new Button("Barre (1 Noeud)");
        this.bBarre1.setOnAction((t) -> {
            this.controleur.boutonBarre1(t);
        });
        this.bBarre2 = new Button("Barre (2 Noeud)");
        this.bBarre2.setOnAction((t) -> {
            this.controleur.boutonBarre2(t);
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
        
            
            
        
        
        this.baddtype = new Button("Ajouter un type de barre");
        this.baddtype.setOnAction((t) -> {
            this.controleur.boutonaddtype(t);
            
        });
        
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

    public Controleur getControleur() {
        return controleur;
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

    public File getCurFile() {
        return curFile;
    }

    public void setCurFile(File curFile) {
        this.curFile = curFile;
    }

    public TextInputDialog getAt() {
        return at;
    }

}