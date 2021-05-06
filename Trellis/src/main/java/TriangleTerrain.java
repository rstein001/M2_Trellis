
import fr.insa.stein.cours_s2.trellis.dessin.Figure;
import fr.insa.stein.cours_s2.trellis.dessin.Groupe;
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
import java.util.List;

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
    

    public TriangleTerrain(Numeroteur<TriangleTerrain> num, Point PT0, Point PT1, Point PT2) {
        this.id = num.creeID(this);
        this.PT[0] = PT0;
        this.PT[1] = PT1;
        this.PT[2] = PT2;
        this.add(PT0);
        this.add(PT1);
        this.add(PT2);
        this.add(new Segment(PT0,PT1));
        this.add(new Segment(PT1,PT2));
        this.add(new Segment(PT2,PT0));
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
    
    public int numPoint(Point P){
        double d1= this.getContient().get(3).distancePoint(P);
        double d2= this.getContient().get(4).distancePoint(P);
        double d3= this.getContient().get(5).distancePoint(P);
        if(d1<=d2 && d1<=d3){
            return 0;
        }
        if(d2<=d1 && d2<=d3){
            return 1;
        }
        if(d3<=d2 && d3<=d1){
            return 2;
        }else{
            throw new Error();
        }
    }
}
