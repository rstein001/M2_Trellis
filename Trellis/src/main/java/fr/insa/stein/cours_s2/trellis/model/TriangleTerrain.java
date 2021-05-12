package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.dessin.Figure;
import fr.insa.stein.cours_s2.trellis.dessin.Groupe;
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
import static java.lang.Math.acos;
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
public class TriangleTerrain extends Groupe {
    
    private int id;
    private Point[] PT= new Point[3];
    
    public TriangleTerrain(Numeroteur<TriangleTerrain> num, Point PT0, Point PT1, Point PT2, Color col) {
        this.id = num.creeID(this);
        this.PT[0] = PT0;
        this.PT[1] = PT1;
        this.PT[2] = PT2;
        this.add(new Segment(PT0,PT1, col));
        this.add(new Segment(PT1,PT2, col));
        this.add(new Segment(PT2,PT0, col));
    }

    public TriangleTerrain(Numeroteur<TriangleTerrain> num, Point PT0, Point PT1, Point PT2) {
        this(num, PT0, PT1, PT2, Color.LIGHTGREEN);
    }

    public Point getPT(int i) {
        return PT[i];
    }

    public void setPT(Point PT, int i) {
        this.PT[i] = PT;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return  "Triangle Terrain {\n" + "id : "+getId()+" ; "+ PT[0].toString()+" ; "+ PT[1].toString()+" ; "+ PT[2].toString() + "\n}";
    }
    
    
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
}
