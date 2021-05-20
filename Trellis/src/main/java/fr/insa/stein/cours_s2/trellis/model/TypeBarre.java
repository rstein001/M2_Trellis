package fr.insa.stein.cours_s2.trellis.model;

import java.io.IOException;
import java.io.Writer;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class TypeBarre {
    
    private int id;
    private double cout;
    private double Lmin;
    private double Lmax;
    private double Rtrac;
    private double Rcomp;

    public TypeBarre(Numeroteur<TypeBarre> num, double cout, double Lmin, double Lmax, double Rtrac, double Rcomp) {
        this.id = num.creeID(this);
        this.cout = cout;
        this.Lmin = Lmin;
        this.Lmax = Lmax;
        this.Rtrac = Rtrac;
        this.Rcomp = Rcomp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public double getLmin() {
        return Lmin;
    }

    public void setLmin(double Lmin) {
        this.Lmin = Lmin;
    }

    public double getLmax() {
        return Lmax;
    }

    public void setLmax(double Lmax) {
        this.Lmax = Lmax;
    }

    public double getRtrac() {
        return Rtrac;
    }

    public void setRtrac(double Rtrac) {
        this.Rtrac = Rtrac;
    }

    public double getRcomp() {
        return Rcomp;
    }

    public void setRcomp(double Rcomp) {
        this.Rcomp = Rcomp;
    }

    @Override
    public String toString() {
        return "TypeBarre{\n" + "id : " + id + " ; cout : " + cout + "; Lmin : " + Lmin + " ; Lmax : " + Lmax +
                " ; Rtrac : " + Rtrac + " ; Rcomp : " + Rcomp + "\n}";
    }
    
    public void save (Writer w) throws IOException{
        w.append("TypeBarre;"+this.getId()+";"+this.getCout()+';'+this.getLmin()+";"+this.Lmax+';'+this.Rtrac+
                ";"+this.Rcomp+"\n");
    }
    
}
