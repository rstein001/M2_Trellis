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

import fr.insa.stein.cours_s2.trellis.dessin.Groupe;
import fr.insa.stein.cours_s2.trellis.model.ZoneConstructible;
import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author francois
 */
public class MainPane extends BorderPane {

    private ZoneConstructible Zone;
    private Controleur controleur;

    private Stage inStage;
    private File curFile;

    private RadioButton rbSelect;
    private RadioButton rbTerrain;
    private RadioButton rbAppuisDouble;
    private RadioButton rbAppuisSimple;
    private RadioButton rbBarre1;
    private RadioButton rbBarre2;

    private Button bDeplacer;
    private Button bSupprimer;
    private ColorPicker cpCouleur;
    private ChoiceBox cbType;
    private Button baddtype;
    
    private DessinCanvas cDessin;

    private MainMenu menu;

    public MainPane(Stage inStage, ZoneConstructible Zone) {
        this(inStage, null, Zone);
    }
    
    public MainPane(Stage inStage) {
        this(inStage, null, new ZoneConstructible());
    }

    public MainPane(Stage inStage, File fromFile, ZoneConstructible Zone) {
        this.inStage = inStage;
        this.curFile = fromFile;
        this.Zone = Zone;
        this.controleur = new Controleur(this);

        this.rbSelect = new RadioButton("Sélection");
        this.rbSelect.setOnAction((t) -> {
            this.controleur.boutonSelect(t);
        });
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
        this.bDeplacer = new Button("Déplacer");
        this.bDeplacer.setOnAction((t) -> {
            this.controleur.boutonDeplacer(t);
        });
        this.cpCouleur = new ColorPicker(Color.BLACK);
        this.cpCouleur.setOnAction((t) -> {
            this.controleur.changeColor(this.cpCouleur.getValue());
        });
        this.bSupprimer = new Button("Supprimer");
        this.bSupprimer.setOnAction((t) -> {
            this.controleur.boutonSupprimer(t);
        });
        this.cbType = new ChoiceBox();
        /*this.cbType.setOnAction((t) -> {
            this.controleur.boutonSupprimer(t);
        });*/
        
        this.baddtype = new Button("Ajouter un type de barre");
        this.baddtype.setOnAction((t) -> {
            this.controleur.boutonaddtype(t);
        });

        ToggleGroup bgEtat = new ToggleGroup();
        this.rbSelect.setToggleGroup(bgEtat);
        this.rbTerrain.setToggleGroup(bgEtat);
        this.rbAppuisDouble.setToggleGroup(bgEtat);
        this.rbAppuisSimple.setToggleGroup(bgEtat);
        this.rbBarre1.setToggleGroup(bgEtat);
        this.rbBarre2.setToggleGroup(bgEtat);
        this.rbTerrain.setSelected(true);

        VBox vbGauche = new VBox(this.rbSelect, this.rbTerrain, this.rbAppuisDouble, this.rbAppuisSimple,
        this.baddtype, this.rbBarre1, this.rbBarre2, this.bDeplacer, this.bSupprimer, this.cpCouleur, this.cbType);
        Insets Inset = new Insets(10,10,0,10);
        VBox.setMargin(rbSelect, Inset);
        VBox.setMargin(rbTerrain, Inset);
        VBox.setMargin(rbAppuisDouble, Inset);
        VBox.setMargin(rbAppuisSimple, Inset);
        VBox.setMargin(baddtype, Inset);
        VBox.setMargin(rbBarre1, Inset);
        VBox.setMargin(rbBarre2, Inset);
        VBox.setMargin(bDeplacer, Inset);
        VBox.setMargin(bSupprimer, Inset);
        VBox.setMargin(cpCouleur, Inset);
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

    public ZoneConstructible getZone() {
        return Zone;
    }

    public Controleur getControleur() {
        return controleur;
    }

    public RadioButton getRbSelect() {
        return rbSelect;
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

    public Button getbDeplacer() {
        return bDeplacer;
    }

    public Button getbSupprimer() {
        return bSupprimer;
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
