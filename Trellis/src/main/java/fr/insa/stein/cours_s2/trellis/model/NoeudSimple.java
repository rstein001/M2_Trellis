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
public class NoeudSimple extends Noeud {
   
    public NoeudSimple(Numeroteur<Noeud> num, Point point) {
        super(num, point);
    }
    
    public NoeudSimple(Numeroteur<Noeud> num, double x, double y, Color col) {
        super(num, x, y, col);
    }
   
    public NoeudSimple(Numeroteur<Noeud> num, double x, double y) {
        super(num, x, y);
    }
    
    @Override
    public String toString() {
        return  "Noeud Simple {\n" + "id : "+getId()+" ; ("+ this.getPx() +","+ this.getPy() +")\n}";
    }
}
