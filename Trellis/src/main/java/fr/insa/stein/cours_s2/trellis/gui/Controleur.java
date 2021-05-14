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


import fr.insa.stein.cours_s2.trellis.model.AppuisDouble;
import fr.insa.stein.cours_s2.trellis.model.Treillis;
import fr.insa.stein.cours_s2.trellis.model.TriangleTerrain;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextInputDialog;


public class Controleur {
    
    private MainPane vue;

    private int etat;

    private double[][] pos = new double[3][2];
    private TriangleTerrain TT;
    private int numeroPT;

    public Controleur(MainPane vue) {
        this.vue = vue;
    }

    public TriangleTerrain getTT() {
        return TT;
    }

    public void setTT(TriangleTerrain TTproche) {
        this.TT = TT;
    }

    public int getNumeroPT() {
        return numeroPT;
    }

    public void setNumeroPT(int numeroPT) {
        this.numeroPT = numeroPT;
    }

    void boutonTerrain(ActionEvent t) {
        
    }

    void boutonAppuisDouble(ActionEvent t) {
        
    }

    void boutonAppuisSimple(ActionEvent t) {
        
    }
    
    void boutonBarre1(ActionEvent t) {
        
    }

    void boutonBarre2(ActionEvent t) {
        
    }
    
    void boutonaddtype(ActionEvent t){
        vue.getAt().show();
    }
}
