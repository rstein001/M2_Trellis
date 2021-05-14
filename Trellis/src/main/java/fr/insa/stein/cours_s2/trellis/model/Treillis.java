package fr.insa.stein.cours_s2.trellis.model;


import static fr.insa.stein.cours_s2.trellis.model.TriangleTerrain.demandePT;
import static fr.insa.stein.cours_s2.trellis.model.TypeBarre.demandeTypeBarre;
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
public class Treillis {
    
    private double Xmax;
    private double Xmin;
    private double Ymax;
    private double Ymin;
    
    public List<Noeud> Noeuds;
    public List<TriangleTerrain> TT;
    public List<Barre> Barres;
    public List<TypeBarre> Catalogue;
    
    private Numeroteur<TriangleTerrain> numTT;
    private Numeroteur<Noeud> numN;
    private Numeroteur<Barre> numB;
    private Numeroteur<TypeBarre> numTB;
    
    public Treillis(double Xmax, double Xmin, double Ymax, double Ymin) {
        this.Xmax = Xmax;
        this.Xmin = Xmin;
        this.Ymax = Ymax;
        this.Ymin = Ymin;
        
        this.Noeuds = new ArrayList<>();
        this.TT = new ArrayList<>();
        this.Barres = new ArrayList<>();
        this.Catalogue = new ArrayList<>();
        
        this.numTT = new Numeroteur<>();
        this.numN = new Numeroteur<>();
        this.numB = new Numeroteur<>();
        this.numTB = new Numeroteur<>();
    }
    
    public Treillis() {
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

    public List<Noeud> getNoeuds() {
        return Noeuds;
    }

    public List<TriangleTerrain> getTT() {
        return TT;
    }

    public List<Barre> getBarres() {
        return Barres;
    }

    public List<TypeBarre> getCatalogue() {
        return Catalogue;
    }

    public Numeroteur<TriangleTerrain> getNumTT() {
        return numTT;
    }

    public Numeroteur<Noeud> getNumN() {
        return numN;
    }

    public Numeroteur<Barre> getNumB() {
        return numB;
    }

    public Numeroteur<TypeBarre> getNumTB() {
        return numTB;
    }
    
    public static String indente(String toIndente, String prefix) {
        return prefix + toIndente.replaceAll("\n", "\n" + prefix);
    }
    
    @Override
    public String toString() {
        String res = "Trellis {\n";
        res = res + "Zone Constructible {\n" + "("+getXmin()+","+getYmin()+") ; "+ "("+getXmax()+","+getYmax()+")"+ "\n";
        for (int i = 1; i <= this.TT.size(); i++) {
            res = res + indente(this.numTT.getObj(i).toString(), "  ") + "\n";
        }
        for (int i = 0; i < this.Catalogue.size(); i++) {
            res = res + indente(this.Catalogue.get(i).toString(), "  ") + "\n";
        }
        for (int i = 1; i <= this.Noeuds.size(); i++) {
            res = res + indente(this.numN.getObj(i).toString(), "  ") + "\n";
        }
        for (int i = 1; i <= this.Barres.size(); i++) {
            res = res + indente(this.numB.getObj(i).toString(), "  ") + "\n";
        }
        return res + "}";
    }
    
    public void demandeZone(){
        System.out.println("entrez Xmin");
        this.setXmin(Lire.d());
        System.out.println("entrez Xmax");
        this.setXmax(Lire.d());
        System.out.println("entrez Ymin");
        this.setYmin(Lire.d());
        System.out.println("entrez Xmax");
        this.setYmax(Lire.d());
    }
    
    /*
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
    */
    
    

    public static Treillis treillisTest(){
        Treillis res = new Treillis();
        double [][] PT1 ={{100,50},{50,100},{50,50}};
        TriangleTerrain TT1= new TriangleTerrain(res.numTT, PT1, Color.GREEN);
        res.TT.add(TT1);
        double [][] PT2 ={{10,20},{10,10},{20,10}};
        TriangleTerrain TT2= new TriangleTerrain(res.numTT, PT2, Color.GREEN);
        res.TT.add(TT2);
        return res;
    }
    public double[] demandePoint(){
        System.out.println("abscisse : ");
        double x= Lire.d();
        System.out.println("ordonnée : ");
        double y= Lire.d();
        if(this.pointDansLaZone(x, y)==false){
            return null;
        }
        double[] p = {x, y};
        return p;
    }
    
    public boolean pointDansLaZone(double x, double y){
        if(x>this.getXmax() || x<this.getXmin() || y>this.getYmax() || y<this.getYmin()){
            return false;
        }else{
            return true;
        }
    }
    
    /*
    public static double distanceAvecPoint(double x, double y, double x1, double y1){
        double dx = x - x1;
        double dy = y - y1;
        return Math.sqrt(dx*dx+dy*dy);
    }
    
    public static double distanceAvecSegment(double x, double y, double x1, double y1, double x2, double y2){
        return 0;
    }
*/
            
    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher le treillis");
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
                    this.demandeZone();
                    break;
                    
                    
                case 3:
                    double[][] pt = demandePT(this);
                    if(pt==null){
                        System.out.println("pas dans la zone constructible");
                        break;
                    }
                    this.TT.add(new TriangleTerrain(this.numTT, pt));
                    break;
                    
                    
                case 4:
                    if(this.TT.isEmpty()){
                        System.out.println("créer d'abord un triangle terrain");
                        break;
                    }
                    System.out.println("choisissez un triangle terrain");
                    for (int i = 1; i <= this.TT.size(); i++) {
                        System.out.println(i + ") " + this.TT.get(i-1).toString());
                    }
                    TriangleTerrain TT1 = this.numTT.getObj(Lire.i());
                    
                    int numeroPT = TT1.choisiPT();
                    
                    System.out.println("entrez alpha : ");
                    double alpha = Lire.d();
                    if(alpha>=0 &&alpha<=1){
                        this.Noeuds.add(new AppuisDouble(numN, TT1, numeroPT, alpha)); 
                    }else{
                        System.out.println("alpha doit être compris entre 0 et 1");
                    }
                    break;
                    
                    
                case 5:
                    if(this.TT.isEmpty()){
                        System.out.println("créer d'abord un triangle terrain");
                        break;
                    }
                    System.out.println("choisissez un triangle terrain");
                    for (int i = 1; i <= this.TT.size(); i++) {
                        System.out.println(i + ") " + this.TT.get(i-1).toString());
                    }
                    TT1 = this.numTT.getObj(Lire.i());
                    
                    numeroPT = TT1.choisiPT();
                    
                    System.out.println("entrez alpha : ");
                    alpha = Lire.d();
                    if(alpha>=0 &&alpha<=1){
                        this.Noeuds.add(new AppuisSimple(numN, TT1, numeroPT, alpha)); 
                    }else{
                        System.out.println("alpha doit être compris entre 0 et 1");
                    }
                    break;
                    
                    
                case 6:
                    System.out.println("entrez les coordonnées : ");
                    double[] p = demandePoint();
                    if(p==null){
                        System.out.println("pas dans la zone constructible");
                        break;
                    }
                    this.Noeuds.add(new NoeudSimple(this.numN, p));
                    break;
                    
                    
                case 7:
                    this.Catalogue.add(demandeTypeBarre(this));   
                    break;
                    
                    
                case 8:
                    if(this.Catalogue.isEmpty()){
                        System.out.println("créer d'abord un type de barre");
                        break;
                    }
                    System.out.println("choisissez un type de barre");
                    for (int i = 1; i <= this.Catalogue.size(); i++) {
                        System.out.println(i + ") " + this.Catalogue.get(i-1).toString());
                    }
                    TypeBarre Type = numTB.getObj(Lire.i());
                    
                    if(this.Noeuds.size()<2){
                        System.out.println("créer d'abord 2 noeuds");
                        break;
                    }
                    System.out.println("choisissez le noeud 1");
                    for (int i = 1; i <= this.Noeuds.size(); i++) {
                        System.out.println(i + ") " + this.Noeuds.get(i-1).toString());
                        }
                    int idN1 = Lire.i();
                    
                    System.out.println("choisissez le noeud  2");
                    for (int i = 1; i <= this.Noeuds.size(); i++) {
                        if(i!= idN1){
                            System.out.println(i + ") " + this.Noeuds.get(i-1).toString());
                        }
                    }
                    this.Barres.add(new Barre(this.numB, Type, idN1, Lire.i()));
                    break;
                    
                    
                case 9:
                    if(this.Catalogue.isEmpty()){
                        System.out.println("créer d'abord un type de barre");
                        break;
                    }
                    System.out.println("choisissez un type de barre");
                    for (int i = 1; i <= this.Catalogue.size(); i++) {
                        System.out.println(i + ") " + this.Catalogue.get(i-1).toString());
                    }
                    Type = numTB.getObj(Lire.i());
                    
                    if(this.Noeuds.isEmpty()){
                        System.out.println("créer d'abord un noeud");
                        break;
                    }
                    System.out.println("choisissez le noeud 1");
                    for (int i = 1; i <= this.Noeuds.size(); i++) {
                        System.out.println(i + ") " + this.Noeuds.get(i-1).toString());
                        }
                    idN1 = Lire.i();
                    
                    System.out.println("entrez les coordonnées du nouveaux noeud : ");
                    p = demandePoint();
                    if(p==null){
                        System.out.println("pas dans la zone constructible");
                        break;
                    }
                    this.Noeuds.add(new NoeudSimple(this.numN, p));
                    
                    this.Barres.add(new Barre(this.numB, Type, idN1, this.numN.getSize()));
                    break;
                default:
                    break;
            }
        }
    }

    public static void testMenu(){
        Treillis Zone = treillisTest();
        Zone.menuTexte();
    }
    
    public static void main(String[] args) {
        testMenu();
    }
}
