package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
import static fr.insa.stein.cours_s2.trellis.model.ZoneConstructible.alphaToCoordinate;
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
    private int numeroPT;
    private double alpha;
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha, Color col) {
        super(num);
        idTriangle = TT.getId();
        this.numeroPT = numeroPT;
        this.alpha = alpha;
        Point P = alphaToCoordinate(TT, numeroPT, alpha);
        this.setPx(P.getPx());
        this.setPy(P.getPy());
        this.setCouleur(col);
    }
        
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha) {
        this(num, TT, numeroPT, alpha, Color.BLACK);
    }
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, Point P) {
        this(num, TT, numeroPT, 0, P.getCouleur());
        Point P2 = ((Segment) TT.contient.get(numeroPT)).projOrtho(P);
        this.setPx(P2.getPx());
        this.setPy(P2.getPy());
        this.alpha= (P2.getPx()- TT.getPT((numeroPT+1)%3).getPx())/(TT.getPT((numeroPT+1)%3).getPx()- TT.getPT(numeroPT).getPx());
    }

    public int getIdTriangle() {
        return idTriangle;
    }

    public int getNumeroPT() {
        return numeroPT;
    }

    public double getAlpha() {
        return alpha;
    }
}
