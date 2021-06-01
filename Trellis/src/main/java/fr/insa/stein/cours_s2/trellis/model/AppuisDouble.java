/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.model;


import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;



/**
 *
 * @author renaud
 */
public class AppuisDouble extends Appuis {
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha, Color col) {
        super(num, TT, numeroPT, alpha, col);
    }
        
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double alpha) {
        super(num, TT, numeroPT, alpha, Color.BLACK);
    }
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double x, double y, Color col) {
        super(num, TT, numeroPT, x, y, col);
    }
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double x, double y) {
        super(num, TT, numeroPT, x, y);
    }
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double [] p, Color col) {
        super(num, TT, numeroPT, p[0], p[1], col);
    }
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, int numeroPT, double [] p) {
        super(num, TT, numeroPT, p[0], p[1]);
    }
    
    @Override
    public String toString() {
        return  "Appui Double {\n" + "id : "+getId()+" ; idTriangle : "+getIdTriangle()+
                " ; Point TT : "+getNumeroPT()+" ; alpha : "+getAlpha() +" ("+ this.getPx() +","+ this.getPy() 
                +") ; Fx : "+ this.getFx()+" ; Fy : "+ this.getFy() +"\n}";
    }
    
    public void save (Writer w) throws IOException{
        w.append("AppuiDouble;"+this.getId()+";"+this.getIdTriangle()+';'+this.getNumeroPT()+";"+this.getAlpha()+"\n");
    }
    
}

