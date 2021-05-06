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
package fr.insa.stein.cours_s2.trellis.dessin;

import javafx.scene.paint.Color;

/**
 *
 * @author francois
 */
public abstract class FigureSimple extends Figure {

    private Color couleur;

    public FigureSimple(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    @Override
    public void changeCouleur(Color value) {
        this.setCouleur(value);
    }
    
    public static String saveColor(Color c) {
        return c.getRed()+";"+c.getGreen()+";"+c.getBlue();
    }
    
   public static Color parseColor(String sr, String sg, String sb) {
       double rouge = Double.parseDouble(sr);
       double vert = Double.parseDouble(sg);
       double bleu = Double.parseDouble(sb);
       return Color.color(rouge, vert, bleu);
    }


}
