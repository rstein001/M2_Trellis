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
    
    public Noeud(ZoneConstructible Zone, Numeroteur<Noeud> num, Point P) {
        if(Zone.dansLaZone(P)==false){
            throw new Error("Pas dans la zone constructible");
        }
        if(Zone.dansLeTerrain(P)){
            throw new Error("Noued dans un Triange Terrain");
        }
        this.setPx(P.getPx());
        this.setPy(P.getPy());
        this.setCouleur(P.getCouleur());
        this.id = num.creeID(this);
    }

    public Noeud(ZoneConstructible Zone, Numeroteur<Noeud> num, double x, double y, Color col) {
        this(Zone, num, new Point(x,y,col));
    }
    
    public Noeud(ZoneConstructible Zone, Numeroteur<Noeud> num, double x, double y) {
        this(Zone, num, x, y, Color.BLACK);
    }

    public int getId() {
        return id;
    }   
}
