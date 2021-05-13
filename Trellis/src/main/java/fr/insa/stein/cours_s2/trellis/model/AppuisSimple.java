package fr.insa.stein.cours_s2.trellis.model;


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
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double x, double y, Color col) {
        super(num, TT, numeroPT, x, y, col);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double x, double y) {
        super(num, TT, numeroPT, x, y);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double [] p, Color col) {
        super(num, TT, numeroPT, p[0], p[1], col);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double [] p) {
        super(num, TT, numeroPT, p[0], p[1]);
    }
    
    @Override
    public String toString() {
        return  "Appui Simple {\n" + "id : "+getId()+" ; "+ "idTriangle : "+getIdTriangle()+" ; " +
                "Point TT : "+getNumeroPT()+" ; " + "alpha : "+getAlpha() + "\n}";
    }
}
