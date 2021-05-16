package fr.insa.stein.cours_s2.trellis.model;


import java.util.ArrayList;
import java.util.List;
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
public abstract class Noeud{
    
    public static double RAYON_IN_DRAW = 5;

    private double px;
    private double py;
    private Color col;
    private int id;
    private List<Integer> barre;
    private List<Integer> force;

    public Noeud(Numeroteur<Noeud> num, double x, double y, Color col) {
        this.px = x;
        this.py = y;
        this.col = col;
        this.id = num.creeID(this);
        this.barre = new ArrayList();
        this.force = new ArrayList();
    }
    
    public Noeud(Numeroteur<Noeud> num, double x, double y) {
        this(num, x, y, Color.BLACK);
    }
    
    public Noeud(Numeroteur<Noeud> num, double [] p, Color col) {
        this(num, p[0], p[1], col);
    }
    
    public Noeud(Numeroteur<Noeud> num, double [] p) {
        this(num, p[0], p[1], Color.BLACK);
    }
    
    public Noeud(Numeroteur<Noeud> num, Color col) {
        this(num, 0, 0, col);
    }
    
    public Noeud(Numeroteur<Noeud> num) {
        this(num, 0, 0);
    }

    public double getPx() {
        return px;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double getPy() {
        return py;
    }

    public void setPy(double py) {
        this.py = py;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }
    
    public int getId() {
        return id;
    }

    public List<Integer> getBarreN() {
        return barre;
    }

    public List<Integer> getForceN() {
        return force;
    }
    
}
