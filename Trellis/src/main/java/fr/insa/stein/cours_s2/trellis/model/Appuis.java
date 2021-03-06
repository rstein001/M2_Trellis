/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.model;


import javafx.scene.paint.Color;


/**
 *
 * @author renaud
 */
public abstract class Appuis extends Noeud {
    
    private int idTriangle;
    private int numeroPT;
    private double alpha;
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha, Color col) {
        super(num, col);
        idTriangle = TT.getId();
        this.numeroPT = numeroPT;
        this.alpha = alpha;
        this.setPx(alphaToPx(TT, numeroPT, alpha));
        this.setPy(alphaToPy(TT, numeroPT, alpha));
    }
        
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha) {
        this(num, TT, numeroPT, alpha, Color.BLACK);
    }
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double x, double y, Color col) {
        super(num, col);
        idTriangle = TT.getId();
        this.numeroPT = numeroPT;
        this.alpha = (this.getPx()- TT.getPTx((numeroPT+1)%3))/(TT.getPTx(numeroPT)- TT.getPTx((numeroPT+1)%3));
        this.setPx(x);
        this.setPy(y);
    }
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double x, double y) {
        this(num, TT, numeroPT, x, y, Color.BLACK);
    }
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double [] p, Color col) {
        this(num, TT, numeroPT, p[0], p[1], col);
    }
    
    public Appuis(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double [] p) {
        this(num, TT, numeroPT, p[0], p[1], Color.BLACK);
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
    
    public static double alphaToPx(TriangleTerrain TT, int numeroPT, double alpha){
        return TT.getPTx(numeroPT)+(TT.getPTx((numeroPT+1)%3)-TT.getPTx(numeroPT))*alpha;
        //return TT.getPTx(numeroPT) + alpha*TT.getPTx(numeroPT)+(1-alpha)*TT.getPTx((numeroPT+1)%3);
    }
    
    public static double alphaToPy(TriangleTerrain TT, int numeroPT, double alpha){
        return TT.getPTy(numeroPT)+(TT.getPTy((numeroPT+1)%3)-TT.getPTy(numeroPT))*alpha;
        //return TT.getPTy(numeroPT) + alpha*TT.getPTy(numeroPT)+(1-alpha)*TT.getPTy((numeroPT+1)%3);
        
    }
}
