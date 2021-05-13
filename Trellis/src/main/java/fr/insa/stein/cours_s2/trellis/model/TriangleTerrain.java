package fr.insa.stein.cours_s2.trellis.model;


import static java.lang.Math.acos;
import javafx.scene.paint.Color;
import recup.Lire;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class TriangleTerrain {
    
    private int id;
    private double[][] PT= new double[3][2];
    private Color col;
    
    public TriangleTerrain(Numeroteur<TriangleTerrain> num, double[][] PT, Color col) {
        this.id = num.creeID(this);
        this.col = col;
        for(int i=0; i<3; i++){
           for(int j=0; j<2; j++){
               this.PT[i][j] = PT[i][j];
            } 
        }
    }

    public TriangleTerrain(Numeroteur<TriangleTerrain> num, double[][] PT) {
        this(num, PT, Color.LIGHTGREEN);
    }

    public int getId() {
        return id;
    }

    public double getPTx(int i) {
        return PT[i][0];
    }
    
    public double getPTy(int i) {
        return PT[i][1];
    }

    public void setPTx(int i, double x) {
        this.PT[i][0] = x;
    }
    
    public void setPTy(int i, double y) {
        this.PT[i][1] = y;
    }

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }
    
    @Override
    public String toString() {
        return  "Triangle Terrain {\n" + "id : "+getId()+" ; (" + this.getPTx(0) + "," + this.getPTy(0) + ") ; (" +
                this.getPTx(1) + "," + this.getPTy(1) + ") ; (" + this.getPTx(2) + "," + this.getPTy(2) + ") \n}";
    }
    
    public static double[][] demandePT(Treillis Z){
        double[][] pt = new double[3][2];
        System.out.println("entrez les coordonées de PT0:");
        double[] p = Z.demandePoint();
        pt[0][0]= p[0];
        pt[0][1]= p[1];
        System.out.println("entrez les coordonées de PT1:");
        p = Z.demandePoint();
        pt[1][0]= p[0];
        pt[1][1]= p[1];
        System.out.println("entrez les coordonées de PT2:");
        p = Z.demandePoint();
        pt[2][0]= p[0];
        pt[2][1]= p[1];
        return pt;
    }
    
    public int choisiPT(){
        System.out.println("choisissez un segment");
        for (int i=0; i <3; i++) {
            int j = (i+1)%3;
            System.out.println( i+1 + ") [ (" + this.getPTx(i) + "," + this.getPTy(i) + ") , (" + 
                    this.getPTx(j) + "," + this.getPTy(j) + ") ]");
        }
        return Lire.i()-1;
    }
    
    /*
    public int SegmentPlusProche(Point P, double dmax){
        double d1= this.getContient().get(0).distancePoint(P);
        double d2= this.getContient().get(1).distancePoint(P);
        double d3= this.getContient().get(2).distancePoint(P);
        if(d1<=d2 && d1<=d3 && d1<=dmax){
            return 0;
        }
        if(d2<=d1 && d2<=d3 && d2<=dmax){
            return 1;
        }
        if(d3<=d2 && d3<=d1 && d3<=dmax){
            return 2;
        }else{
            throw new Error("Trop loin du terrain");
        }
    }
    
    public double Angle(int i1, int i2, Point P){
        Point vec1 = this.getPT(i1).Vecteur(this.getPT(i2));
        Point vec2 = this.getPT(i1).Vecteur(P);
        double scalaire = vec1.Scalaire(vec2);
        return acos(scalaire/(vec1.Norme()*vec2.Norme()));
    }
*/
}
