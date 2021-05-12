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
public abstract class Noeud extends Point{
    
    private int id;

    public Noeud(Numeroteur<Noeud> num, double x, double y, Color col) {
        super(x, y, col);
        this.id = num.creeID(this);
    }
    
    public Noeud(Numeroteur<Noeud> num, double x, double y) {
        this(num, x, y, Color.BLACK);
    }
    
    public Noeud(Numeroteur<Noeud> num, Point P) {
        this(num, P.getPx(), P.getPy(), P.getCouleur());
    }
    
    public Noeud(Numeroteur<Noeud> num) {
        this(num, new Point());
    }

    public int getId() {
        return id;
    }   
}
