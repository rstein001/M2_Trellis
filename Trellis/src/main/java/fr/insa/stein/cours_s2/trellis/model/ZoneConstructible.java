package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.dessin.Figure;
import fr.insa.stein.cours_s2.trellis.dessin.Groupe;
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
import java.util.List;
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
public class ZoneConstructible extends Groupe {
    
    private double Xmax;
    private double Xmin;
    private double Ymax;
    private double Ymin;

    public ZoneConstructible(double Xmax, double Xmin, double Ymax, double Ymin) {
        this.Xmax = Xmax;
        this.Xmin = Xmin;
        this.Ymax = Ymax;
        this.Ymin = Ymin;
    }
    
    public ZoneConstructible() {
        this(2000,0,2000,0);
    }

    public double getXmax() {
        return Xmax;
    }

    public void setXmax(double Xmax) {
        this.Xmax = Xmax;
    }

    public double getXmin() {
        return Xmin;
    }

    public void setXmin(double Xmin) {
        this.Xmin = Xmin;
    }

    public double getYmax() {
        return Ymax;
    }

    public void setYmax(double Ymax) {
        this.Ymax = Ymax;
    }

    public double getYmin() {
        return Ymin;
    }

    public void setYmin(double Ymin) {
        this.Ymin = Ymin;
    }
    
    @Override
    public String toString() {
        String res = "Trellis {\n";
        res = res + "Zone Constructible {\n" + "("+getXmin()+","+getYmin()+") ; "+ "("+getXmax()+","+getYmax()+")"+ "\n";
        for (int i = 0; i < this.contient.size(); i++) {
            if(this.contient.get(i) instanceof TriangleTerrain){
                res = res + indente(this.contient.get(i).toString(), "  ") + "\n";
            }
        }
        for (int i = 0; i < this.contient.size(); i++) {
            if(this.contient.get(i) instanceof TriangleTerrain){
                res = res + indente(this.contient.get(i).toString(), "  ") + "\n";
            }
        }
        for (int i = 0; i < this.contient.size(); i++) {
            if(this.contient.get(i) instanceof Noeud){
                res = res + indente(this.contient.get(i).toString(), "  ") + "\n";
            }
        }
        for (int i = 0; i < this.contient.size(); i++) {
            if(this.contient.get(i) instanceof Barre){
                res = res + indente(this.contient.get(i).toString(), "  ") + "\n";
            }
        }
        return res + "}";
    }
    
    public TriangleTerrain TTplusProche(Numeroteur<TriangleTerrain> num, Point p, double distMax) {
        if (num.getSize()==0){
            return null;
        } else {
            TriangleTerrain fmin = (TriangleTerrain) this.contient.get(0);
            double min = fmin.distancePoint(p);
            for (int i = 1; i < this.contient.size(); i++) {
                if(this.contient.get(i) instanceof TriangleTerrain){
                    TriangleTerrain fcur = (TriangleTerrain) this.contient.get(i);
                    double cur = fcur.distancePoint(p);
                    if ((cur < min) || ((fmin instanceof TriangleTerrain)==false)) {
                       min = cur;
                        fmin = fcur;
                    }
                }
            }
            if (min <= distMax) {
                return fmin;
            } else {
                return null;
            }
        }
    }
    
    public boolean dansLaZone(Figure f){
        if(f.maxX()<=this.getXmax() || f.minX()>=this.getXmin() || f.maxY()<=this.getYmax() || f.minY()>=this.getYmin()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean dansLeTerrain(Point P){
        for (int i = 0; i < this.contient.size(); i++) {
            if(this.contient.get(i) instanceof TriangleTerrain){
                TriangleTerrain T = (TriangleTerrain) this.contient.get(i);
                double angle1 = T.Angle(0, 1, P);
                double angle2 = T.Angle(1, 2, P);
                double angle3 = T.Angle(2, 0, P);
                if((angle1<=0 && angle2<=0 && angle3<=0)||(angle1>=0 && angle2>=0 && angle3>=0)){
                    return true;
                }
            }
        }
        return false;
    }

/*            
    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher le groupe");
            System.out.println("2) ajouter un point");
            System.out.println("3) ajouter un segment avec deux nouveaux points");
            System.out.println("4) ajouter un segment sur deux points existants");
            System.out.println("5) créer un sous-groupe");
            System.out.println("6) afficher le rectangle englobant");
            System.out.println("7) calculer la distance à un point");
            System.out.println("8) retirer des figures du groupe");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 2) {
                Point np = Point.demandePoint();
                this.add(np);
            } else if (rep == 3) {
                Segment ns = Segment.demandeSegment();
                this.add(ns);
            } else if (rep == 4) {
                System.out.println("choisissez le début du segment");
                Point deb = this.choisiPoint();
                if (deb != null) {
                    System.out.println("choisissez la fin du segment");
                    Point fin = this.choisiPoint();
                    Segment ns = new Segment(deb, fin);
                    this.add(ns);
                }
            } else if (rep == 5) {
                List<Figure> select = this.choisiFigures();
                this.sousGroupe(select);
            } else if (rep == 6) {
                System.out.println("maxX = " + this.maxX() + " ; "
                        + "minX = " + this.minX() + "\n"
                        + "maxY = " + this.maxY() + " ; "
                        + "minY = " + this.minY() + "\n");
            } else if (rep == 7) {
                System.out.println("entrez un point :");
                Point p = Point.demandePoint();
                System.out.println("distance : " + this.distancePoint(p));
            } else if (rep == 8) {
                List<Figure> select = this.choisiFigures();
                this.removeAll(select);
            }
        }
    }


    public static void test1() {
        System.out.println("groupe test : \n" + Groupe.groupeTest());
    }

    public static void testMenu() {
        Groupe g = groupeTest();
        g.menuTexte();
    }
*/
}
