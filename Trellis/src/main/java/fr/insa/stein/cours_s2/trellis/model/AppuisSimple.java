package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class AppuisSimple extends Appuis {
    
    public AppuisSimple(ZoneConstructible Zone, Numeroteur<Noeud> num, TriangleTerrain TT, Point P) {
        super(Zone, num, TT, P);
    }
    
    public AppuisSimple(ZoneConstructible Zone, Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y, Color col) {
        super(Zone, num, TT, X, Y, col);
    }
    
    public AppuisSimple(ZoneConstructible Zone, Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y) {
        super(Zone, num, TT, X, Y);
    }
    
    @Override
    public String toString() {
        return  "Appui Simple {\n" + "id : "+getId()+" ; "+ "idTriangle : "+getIdTriangle()+" ; " +
                "Point TT : "+getNumPT()+" ; " + "alpha : "+getAlpha() + "\n}";
    }
}
