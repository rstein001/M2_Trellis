package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.dessin.Figure;
import fr.insa.stein.cours_s2.trellis.dessin.Groupe;
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import static fr.insa.stein.cours_s2.trellis.dessin.Point.demandePoint;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
import java.util.ArrayList;
import java.util.List;
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
public class ZoneConstructible extends Groupe {
    
    private double Xmax;
    private double Xmin;
    private double Ymax;
    private double Ymin;

    public List<TypeBarre> Catalogue;
    
    private Numeroteur<TriangleTerrain> numTT;
    private Numeroteur<Noeud> numN;
    private Numeroteur<Barre> numB;
    private Numeroteur<TypeBarre> numTB;
    
    public ZoneConstructible(double Xmax, double Xmin, double Ymax, double Ymin) {
        this.Xmax = Xmax;
        this.Xmin = Xmin;
        this.Ymax = Ymax;
        this.Ymin = Ymin;
        this.Catalogue = new ArrayList<TypeBarre>();
        this.numTT = new Numeroteur<TriangleTerrain>();
        this.numN = new Numeroteur<Noeud>();
        this.numB = new Numeroteur<Barre>();
        this.numTB = new Numeroteur<TypeBarre>();
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

    public List<TypeBarre> getCatalogue() {
        return Catalogue;
    }

    public void setCatalogue(List<TypeBarre> Catalogue) {
        this.Catalogue = Catalogue;
    }

    public Numeroteur<TriangleTerrain> getNumTT() {
        return numTT;
    }

    public void setNumTT(Numeroteur<TriangleTerrain> numTT) {
        this.numTT = numTT;
    }

    public Numeroteur<Noeud> getNumN() {
        return numN;
    }

    public void setNumN(Numeroteur<Noeud> numN) {
        this.numN = numN;
    }

    public Numeroteur<Barre> getNumB() {
        return numB;
    }

    public void setNumB(Numeroteur<Barre> numB) {
        this.numB = numB;
    }
    
    @Override
    public String toString() {
        String res = "Trellis {\n";
        res = res + "Zone Constructible {\n" + "("+getXmin()+","+getYmin()+") ; "+ "("+getXmax()+","+getYmax()+")"+ "\n";
        for (int i = 1; i <= this.numTT.getSize(); i++) {
            res = res + indente(this.numTT.getObj(i).toString(), "  ") + "\n";
        }
        for (int i = 0; i < this.Catalogue.size(); i++) {
            res = res + indente(this.Catalogue.get(i).toString(), "  ") + "\n";
        }
        for (int i = 1; i <= this.numN.getSize(); i++) {
            res = res + indente(this.numN.getObj(i).toString(), "  ") + "\n";
        }
        for (int i = 1; i <= this.numB.getSize(); i++) {
            res = res + indente(this.numB.getObj(i).toString(), "  ") + "\n";
        }
        return res + "}";
    }
    
    public TriangleTerrain TTplusProche(Numeroteur<TriangleTerrain> numTT, Point p, double distMax) {
        if (numTT.getSize()==0){
            return null;
        } else {
            TriangleTerrain TTmin = (TriangleTerrain) this.contient.get(0);
            double min = TTmin.distancePoint(p);
            for (int i = 1; i < this.contient.size(); i++) {
                if(this.contient.get(i) instanceof TriangleTerrain){
                    TriangleTerrain fcur = (TriangleTerrain) this.contient.get(i);
                    double cur = fcur.distancePoint(p);
                    if ((cur < min) || ((TTmin instanceof TriangleTerrain)==false)) {
                       min = cur;
                        TTmin = fcur;
                    }
                }
            }
            if (min <= distMax) {
                return TTmin;
            } else {
                return null;
            }
        }
    }
    
    public boolean dansLaZone(Figure f){
        if(f.maxX()>this.getXmax() || f.minX()<this.getXmin() || f.maxY()>this.getYmax() || f.minY()<this.getYmin()){
            return false;
        }else{
            return true;
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
    
    public static Point alphaToCoordinate(TriangleTerrain TT, int numeroPT, double alpha){
        double X= alpha*TT.getPT(numeroPT).getPx()+(1-alpha*TT.getPT((numeroPT+1)%3).getPx());
        double Y= alpha*TT.getPT(numeroPT).getPy()+(1-alpha*TT.getPT((numeroPT+1)%3).getPy());
        return new Point(X,Y);
    }

    public static ZoneConstructible trellisTest(){
        ZoneConstructible res = new ZoneConstructible();
        Point PT0 = new Point(100,50);
        Point PT1 = new Point(50,150);
        Point PT2 = new Point(50,50);
        TriangleTerrain TT1= new TriangleTerrain(res.numTT, PT0, PT1, PT2, Color.GREEN);
        res.add(TT1);
        Point PT4 = new Point(10,20);
        Point PT5 = new Point(10,10);
        Point PT6 = new Point(20,10);
        TriangleTerrain TT2= new TriangleTerrain(res.numTT, PT4, PT5, PT6, Color.GREEN);
        res.add(TT2);
        return res;
    }
            
    public void menuTexte() {
        int rep = -1;
        TriangleTerrain TT;
        int numeroPT;
        double alpha;
        Point P;
        TypeBarre Type;
        Noeud N;
        while (rep != 0) {
            System.out.println("1) afficher le trellis");
            System.out.println("2) modifier la zone constructible");
            System.out.println("3) ajouter un triangle de terrain");
            System.out.println("4) ajouter un appuis double");
            System.out.println("5) ajouter un appuis simple");
            System.out.println("6) ajouter un noeud simple");
            System.out.println("7) ajouter un type de barres");
            System.out.println("8) ajouter une barre à partir de deux noeuds");
            System.out.println("9) ajouter une barre à partir d'un noeud");
            System.out.println("10) ajouter une barre avec 2 nouveaux noeuds");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            switch (rep) {
                case 1:
                    System.out.println(this);
                    break;
                case 2:
                    
                    break;
                case 3:
                    System.out.println("entrez le point 1");
                    Point PT0 = demandePoint();
                    System.out.println("entrez le point 2");
                    Point PT1 = demandePoint();
                    System.out.println("entrez le point 3");
                    Point PT2 = demandePoint();
                    if(this.dansLaZone(PT0) && this.dansLaZone(PT1) && this.dansLaZone(PT2)){
                        this.add(new TriangleTerrain(this.numTT, PT0, PT1, PT2));
                    }else{
                        System.out.println("pas dans la zone constructible");
                    }
                    break;
                case 4:
                    System.out.println("choisissez un triangle terrain");
                    if(this.numTT.getSize()==0){
                        for (int i = 1; i <= this.numTT.getSize(); i++) {
                            System.out.println(i + ") " + this.numTT.getObj(i).toString());
                        }
                        TT = numTT.getObj(Lire.i());
                    }else{
                        System.out.println("créer d'abord un triangle terrain");
                        break;
                    }
                    System.out.println("choisissez un segment");
                    for (int i=0; i <3; i++) {
                        System.out.println(i+1 + ") " + TT.contient.get(i).toString());
                    }
                    numeroPT = Lire.i()-1;
                    System.out.println("entrez alpha : ");
                    alpha = Lire.d();
                    if(alpha>=0 &&alpha<=1){
                        this.add(new AppuisDouble(numN, TT, numeroPT, alpha)); 
                    }else{
                        System.out.println("alpha doit être compris entre 0 et 1");
                    }
                    break;
                case 5:
                    System.out.println("choisissez un triangle terrain");
                    for (int i = 1; i <= this.numTT.getSize(); i++) {
                        System.out.println(i + ") " + this.numTT.getObj(i).toString());
                    }
                    TT = numTT.getObj(Lire.i());
                    System.out.println("choisissez un segment");
                    for (int i=0; i <3; i++) {
                        System.out.println(i+1 + ") " + TT.contient.get(i).toString());
                    }
                    numeroPT = Lire.i()-1;
                    System.out.println("entrez alpha : ");
                    alpha = Lire.d();
                    if(alpha>=0 &&alpha<=1){
                        this.add(new AppuisSimple(numN, TT, numeroPT, alpha)); 
                    }else{
                        System.out.println("alpha doit être compris entre 0 et 1");
                    }
                    break;
                case 6:
                    System.out.println("entrez le point");
                    P = demandePoint();
                    if(this.dansLaZone(P)){
                        this.add(new NoeudSimple(this.numN, P));
                    }else{
                        System.out.println("pas dans la zone constructible");
                    }
                    break;
                case 7:
                    System.out.println("entrez le cout/m");
                    double cout = Lire.d();
                    System.out.println("entrez la longueur minimale");
                    double Lmin = Lire.d();
                    System.out.println("entrez la longueur maximale");
                    double Lmax = Lire.d();
                    System.out.println("entrez la résistance maximale à la traction");
                    double Rtract = Lire.d();
                    System.out.println("entrez la résistance maximale à la compression");
                    double Rcomp = Lire.d();
                    this.Catalogue.add(new TypeBarre(this.numTB, cout, Lmin, Lmax, Rtract, Rcomp));     
                    break;
                case 8:
                    System.out.println("choisissez un type de barre");
                    if(this.numTB.getSize()==0){
                        for (int i = 1; i <= this.numTB.getSize(); i++) {
                            System.out.println(i + ") " + this.numTB.getObj(i).toString());
                        }
                        Type = numTB.getObj(Lire.i());
                    }else{
                        System.out.println("créer d'abord un type de barre");
                        break;
                    }
                    System.out.println("choisissez le noeud 1");
                    if(this.numN.getSize()==0){
                        for (int i = 1; i <= this.numN.getSize(); i++) {
                            System.out.println(i + ") " + this.numN.getObj(i).toString());
                        }
                        N = numN.getObj(Lire.i());
                    }else{
                        System.out.println("créer d'abord un noeud");
                        break;
                    }
                    System.out.println("choisissez le noeud  2");
                    for (int i = 1; i <= this.numTB.getSize(); i++) {
                        if(i!= N.getId()){
                            System.out.println(i + ") " + this.numTB.getObj(i).toString());
                        }
                    }
                    this.add(new Barre(this.numB, Type, N, numN.getObj(Lire.i())));
                    break;
                case 9:
                    System.out.println("choisissez un type de barre");
                    if(this.numTB.getSize()==0){
                        for (int i = 1; i <= this.numTB.getSize(); i++) {
                            System.out.println(i + ") " + this.numTB.getObj(i).toString());
                        }
                        Type = numTB.getObj(Lire.i());
                    }else{
                        System.out.println("créer d'abord un type de barre");
                        break;
                    }
                    System.out.println("choisissez le noeud 1");
                    if(this.numN.getSize()==0){
                        for (int i = 1; i <= this.numN.getSize(); i++) {
                            System.out.println(i + ") " + this.numN.getObj(i).toString());
                        }
                        N = numN.getObj(Lire.i());
                    }else{
                        System.out.println("créer d'abord un noeud");
                        break;
                    }
                    System.out.println("choisissez le noeud  2");
                    for (int i = 1; i <= this.numTB.getSize(); i++) {
                        if(i!= N.getId()){
                            System.out.println(i + ") " + this.numTB.getObj(i).toString());
                        }
                    }
                    this.add(new Barre(this.numB, Type, N, numN.getObj(Lire.i())));
                    break;
                default:
                    break;
            }
        }
    }

    public static void testMenu() {
        ZoneConstructible Zone = trellisTest();
        Zone.menuTexte();
    }
    
    public static void main(String[] args) {
        testMenu();
    }
}
