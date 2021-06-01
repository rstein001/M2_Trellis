/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.model;

import java.io.IOException;
import java.io.Writer;



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

    

    public double getCout() {
        return cout;
    }

    
    public double getLmin() {
        return Lmin;
    }

    

    public double getLmax() {
        return Lmax;
    }

    

    public double getRtrac() {
        return Rtrac;
    }

    

    public double getRcomp() {
        return Rcomp;
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
