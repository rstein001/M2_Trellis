package fr.insa.stein.cours_s2.trellis.model;


import static fr.insa.stein.cours_s2.trellis.model.Treillis.angleHoriz;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
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
    private double fx;
    private double fy;

    public Noeud(Numeroteur<Noeud> num, double x, double y, Color col) {
        this.px = x;
        this.py = y;
        this.col = col;
        this.id = num.creeID(this);
        this.barre = new ArrayList();
        this.fx = 0;
        this.fy = 0;
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

    public double getFx() {
        return fx;
    }

    public void setFx(double fx) {
        this.fx = fx;
    }

    public double getFy() {
        return fy;
    }

    public void setFy(double fy) {
        this.fy = fy;
    }
    
    public double angleBarre(Numeroteur<Noeud> num, int idN2){
        return angleHoriz(this.getPx(), this.getPy(), num.getObj(idN2).getPx(), num.getObj(idN2).getPy());
        
    }  
     public void dessine (GraphicsContext context){
         context.setFill(this.getCol());
         context.fillOval(px, px, px, px);
     }   
    
}
