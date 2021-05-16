package fr.insa.stein.cours_s2.trellis.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class Force {
    
    
    private int id;
    private int idN;
    private double Fx;
    private double Fy;

    public Force(Numeroteur<Force> num, int idN, double Fx, double Fy) {
        this.id = num.creeID(this);
        this.idN = idN;
        this.Fx = Fx;
        this.Fy = Fy;
    }
    
    public Force(Numeroteur<Force> num, int idN, double[] F) {
        this(num, idN, F[0],F[0]);
    }

    public int getIdN() {
        return idN;
    }

    public double getFx() {
        return Fx;
    }

    public void setFx(double Fx) {
        this.Fx = Fx;
    }

    public double getFy() {
        return Fy;
    }

    public void setFy(double Fy) {
        this.Fy = Fy;
    }
    
    
}
