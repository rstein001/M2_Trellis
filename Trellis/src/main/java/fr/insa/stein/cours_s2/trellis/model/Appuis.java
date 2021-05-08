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
public abstract class Appuis extends Noeud {
    
    private int idTriangle;
    private int numPT;
    private double alpha;
    
    public Appuis(ZoneConstructible Zone, Numeroteur<Noeud> num, TriangleTerrain TT, Point P) {
        super(Zone, num, P);
        idTriangle= TT.getId();
        numPT= TT.numPoint(P, 1000);
        alpha= (P.getPx()- TT.getPT((numPT+1)%3).getPx())/(TT.getPT((numPT+1)%3).getPx()- TT.getPT(numPT).getPx());
    }
    
    
    public Appuis(ZoneConstructible Zone, Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y, Color col) {
        this(Zone, num, TT, new Point(X, Y, col));
    }
    
    public Appuis(ZoneConstructible Zone, Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y) {
        this(Zone, num, TT, X, Y, Color.BLACK);
    }

    public int getIdTriangle() {
        return idTriangle;
    }

    public int getNumPT() {
        return numPT;
    }

    public double getAlpha() {
        return alpha;
    }
    
    public Point getCoordinate(TriangleTerrain TT){
        double X= this.getAlpha()*TT.getPT(numPT).getPx()+(1-this.getAlpha()*TT.getPT((numPT+1)%3).getPx());
        double Y= this.getAlpha()*TT.getPT(numPT).getPy()+(1-this.getAlpha()*TT.getPT((numPT+1)%3).getPy());
        return new Point(X,Y);
    }
}
