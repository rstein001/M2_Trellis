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

    private Treillis Trellis;
    private Controleur controleur;

    private Stage inStage;
    private File curFile;

    private RadioButton rbTerrain;
    private RadioButton rbAppuisDouble;
    private RadioButton rbAppuisSimple;
    private RadioButton rbBarre1;
    private RadioButton rbBarre2;

    private ColorPicker cpCouleur;
    private ChoiceBox cbType;
    private Button baddtype;
    private TilePane tilePane;
    private Text t;
    
    private DessinCanvas cDessin;

    private MainMenu menu;

    public MainPane(Stage inStage, Treillis Zone) {
        this(inStage, null, Zone);
    }
    
    public MainPane(Stage inStage) {
        this(inStage, null, new Treillis());
    }

    public MainPane(Stage inStage, File fromFile, Treillis Treillis) {
        this.inStage = inStage;
        this.curFile = fromFile;
        this.Trellis = Treillis;
        this.controleur = new Controleur(this);

        this.rbTerrain = new RadioButton("TriangleTerrain");
        this.rbTerrain.setOnAction((t) -> {
            this.controleur.boutonTerrain(t);
        });
        this.rbAppuisDouble = new RadioButton("Appuis Double");
        this.rbAppuisDouble.setOnAction((t) -> {
            this.controleur.boutonAppuisDouble(t);
        });
        this.rbAppuisDouble = new RadioButton("Appuis Double");
        this.rbAppuisDouble.setOnAction((t) -> {
            this.controleur.boutonAppuisDouble(t);
        });
        this.rbAppuisSimple = new RadioButton("Appuis Simple");
        this.rbAppuisSimple.setOnAction((t) -> {
            this.controleur.boutonAppuisSimple(t);
        });
        this.rbBarre1 = new RadioButton("Barre (1 Noeud)");
        this.rbBarre1.setOnAction((t) -> {
            this.controleur.boutonBarre1(t);
        });
        this.rbBarre2 = new RadioButton("Barre (2 Noeud)");
        this.rbBarre2.setOnAction((t) -> {
            this.controleur.boutonBarre2(t);
        });
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.controleur.changeColor(this.cpCouleur.getValue());
        });
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
        
        

        ToggleGroup bgEtat = new ToggleGroup();
        this.rbTerrain.setToggleGroup(bgEtat);
        this.rbAppuisDouble.setToggleGroup(bgEtat);
        this.rbAppuisSimple.setToggleGroup(bgEtat);
        this.rbBarre1.setToggleGroup(bgEtat);
        this.rbBarre2.setToggleGroup(bgEtat);
        this.rbTerrain.setSelected(true);

        VBox vbGauche = new VBox(this.rbTerrain, this.rbAppuisDouble, this.rbAppuisSimple,
        this.baddtype, this.rbBarre1, this.rbBarre2, this.cpCouleur, this.t, this.cbType);
        Insets Inset = new Insets(10,10,0,10);
        VBox.setMargin(rbTerrain, Inset);
        VBox.setMargin(rbAppuisDouble, Inset);
        VBox.setMargin(rbAppuisSimple, Inset);
        VBox.setMargin(baddtype, Inset);
        VBox.setMargin(rbBarre1, Inset);
        VBox.setMargin(rbBarre2, Inset);
        VBox.setMargin(cpCouleur, Inset);
        VBox.setMargin(t, Inset);
        VBox.setMargin(cbType, Inset);
      
        this.setLeft(vbGauche);
       
        this.cDessin = new DessinCanvas(this);
        this.setCenter(this.cDessin);

        this.menu = new MainMenu(this);
        this.setTop(this.menu);

        this.controleur.changeEtat(20);

    }

    public void redrawAll() {
        this.cDessin.redrawAll();
    }

    public Treillis getZone() {
        return Trellis;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public RadioButton getRbTerrain() {
        return rbTerrain;
    }

    public RadioButton getRbAppuisDouble() {
        return rbAppuisDouble;
    }

    public RadioButton getRbAppuisSimple() {
        return rbAppuisSimple;
    }

    public RadioButton getRbBarre1() {
        return rbBarre1;
    }

    public RadioButton getRbBarre2() {
        return rbBarre2;
    }

    public ChoiceBox getCbType() {
        return cbType;
    }

    public Button getBaddtype() {
        return baddtype;
    }

    public MainMenu getMenu() {
        return menu;
    }

    public ColorPicker getCpCouleur() {
        return cpCouleur;
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

}