package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
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
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, Point P) {
        super(num, P);
        idTriangle= TT.getId();
        numPT= TT.numPoint(P, 1000);
        Point P2 = ((Segment) TT.contient.get(numPT)).projOrtho(P);
        this.setPx(P2.getPx());
        this.setPy(P2.getPy());
        alpha= (P2.getPx()- TT.getPT((numPT+1)%3).getPx())/(TT.getPT((numPT+1)%3).getPx()- TT.getPT(numPT).getPx());
    }
    
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y, Color col) {
        this(num, TT, new Point(X, Y, col));
    }
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y) {
        this(num, TT, X, Y, Color.BLACK);
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
}
