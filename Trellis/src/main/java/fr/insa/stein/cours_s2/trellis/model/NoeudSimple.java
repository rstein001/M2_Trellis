package fr.insa.stein.cours_s2.trellis.model;


import javafx.scene.paint.Color;
import recup.Lire;

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
    
    public NoeudSimple(Numeroteur<Noeud> num, double x, double y, Color col) {
        super(num, x, y, col);
    }
   
    public NoeudSimple(Numeroteur<Noeud> num, double x, double y) {
        super(num, x, y);
    }
    
    public NoeudSimple(Numeroteur<Noeud> num, double []p, Color col) {
        super(num, p, col);
    }
   
    public NoeudSimple(Numeroteur<Noeud> num, double []p) {
        super(num, p);
    }
    
    public NoeudSimple(Numeroteur<Noeud> num) {
        super(num);
    }
    
    @Override
    public String toString() {
        return  "Noeud Simple {\n" + "id : "+getId()+" ; ("+ this.getPx() +","+ this.getPy() 
                +") ; Fx : "+ this.getFx()+" ; Fy : "+ this.getFy()+"\n}";
    }

}
