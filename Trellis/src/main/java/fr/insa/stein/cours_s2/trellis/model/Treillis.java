package fr.insa.stein.cours_s2.trellis.model;


import fr.insa.stein.cours_s2.trellis.matrice.Matrice;
import static fr.insa.stein.cours_s2.trellis.model.TriangleTerrain.demandePT;
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
    
    private List<Noeud> Noeuds;
    private List<TriangleTerrain> TT;
    private List<Barre> Barres;
    private List<TypeBarre> Catalogue;
    
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
    
    public boolean pointDansLaZone(double x, double y){
        if(x>this.getXmax() || x<this.getXmin() || y>this.getYmax() || y<this.getYmin()){
            return false;
        }else{
            return true;
        }
    }

    public static double angleHoriz(double x1, double y1, double x2, double y2){
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.atan2(dy, dx);
    }
    
    public static double angle3pt(double x1, double y1, double x2, double y2, double x3, double y3){
        double ang1 = angleHoriz(x1, y1, x3, y3);
        double ang2 = angleHoriz(x1, y1, x2, y2);        
        double angDel= ang1-ang2;
        if (angDel <= -Math.PI){
            return angDel + 2*Math.PI;
        }    
        if (angDel > Math.PI){
            return angDel - 2*Math.PI;
        }    
	return angDel;
    }
    
    public boolean dansLeTerrain(double x, double y){
        for (int i = 0; i < this.TT.size(); i++) {
            double angle1 = this.TT.get(i).angleAvecPoint(0, 1, x, y);
            double angle2 = this.TT.get(i).angleAvecPoint(1, 2, x, y);
            double angle3 = this.TT.get(i).angleAvecPoint(2, 0, x, y);
            if((angle1<=0 && angle2<=0 && angle3<=0)||(angle1>=0 && angle2>=0 && angle3>=0)){
                return true;        
            }
        }
        return false;
    }
    
    public static double distanceAvecPoint(double x, double y, double x1, double y1){
        double dx = x - x1;
        double dy = y - y1;
        return Math.sqrt(dx*dx+dy*dy);
    }
    
    public double distance2Noeuds(int id1, int id2){
        double x = this.Noeuds.get(id1-1).getPx();
        double y = this.Noeuds.get(id1-1).getPy();
        double x1 = this.Noeuds.get(id2-1).getPx();
        double y1 = this.Noeuds.get(id2-1).getPy();
        return distanceAvecPoint(x,y,x1,y1);
    }
    
    public int nbrAppuisSimple(){
        int n=0;
        for (int i = 0; i < this.Noeuds.size(); i++) {
            if(this.Noeuds.get(i) instanceof AppuisSimple){
                n=n+1;
            }
        }
        return n;
    }
    
    public int nbrAppuisDouble(){
        int n=0;
        for (int i = 0; i < this.Noeuds.size(); i++) {
            if(this.Noeuds.get(i) instanceof AppuisDouble){
                n=n+1;
            }
        }
        return n;
    }
    
    public boolean estIsostat(){
        if(2*this.Noeuds.size()==this.Barres.size()+this.nbrAppuisSimple()+2*this.nbrAppuisDouble()){
            return true;
        }else{
            return false;
        }
    }
    
    public void calculs(){
        int ns = this.Noeuds.size();
        int nb = this.Barres.size();
        int nas = this.nbrAppuisSimple();
        int nad = this.nbrAppuisDouble();
        int nns = ns-nas-nad;
        
        int cur=0;
        int compteurAD=0;
        int compteurAS=0;
        
        Matrice matA = new Matrice(2*ns, 2*ns);
        Matrice matR = new Matrice(2*ns, 1);
        
        for(int i=0; i<ns; i++){
            Noeud Ncur = this.Noeuds.get(i);
            int idNcur = Ncur.getId();
            
            matR.set(cur, 0, -Ncur.getFx());
            matR.set(cur+1, 0, -Ncur.getFy());
            
            if(Ncur instanceof NoeudSimple){
                
                for(int j=0; j<nb; j++){
                    Barre Bcur = this.Barres.get(j);
                    int idBcur = Bcur.getId();
                    int idN1Bcur = Bcur.getIdNoeud1();
                    int idN2Bcur = Bcur.getIdNoeud2();
                    
                    if(idNcur==idN1Bcur){
                        double angle = ((NoeudSimple) Ncur).angleBarre(numN, idN2Bcur);
                        System.out.println(angle);
                        matA.set(cur, idBcur, Math.cos(angle));
                        matA.set(cur+1, idBcur, Math.sin(angle));
                        
                    }else{
                        if(idNcur==idN2Bcur){
                            double angle = ((NoeudSimple) Ncur).angleBarre(numN, idN1Bcur);
                            System.out.println(angle);
                            matA.set(cur, idBcur-1, Math.cos(angle));
                            matA.set(cur+1, idBcur-1, Math.sin(angle));
                        } 
                    }
                }
                
                
            }else{
                if(Ncur instanceof AppuisDouble){
                    compteurAD++;
                    
                    for(int j=0; j<nb; j++){
                    Barre Bcur = this.Barres.get(j);
                    int idBcur = Bcur.getId();
                    int idN1Bcur = Bcur.getIdNoeud1();
                    int idN2Bcur = Bcur.getIdNoeud2();
                    
                    if(idNcur==idN1Bcur){
                        //double angle = Ncur.angleBarre(numN, idN2Bcur);
                        //matA.set(cur, idBcur, Math.cos(angle));
                        //matA.set(cur+1, idBcur, Math.sin(angle));
                        
                    }else{
                        if(idNcur==idN2Bcur){
                            //double angle = Ncur.angleBarre(numN, idN1Bcur);
                            //matA.set(cur, idBcur-1, Math.cos(angle));
                            //.set(cur+1, idBcur-1, Math.sin(angle));
                        } 
                    }
                }
                    
                matA.set(cur, nb+compteurAD-1, 1);
                matA.set(cur+1, nb+compteurAD, 1);
                    
                }else{   
                    if(Ncur instanceof AppuisSimple){
                    compteurAS++;
                    
                        
                        for(int j=0; j<nb; j++){
                            Barre Bcur = this.Barres.get(j);
                            int idBcur = Bcur.getId();
                            int idN1Bcur = Bcur.getIdNoeud1();
                            int idN2Bcur = Bcur.getIdNoeud2();
                    
                            if(idNcur==idN1Bcur){
                                //double angle = Ncur.angleBarre(numN, idN2Bcur);
                                //matA.set(cur, idBcur, Math.cos(angle));
                                //matA.set(cur+1, idBcur, Math.sin(angle));
                        
                            }else{
                                if(idNcur==idN2Bcur){
                                    //double angle = Ncur.angleBarre(numN, idN1Bcur);
                                    //matA.set(cur, idBcur-1, Math.cos(angle));
                                    //.set(cur+1, idBcur-1, Math.sin(angle));
                                } 
                            }
                        }
                    
                    matA.set(cur, nb+2*nad+compteurAS-1, 1);
                    matA.set(cur+1, nb+2*nad+compteurAS-1, 1);
                        
                    }
                }
            }
            cur=cur+2;
        }
        System.out.println(matR);
        System.out.println(matA);
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
    
    public double[] demandePoint(){
        System.out.println("abscisse : ");
        double x= Lire.d();
        System.out.println("ordonnée : ");
        double y= Lire.d();
        if(this.pointDansLaZone(x, y)==false){
            System.out.println("pas dans la zone constructible");
            return null;
        }
        if(this.dansLeTerrain(x, y)==true){
            System.out.println("dans un triangle terrain");
            return null;
        }
        double[] p = {x, y};
        return p;
    }
    
    public double[] demandeNoeud(){
        System.out.println("entrez les coordonnées du nouveaux noeud : ");
            double[] p = demandePoint();
            if(p==null){
                return null;
            }
            for (int i = 0; i < this.Noeuds.size(); i++) {
                if(p[0]==this.Noeuds.get(i).getPx() && p[1]==this.Noeuds.get(i).getPy()){
                    System.out.println("il existe déjà un noeud à cette position");
                    return null;
                }
            }   
        return p;
    }
    
    public static double demandeAlpha(){
        System.out.println("entrez alpha : ");
        double alpha = Lire.d();
            if(alpha<0 &&alpha>1){
                System.out.println("alpha doit être compris entre 0 et 1");
                return -1;
            }
        return alpha;
    }
    
    public void demandeForce(){
        if(this.Noeuds.isEmpty()){
            System.out.println("créer d'abord un noeud");
        }
        System.out.println("choisissez le noeud");
        for (int i = 1; i <= this.Noeuds.size(); i++) {
            System.out.println(i + ") " + this.Noeuds.get(i-1).toString());
        }
        Noeud N = this.numN.getObj(Lire.i());
        System.out.println("entrez Fx : ");
        N.setFx(Lire.d());
        System.out.println("entrez Fy : ");
        N.setFy(Lire.d());
    }
    
    public TypeBarre demandeTypeBarre(){
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
        return new TypeBarre(this.numTB, cout, Lmin, Lmax, Rtract, Rcomp);
    }
    
    public TriangleTerrain choisiTT(){
        if(this.TT.isEmpty()){
            System.out.println("créer d'abord un triangle terrain");
            return null;
        }
        System.out.println("choisissez un triangle terrain");
        for (int i = 1; i <= this.TT.size(); i++) {
            System.out.println(i + ") " + this.TT.get(i-1).toString());
        }
        return this.numTT.getObj(Lire.i());
    }
    
    public TypeBarre choisiTB(){
        if(this.Catalogue.isEmpty()){
            System.out.println("créer d'abord un type de barre");
            return null;
        }
        System.out.println("choisissez un type de barre");
        for (int i = 1; i <= this.Catalogue.size(); i++) {
            System.out.println(i + ") " + this.Catalogue.get(i-1).toString());
        } 
        return numTB.getObj(Lire.i());
    }
    
    public int choisNoeud(){
        if(this.Noeuds.isEmpty()){
            System.out.println("créer d'abord un noeud");
            return -1;
        }
        System.out.println("choisissez le noeud 1");
        for (int i = 1; i <= this.Noeuds.size(); i++) {
            System.out.println(i + ") " + this.Noeuds.get(i-1).toString());
        }
        return Lire.i();
    }
            
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
            System.out.println("9) ajouter une barre avec 1 nouveau noeud simple");
            System.out.println("10) ajouter une barre avec 2 nouveaux noeuds simples");
            System.out.println("11) ajouter une force");
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
                        break;
                    }
                    this.TT.add(new TriangleTerrain(this.numTT, pt));
                    break;
                    
                    
                case 4:
                    TriangleTerrain TT1 = this.choisiTT();
                    if(TT1==null){
                        break;
                    }
                    
                    int numeroPT = TT1.choisiPT();
                    
                    double alpha = demandeAlpha();
                    if(alpha==-1){
                        break;
                    }
                    this.Noeuds.add(new AppuisDouble(numN, TT1, numeroPT, alpha));
                    for (int i = 0; i < this.Noeuds.size()-1; i++) {
                        if(this.Noeuds.get((this.numN.getSize()-1)).getPx()==this.Noeuds.get(i).getPx() && 
                        this.Noeuds.get((this.numN.getSize()-1)).getPy()==this.Noeuds.get(i).getPy() && this.numN.getSize()>0){
                            System.out.println("il existe déjà un noeud à cette position");
                            this.Noeuds.remove(this.numN.getSize()-1);
                        break;
                        }
                    }
                    break;
                    
                    
                case 5:
                    TT1 = this.choisiTT();
                    if(TT1==null){
                        break;
                    }
                    
                    numeroPT = TT1.choisiPT();
                    
                    alpha = demandeAlpha();
                    if(alpha==-1){
                        break;
                    }
                    this.Noeuds.add(new AppuisSimple(numN, TT1, numeroPT, alpha));
                    for (int i = 0; i < this.Noeuds.size()-1; i++) {
                        if(this.Noeuds.get((this.numN.getSize()-1)).getPx()==this.Noeuds.get(i).getPx() && 
                        this.Noeuds.get((this.numN.getSize()-1)).getPy()==this.Noeuds.get(i).getPy() && this.numN.getSize()>0){
                            System.out.println("il existe déjà un noeud à cette position");
                            this.Noeuds.remove(this.numN.getSize()-1);
                        break;
                        }
                    }
                    break;
                    
                    
                case 6:
                    double[] p = demandeNoeud();
                    if(p==null){
                        break;
                    }
                    this.Noeuds.add(new NoeudSimple(this.numN, p));
                    break;
                    
                    
                case 7:
                    this.Catalogue.add(demandeTypeBarre());   
                    break;
                    
                    
                case 8:
                    TypeBarre Type = this.choisiTB();
                    if(Type==null){
                        break;
                    }
                    
                    if(this.Noeuds.size()<2){
                        System.out.println("créer d'abord 2 noeuds");
                        break;
                    }
                    int idN1 = this.choisNoeud();
                    
                    System.out.println("choisissez le noeud  2");
                    for (int i = 1; i <= this.Noeuds.size(); i++) {
                        if(i!= idN1){
                            System.out.println(i + ") " + this.Noeuds.get(i-1).toString());
                        }
                    }
                    int idN2 = Lire.i();
                    if(this.distance2Noeuds(idN1, idN2)>Type.getLmax()){
                        System.out.println("Noeuds trop éloignés");
                        break;
                    }
                    if(this.distance2Noeuds(idN1, idN2)<Type.getLmin()){
                        System.out.println("Noeuds trop proches");
                        break;
                    }
                    this.Barres.add(new Barre(this.numB, Type, idN1, idN2));
                    this.Noeuds.get(this.Barres.get(this.numTB.getSize()-1).getIdNoeud1()-1).getBarreN().add(this.numTB.getSize()-1);
                    this.Noeuds.get(this.Barres.get(this.numTB.getSize()-1).getIdNoeud2()-1).getBarreN().add(this.numTB.getSize()-1);
                    break;
                    
                    
                case 9:
                    Type = this.choisiTB();
                    if(Type==null){
                        break;
                    }
                    
                    idN1 = this.choisNoeud();
                    if(idN1==-1){
                        break;
                    }
                    
                    p = demandeNoeud();
                    if(p==null){
                        break;
                    }
                    this.Noeuds.add(new NoeudSimple(this.numN, p));
                    
                    if(this.distance2Noeuds(idN1, this.numN.getSize())>Type.getLmax()){
                        System.out.println("Noeuds trop éloignés");
                        this.Noeuds.remove(this.numN.getSize()-1);
                        break;
                    }
                    if(this.distance2Noeuds(idN1, this.numN.getSize())<Type.getLmin()){
                        System.out.println("Noeuds trop proches");
                        this.Noeuds.remove(this.numN.getSize()-1);
                        break;
                    }
                    this.Barres.add(new Barre(this.numB, Type, idN1, this.numN.getSize()));
                    this.Noeuds.get(this.Barres.get(this.numTB.getSize()-1).getIdNoeud1()-1).getBarreN().add(this.numTB.getSize()-1);
                    this.Noeuds.get(this.Barres.get(this.numTB.getSize()-1).getIdNoeud2()-1).getBarreN().add(this.numTB.getSize()-1);
                    break;
                    
                    case 10:
                    Type = this.choisiTB();
                    if(Type==null){
                        break;
                    }
                    
                    p = demandeNoeud();
                    if(p==null){
                        break;
                    }
                    this.Noeuds.add(new NoeudSimple(this.numN, p));
                    
                    p = demandeNoeud();
                    if(p==null){
                        break;
                    }
                    this.Noeuds.add(new NoeudSimple(this.numN, p));
                    
                    if(this.distance2Noeuds(this.numN.getSize()-1, this.numN.getSize())>Type.getLmax()){
                        System.out.println("Noeuds trop éloignés");
                        this.Noeuds.remove(this.numN.getSize()-1);
                        break;
                    }
                    if(this.distance2Noeuds(this.numN.getSize()-1, this.numN.getSize())<Type.getLmin()){
                        System.out.println("Noeuds trop proches");
                        this.Noeuds.remove(this.numN.getSize()-1);
                        break;
                    }
                    this.Barres.add(new Barre(this.numB, Type, this.numN.getSize()-1, this.numN.getSize()));
                    this.Noeuds.get(this.Barres.get(this.numTB.getSize()-1).getIdNoeud1()-1).getBarreN().add(this.numTB.getSize()-1);
                    this.Noeuds.get(this.Barres.get(this.numTB.getSize()-1).getIdNoeud2()-1).getBarreN().add(this.numTB.getSize()-1);
                    break;
                    
                    case 11:
                        demandeForce();
                    break;
                default:
                    break;
            }
        }
    }
    
    public static Treillis treillisTest(){
        Treillis res = new Treillis();
        double [][] PT1 ={{0,0},{0,2},{-3,1}};
        res.TT.add(new TriangleTerrain(res.numTT, PT1, Color.GREEN));
        res.Catalogue.add(new TypeBarre(res.numTB, 100,1,5,1000,2000));
        res.Noeuds.add(new AppuisDouble(res.numN,res.numTT.getObj(1),0,1));
        res.Noeuds.add(new AppuisSimple(res.numN,res.numTT.getObj(1),0,0));
        res.Noeuds.add(new NoeudSimple(res.numN, 1, 1));
        res.Barres.add(new Barre(res.numB,res.Catalogue.get(0),1,3));
        res.Barres.add(new Barre(res.numB,res.Catalogue.get(0),2,3));
        res.Barres.add(new Barre(res.numB,res.Catalogue.get(0),1,2));
        res.numN.getObj(3).setFy(-1000);
        return res;
    }
    
    public static void testMenu(){
        Treillis Zone = treillisTest();
        Zone.calculs();
        
        Zone.menuTexte();
    }
    
    public static void main(String[] args) {
        testMenu();
        
    }
}