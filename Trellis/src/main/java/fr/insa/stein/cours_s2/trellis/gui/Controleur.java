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


import fr.insa.stein.cours_s2.trellis.dessin.Figure;
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.model.AppuisDouble;
import fr.insa.stein.cours_s2.trellis.model.Barre;
import fr.insa.stein.cours_s2.trellis.model.Noeud;
import fr.insa.stein.cours_s2.trellis.model.TriangleTerrain;
import fr.insa.stein.cours_s2.trellis.model.ZoneConstructible;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author francois
 */
public class Controleur {

    private MainPane vue;

    private int etat;

    private double[][] pos = new double[3][2];
    private TriangleTerrain TT;

    private List<Figure> selection;
    
    private Numeroteur<TriangleTerrain> numTT;
    private Numeroteur<Noeud> numN;
    private Numeroteur<Barre> numB;

    public Controleur(MainPane vue) {
        this.vue = vue;
        this.selection = new ArrayList<>();
        this.numTT = new Numeroteur<TriangleTerrain>();
        this.numN = new Numeroteur<Noeud>();
        this.numB = new Numeroteur<Barre>();
    }

    public TriangleTerrain getTT() {
        return TT;
    }

    public void setTT(TriangleTerrain TTproche) {
        this.TT = TT;
    }

    public void changeEtat(int nouvelEtat) {
        if (nouvelEtat == 20) {
            this.vue.getRbSelect().setSelected(true);
            this.selection.clear();
            this.vue.redrawAll();
        } else if (nouvelEtat == 30) {
            this.vue.getRbTerrain().setSelected(true);
            this.selection.clear();
            this.vue.getbDeplacer().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 40) {
            this.vue.getRbAppuisDouble().setSelected(true);
            this.selection.clear();
            this.vue.getbDeplacer().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 50) {
            this.vue.getRbAppuisSimple().setSelected(true);
            this.selection.clear();
            this.vue.getbDeplacer().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 60) {
            this.vue.getRbBarre1().setSelected(true);
            this.selection.clear();
            this.vue.getbDeplacer().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        } else if (nouvelEtat == 70) {
            this.vue.getRbBarre2().setSelected(true);
            this.selection.clear();
            this.vue.getbDeplacer().setDisable(true);
            this.vue.getbSupprimer().setDisable(true);
            this.vue.redrawAll();
        }
        this.etat = nouvelEtat;
    }

    void clicDansZoneDessin(MouseEvent t) {
        switch (this.etat) {
            case 20:
                Point pclic = new Point(t.getX(), t.getY());
                // pas de limite de distance entre le clic et l'objet selectionné
                Figure proche = this.vue.getZone().plusProche(pclic, Double.MAX_VALUE);
                // il faut tout de même prévoir le cas ou le groupe est vide
                // donc pas de plus proche
                if (proche != null) {
                    if (t.isShiftDown()) {
                        this.selection.add(proche);
                    } else if (t.isControlDown()) {
                        if (this.selection.contains(proche)) {
                            this.selection.remove(proche);
                        } else {
                            this.selection.add(proche);
                        }
                    } else {
                        this.selection.clear();
                        this.selection.add(proche);
                    }
                    this.activeDeplacerSuivantSelection();
                    this.activeSupprimerSuivantSelection();
                    this.vue.redrawAll();
                }   break;
            case 30:
                {
                    this.pos[0][0] = t.getX();
                    this.pos[0][1] = t.getY();
                    this.changeEtat(31);
                    break;
                }
                    case 31:
                {
                    this.pos[1][0] = t.getX();
                    this.pos[1][1] = t.getY();
                    this.changeEtat(32);
                    break;
                }
                case 32:
                {
                    this.pos[2][0] = t.getX();
                    this.pos[2][1] = t.getY();
                    Point PT0 = new Point(this.pos[0][0], this.pos[0][1]);
                    Point PT1 = new Point(this.pos[1][0], this.pos[1][1]);
                    Point PT2 = new Point(this.pos[2][0], this.pos[2][1]);
                    if(this.vue.getZone().dansLaZone(PT0) && this.vue.getZone().dansLaZone(PT1) && this.vue.getZone().dansLaZone(PT2)){
                        TriangleTerrain TT = new TriangleTerrain(this.numTT, PT0, PT1, PT2, Color.GREEN);
                        this.vue.getZone().add(TT);
                        this.vue.redrawAll();
                    }else{
                        throw new Error("Pas dans la zone constructible");
                       }
                    this.changeEtat(30);
                    break;
                }
                case 40:
                {
                    Point P = new Point(t.getX(), t.getY());
                    TriangleTerrain TTproche = this.vue.getZone().TTplusProche(numTT, P, Double.MAX_VALUE);
                        if (TTproche != null) {
                            this.changeEtat(30);
                            throw new Error("Aucun Triangle Terrain");
                        }else{
                            this.setTT(TTproche);
                            this.changeEtat(41); 
                        }
                    break;
                }
                case 41:
                {
                    Color col = this.vue.getCpCouleur().getValue();
                    AppuisDouble AD = new AppuisDouble(this.numN, this.getTT(), t.getX(), t.getY(), col);
                    this.vue.getZone().add(AD);
                    this.vue.redrawAll();
                    this.changeEtat(40);
                    break;
                }
            default:
                break;
        }
    }

    void boutonSelect(ActionEvent t) {
        this.changeEtat(20);
    }

    void boutonTerrain(ActionEvent t) {
        this.changeEtat(30);
    }

    void boutonAppuisDouble(ActionEvent t) {
        this.changeEtat(40);
    }

    void boutonAppuisSimple(ActionEvent t) {
        this.changeEtat(50);
    }
    
    void boutonBarre1(ActionEvent t) {
        this.changeEtat(60);
    }

    void boutonBarre2(ActionEvent t) {
        this.changeEtat(70);
    }
    
    private void activeDeplacerSuivantSelection() {
        if (this.selection.size() < 1) {
            this.vue.getbDeplacer().setDisable(true);
        } else {
            this.vue.getbDeplacer().setDisable(false);
        }
    }

    private void activeSupprimerSuivantSelection() {
        if (this.selection.size() < 1) {
            this.vue.getbSupprimer().setDisable(true);
        } else {
            this.vue.getbSupprimer().setDisable(false);
        }
    }
    
    
    public List<Figure> getSelection() {
        return selection;
    }

    void boutonDeplacer(ActionEvent t) {
     /*   if (this.etat == 20 && this.selection.size() > 1) {
            // normalement le bouton est disabled dans le cas contraire
            Groupe ssGroupe = this.vue.getModel().sousGroupe(selection);
            this.selection.clear();
            this.selection.add(ssGroupe);
            this.vue.redrawAll();
        }*/
    }

    void boutonSupprimer(ActionEvent t) {
     /*   if (this.etat == 20 && this.selection.size() > 1) {
            // normalement le bouton est disabled dans le cas contraire
            Groupe ssGroupe = this.vue.getModel().sousGroupe(selection);
            this.selection.clear();
            this.selection.add(ssGroupe);
            this.vue.redrawAll();
        }*/
    }
    
    void boutonaddtype(ActionEvent t){
        
    }
    
    void changeColor(Color value) {
        if (this.etat == 20 && this.selection.size() > 0) {
            for (Figure f : this.selection) {
                f.changeCouleur(value);
            }
            this.vue.redrawAll();
        }
    }

    void realSave(File f) {
        try {
            this.vue.getZone().sauvegarde(f);
            this.vue.setCurFile(f);
            this.vue.getInStage().setTitle(f.getName());
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        } finally {
            this.changeEtat(20);
        }
    }

    void menuSave(ActionEvent t) {
        if (this.vue.getCurFile() == null) {
            this.menuSaveAs(t);
        } else {
            this.realSave(this.vue.getCurFile());
        }
    }

    void menuSaveAs(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(this.vue.getInStage());
        if (f != null) {
            this.realSave(f);
        }
    }

    void menuOpen(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(this.vue.getInStage());
        if (f != null) {
            try {
                Figure lue = Figure.lecture(f);
                ZoneConstructible Zlu = (ZoneConstructible) lue;
                Stage nouveau = new Stage();
                nouveau.setTitle(f.getName());
                Scene sc = new Scene(new MainPane(nouveau, f, Zlu), 800, 600);
                nouveau.setScene(sc);
                nouveau.show();
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème durant la sauvegarde");
                alert.setContentText(ex.getLocalizedMessage());

                alert.showAndWait();
            } finally {
                this.changeEtat(20);
            }
        }
    }
//    }

    void menuNouveau(ActionEvent t) {
        Stage nouveau = new Stage();
        nouveau.setTitle("Nouveau");
        Scene sc = new Scene(new MainPane(nouveau), 800, 600);
        nouveau.setScene(sc);
        nouveau.show();
    }

    void menuApropos(ActionEvent t) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("A propos");
        alert.setHeaderText(null);
        alert.setContentText("Trop super ce micro-logiciel de dessin vectoriel 2D\n"
                + "réalisé par François de Bertrand de Beuvron\n"
                + "comme tutoriel pour un cours de POO\n"
                + "à l'INSA de Strasbourg");

        alert.showAndWait();
    }

}
