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
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha, Color col) {
        super(num, TT, numeroPT, alpha, col);
    }
        
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha) {
        super(num, TT, numeroPT, alpha, Color.BLACK);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, Point P) {
        super(num, TT, numeroPT, P);
    }
    
    @Override
    public String toString() {
        return  "Appui Simple {\n" + "id : "+getId()+" ; "+ "idTriangle : "+getIdTriangle()+" ; " +
                "Point TT : "+getNumeroPT()+" ; " + "alpha : "+getAlpha() + "\n}";
    }
}
