/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.matrice;

import java.text.DecimalFormat;
import java.util.Optional;


public class Matrice {

    //--------------- partie 1.1
    /**
     * nombre de ligne de la matrice. Cet attribut n'est pas indispensable ici
     * puisque l'on représente les matrices par des tableau à deux dimension de
     * double. On pourrait dont obtenir le nombre de lignes par :
     * {@code this.coeffs.length} On décide tout de même de conserver
     * explicitement le nombre de lignes pour voir l'utilisation d'attributs
     * simples.
     */
    private int nbrLig;

    /**
     * nombre de colonnes de la matrice. Voir commentaire sur {@link #nbrLig}
     */
    private int nbrCol;

    /**
     * les coefficients de la matrice.
     */
    private double[][] coeffs;

    /**
     * le pivôt est considéré nul s'il est inférieur ou égal à EPSILON_PIVOT
     */
    private static final double EPSILON_PIVOT = 1E-8;

    //--------------- partie 1.2
    /**
     * Cree la matrice nulle de taille nbrLig x nbrCol.
     *
     * @param nbrLig nombre de ligne
     * @param nbrCol nombre de colonnes
     */
    public Matrice(int nbrLig, int nbrCol) {
        this.nbrLig = nbrLig;
        this.nbrCol = nbrCol;
        this.coeffs = new double[nbrLig][nbrCol];
        // il est inutile en java d'initialiser explicitement les coeffs à
        // zéro : java initialise automatiquement à zéro les tableaux
        // numériques.
        // !!! ce ne serait pas forcément le cas dans d'autres langages (ex C)
    }

    /**
     * un autre constructeur possible, non demandé dans le sujet. on fourni
     * explicitement tous les coeffs sous forme de tableau on suppose que le
     * tableau de tableau fourni est effectivement rectangulaire. Attention : il
     * n'y a pas copie : le tableau fourni devient le tableau des coefficients
     * de la matrice. Comme tout cela est un peu "dangereux" on va en faire un
     * constructeur privé
     */
    private Matrice(double[][] coeffs) {
        this.nbrLig = coeffs.length;
        this.nbrCol = coeffs[0].length;
        this.coeffs = coeffs;
    }

    //--------------- partie 1.3
    /**
     * matrice identité.
     *
     * @param taille
     * @return la matrice identité (carrée) taille x taille
     */
    public static Matrice identite(int taille) {
        Matrice res = new Matrice(taille, taille);
        for (int i = 0; i < taille; i++) {
            res.coeffs[i][i] = 1;
        }
        return res;
    }

    public static Matrice matTest1() {
        Matrice res = new Matrice(3, 3);
        int cur = 0;
        for (int i = 0; i < res.nbrLig; i++) {
            for (int j = 0; j < res.nbrCol; j++) {
                res.coeffs[i][j] = cur;
                cur++;
            }
        }
        return res;
    }

    public static Matrice matTest2() {
        Matrice res = Matrice.matTest1();
        res.coeffs[1][1] = -res.coeffs[1][1];
        res.coeffs[2][2] = -res.coeffs[2][2];
        return res;
    }

    /**
     * renvoie aléatoirement 1 ou 2 avec la proba 1/2 1/2.
     *
     * @return aléatoirement 1 ou 2.
     */
    public static int aleaUnOuDeux() {
        if (Math.random() < 0.5) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * crée une matrice de taille nbrLig x nbrCol composée aléatoirement
     * uniquement de -1,0 ou 1. La probabilité qu'un coeff soit nul est
     * proportionDeZero. Ensuite, il y a probabilité égale, donc
     * (1-proportionDeZero)/2 d'avoir un -1 ou un 1.
     *
     * @param nbrLig
     * @param nbrCol
     * @param proportionDeZero proba qu'un coeff soit nul
     * @return la nouvelle matrice
     */
    public static Matrice matAleaZeroUnDeux(int nbrLig, int nbrCol, double proportionDeZero) {
        Matrice res = new Matrice(nbrLig, nbrCol);
        for (int i = 0; i < nbrLig; i++) {
            for (int j = 0; j < nbrCol; j++) {
                if (Math.random() >= proportionDeZero) {
                    res.coeffs[i][j] = aleaUnOuDeux();
                }
            }
        }
        return res;
    }

    public static Matrice creeVecteur(double[] composantes) {
        Matrice res = new Matrice(composantes.length, 1);
        for (int i = 0; i < composantes.length; i++) {
            res.coeffs[i][0] = composantes[i];
        }
        return res;
    }

    //--------------- partie 1.4
    public int getNbrLig() {
        return this.nbrLig;
    }

    public int getNbrCol() {
        return this.nbrCol;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public void set(int lig, int col, double val) {
        this.coeffs[lig][col] = val;
    }

    //--------------- non demandé : c'est simplement pour avoir éventuellement
    //  un affichage plus simple dans le sujet
    public static String formatDouble(double x) {
//        return formatDouble2Digits(x);
//        return formatDoubleMax2Decimales(x);
        return formatDoubleFixe(x);
    }

    public static String formatDoubleMax2Decimales(double x) {
        DecimalFormat f = new DecimalFormat("#.##");
        return f.format(x);
    }

    public static String formatDouble2Digits(double x) {
        return String.format("%+3.1f", x);
    }

    public static String formatDoubleFixe(double x) {
        return String.format("%+4.2E", x);
    }

    //--------------- partie 1.5
    @Override
    public String toString() {
        // oui, il serait plus efficace d'utiliser un {@link java.lang.StringBuilder}
        // mais ils n'ont pas été vu
        String res = "";
        for (int i = 0; i < nbrLig; i++) {
            res = res + "[";
            for (int j = 0; j < nbrCol; j++) {
                res = res + formatDouble(this.get(i, j));
                if (j < nbrCol - 1) {
                    res = res + " ";
                }
            }
            res = res + "]\n";
        }
        return res;
    }

    //--------------- partie 1.6
    public static void test1() {
        System.out.println("----------- test 1 --------------");
        int nl = 4, nc = 6;
        double pz = 0.3;
        System.out.println("matrice alea de taille " + nl + " x " + nc
                + " (proba de zero : " + pz + ") : ");
        System.out.println(Matrice.matAleaZeroUnDeux(nl, nc, pz));
    }

    //--------------- partie 2.1
    public Matrice concatLig(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrCol()) {
            throw new Error("les matrices doivent avoir même nombre de colonnes");
        }
        Matrice res = new Matrice(this.getNbrLig() + m2.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                if (i < this.getNbrLig()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i - this.getNbrLig(), j));
                }
            }
        }
        return res;
    }

    public Matrice concatCol(Matrice m2) {
        if (this.getNbrLig() != m2.getNbrLig()) {
            throw new Error("les matrices doivent avoir même nombre de lignes");
        }
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol() + m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                if (j < this.getNbrCol()) {
                    res.set(i, j, this.get(i, j));
                } else {
                    res.set(i, j, m2.get(i, j - this.getNbrCol()));
                }
            }
        }
        return res;
    }

    //--------------- partie 2.2
    /**
     * sous matrice constituée par les ligne lig telles que
     * {@code ligMin <= lig <= ligMax}
     *
     * @param ligMin
     * @param ligMax
     * @return une nouvelle matrice constituée d'une copie des lignes
     * correspondantes
     */
    public Matrice subLignes(int ligMin, int ligMax) {
        if (ligMin < 0 || ligMax < ligMin || ligMax >= this.getNbrLig()) {
            throw new Error("indices lignes invalides");
        }
        Matrice res = new Matrice(ligMax - ligMin + 1, this.getNbrCol());
        for (int lig = 0; lig < res.getNbrLig(); lig++) {
            for (int col = 0; col < res.getNbrCol(); col++) {
                res.set(lig, col, this.get(ligMin + lig, col));
            }
        }
        return res;
    }

    /**
     * sous matrice constituée par les colonnes col telles que
     * {@code colMin <= col <= colMax}
     *
     * @param colMin
     * @param colMax
     * @return une nouvelle matrice constituée d'une copie des lignes
     * correspondantes
     */
    public Matrice subCols(int colMin, int colMax) {
        if (colMin < 0 || colMax < colMin || colMax >= this.getNbrCol()) {
            throw new Error("indices colonnes invalides");
        }
        Matrice res = new Matrice(this.getNbrLig(), colMax - colMin + 1);
        for (int lig = 0; lig < res.getNbrLig(); lig++) {
            for (int col = 0; col < res.getNbrCol(); col++) {
                res.set(lig, col, this.get(lig, colMin + col));
            }
        }
        return res;
    }

    /**
     *
     * @return une copie de la matrice.
     */
    public Matrice copie() {
        return this.subLignes(0, this.getNbrLig() - 1);
    }

    //--------------- partie 2.3
    public Matrice transposee() {
        Matrice res = new Matrice(this.getNbrCol(), this.getNbrLig());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.set(i, j, this.get(j, i));
            }
        }
        return res;
    }

    //--------------- partie 2.4
    public Matrice metAuCarre() {
        return this.concatCol(Matrice.identite(this.getNbrLig())).concatLig(
                Matrice.identite(this.getNbrCol()).concatCol(this.transposee()));
    }

    public static int intAlea(int bmin, int bmax) {
        return (int) (Math.random() * (bmax - bmin + 1)) + bmin;
    }

    public static void test2() {
        System.out.println("----------- test 2 --------------");
        int nl = intAlea(1, 4), nc = intAlea(1, 4);
        double pz = 0.33;
        Matrice m = Matrice.matAleaZeroUnDeux(nl, nc, pz);
        Matrice mc = m.metAuCarre();
        Matrice mbis = mc.subLignes(0, m.getNbrLig() - 1).subCols(0, m.getNbrCol() - 1);
        System.out.println("m : matrice de base : ");
        System.out.println(m);
        System.out.println("mc : mise au carré : ");
        System.out.println(mc);
        System.out.println("mbis : (sub de mc) : ");
        System.out.println(mbis);
    }

    //--------------- partie 3.1
    /**
     * calcule la somme de deux matrices.
     *
     * @param m2
     * @return this+m2
     */
    public Matrice add(Matrice m2) {
        if (this.getNbrLig() != m2.getNbrLig() || this.getNbrCol() != m2.getNbrCol()) {
            throw new Error("tailles incompatibles pour add");
        }
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.set(i, j, this.get(i, j) + m2.get(i, j));
            }
        }
        return res;
    }

    //--------------- partie 3.2
    /**
     * calcule la matrice opposée. {@code Opp_i,j = - M_i,j}.
     *
     * @return
     */
    public Matrice opp() {
        Matrice res = new Matrice(this.getNbrLig(), this.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                res.set(i, j, -this.get(i, j));
            }
        }
        return res;
    }

    //--------------- partie 3.3
    /**
     * calcule la this-m2.
     *
     * @param m2
     * @return this-m2
     */
    public Matrice moins(Matrice m2) {
        return this.add(m2.opp());
    }

    //--------------- partie 3.4
    public Matrice mult(Matrice m2) {
        if (this.getNbrCol() != m2.getNbrLig()) {
            throw new Error("tailles incompatibles pour mult");
        }
        Matrice res = new Matrice(this.getNbrLig(), m2.getNbrCol());
        for (int i = 0; i < res.getNbrLig(); i++) {
            for (int j = 0; j < res.getNbrCol(); j++) {
                double cur = 0;
                for (int k = 0; k < this.getNbrCol(); k++) {
                    cur = cur + this.get(i, k) * m2.get(k, j);
                }
                res.set(i, j, cur);
            }
        }
        return res;
    }
    //--------------- partie 3.5

    public static void test3() {
        System.out.println("----------- test 3 --------------");
        Matrice m = Matrice.matTest1();
        System.out.println("mat M : ");
        System.out.println(m);
        System.out.println("M + M^2 :");
        System.out.println(m.add(m.mult(m)));
    }

    public  int permuteLigne(int i1, int i2){
        int j= 0;
        double cur;
        for(j=0; j<this.getNbrCol(); j++){
            cur = this.get(i1, j);
            this.set(i1, j, this.get(i2, j));
            this.set(i2, j, cur);
        }
        if (i1 == i2) {
            return 1;
        } else {
            return -1;
        }      
    }
    
    public void transvection(int i1, int i2){
        if(i1 >= this.getNbrCol()){
            throw new Error("Erreur d'indice");
        }else{
            if(this.get(i1, i1)==0){
                throw new Error("Erreur : division par 0");
            }else{
                double p = this.get(i2, i1)/this.get(i1, i1);
                for(int j=0; j<this.getNbrCol(); j++){
                    if(j != i1){
                        this.set(i2, j, this.get(i2, j)-p*this.get(i1, j)); 
                    }else{
                        this.set(i2, j, 0); 
                    }
                }
            }
        }
    }
    
    public int lignePlusGrandPivot(int e){
        int i=0;
        int ligMax=e;
        if(e >= this.getNbrLig() || e >= this.getNbrCol()){
            throw new Error("Erreur d'indice");
        }else{
            for(i=e+1; i<this.getNbrLig(); i++){
                if(Math.abs(this.get(i, e))>Math.abs(this.get(ligMax, e))){
                    ligMax=i;
                }
            }
            if(Math.abs(this.get(ligMax, e))>EPSILON_PIVOT){
               return ligMax;
            }else{
                return -1;
            }
        }      
    }
    
    public ResGauss descenteGauss() {
        int e = 0;
        int imax;
        int signature = 1;
        while (e < this.getNbrLig() && e < this.getNbrCol()
                && (imax = this.lignePlusGrandPivot(e)) != -1) {
            signature = signature * this.permuteLigne(e, imax);
            for (int lig = e + 1; lig < this.getNbrLig(); lig++) {
                this.transvection(e, lig);
            }
            e++;
        }
        return new ResGauss(e, signature);
    }
    
    public static void test4() {
        System.out.println("--------- test 4 --------------");
        Matrice vect = Matrice.creeVecteur(new double[]{1, 2, 3});
        Matrice[] matTests = new Matrice[]{
            Matrice.matTest1().concatCol(vect),
            Matrice.matTest2().concatCol(vect)
        };
        // la encore, une syntaxe du for pas forcément connue.
        // disons simplement que la variable m va prendre succéssivement
        // toutes les valeurs du tableau matTests.
        // cette syntaxe est disponible pour les tableaux, mais aussi pour
        // toutes les collections (par exemple les listes).
        for (Matrice m : matTests) {
            System.out.println("==============================================");
            System.out.println("matrice : ");
            System.out.println(m);
            ResGauss res = m.descenteGauss();
            System.out.println("matrice après descenteGauss : ");
            System.out.println(m);
            System.out.println("résultat retourné : ");
            System.out.println(res);
        }
    }
    
    /**
     * rend unitaire les éléments diagonaux non nuls. Suppose que tous les
     * éléments diagonaux M_i,i sont non nuls pour {@code 0 <= i < rang}
     *
     * @param rang le nombre d'éléments diagonaux non nuls
     */
    public void pivotsUnitaires(int rang) {
        for (int lig = 0; lig < rang; lig++) {
            double div = this.get(lig, lig);
            if (div == 0) {
                throw new Error("pivot nul : ligne " + lig + " Mat :\n" + this);
            }
            for (int col = 0; col < this.getNbrCol(); col++) {
                this.set(lig, col, this.get(lig, col) / div);
            }
        }
    }

    public void remonteeGauss(int rang) {
        for (int e = rang - 1; e >= 0; e--) {
            for (int lig = e - 1; lig >= 0; lig--) {
                this.transvection(e, lig);
            }
        }
    }    public static class ResSysLin {

        /**
         * vrai ssi le système admet une solution unique
         */
        private boolean solUnique;
        /**
         * solution du système linéaire. contient null si {@link #solUnique} est
         * false sinon, contient le vecteur solution sous la forme d'une Matrice
         * d'une seule colonne.
         */
        private Matrice sol;

        private ResSysLin() {
            this.solUnique = false;
        }

        private ResSysLin(Matrice sol) {
            this.solUnique = true;
            this.sol = sol;
        }

        public static ResSysLin pasDeSolUnique() {
            return new ResSysLin();
        }

        public static ResSysLin sol(Matrice sol) {
            return new ResSysLin(sol);
        }

        public String toString() {
            if (this.solUnique) {
                return this.sol.toString();
            } else {
                return "{pas de sol unique}";
            }
        }

    }

    public Matrice getColonne(int col) {
        Matrice res = new Matrice(this.getNbrLig(), 1);
        for (int lig = 0; lig < this.getNbrLig(); lig++) {
            res.set(lig, 0, this.get(lig, col));
        }
        return res;
    }

    public ResSysLin resoudSysLin(Matrice secondMembre) {
        if (this.getNbrLig() != this.getNbrCol()) {
            throw new Error("La matrice n'est pas carrée");
        }
        if (secondMembre.getNbrCol() != 1) {
            throw new Error("Le second membre n'est pas un vecteur");
        }
        Matrice toGauss = this.concatCol(secondMembre);
        ResGauss res = toGauss.descenteGauss();
        if (res.rang == this.getNbrLig()) {
            toGauss.pivotsUnitaires(res.rang);
            toGauss.remonteeGauss(res.rang);
            return ResSysLin.sol(toGauss.getColonne(res.rang));
        } else {
            return ResSysLin.pasDeSolUnique();
        }
    }

    /**
     * calcule si possible la matrice inverse.
     *
     * @return un Optional contenant la matrice inverse si this est inversible,
     * null sinon.
     */
    public Optional<Matrice> inverse() {
        if (this.getNbrLig() != this.getNbrCol()) {
            throw new Error("inverse seulement pour les matrices carrées");
        }
        Matrice toGauss = this.concatCol(Matrice.identite(this.getNbrLig()));
        ResGauss triSup = toGauss.descenteGauss();
        if (triSup.rang == this.getNbrLig()) {
            toGauss.pivotsUnitaires(triSup.rang);
            toGauss.remonteeGauss(triSup.rang);
            Matrice inverse = toGauss.subCols(this.getNbrCol(), 2 * this.getNbrCol() - 1);
            return Optional.of(inverse);
        } else {
            return Optional.ofNullable(null);
        }
    }

    // quelque petites méthodes utilitaires pour tester que le calcul
    // de l'inverse (et donc de la descente et de la remontée) est correct
    // en particulier, à cause des erreur d'arrondi, on a besoin de tester
    // non pas l'égalité exacte, mais l'égalité approchée de deux matrice
    // on utilisera la norme la plus simple : le max des coeffs (en val absolue)
    /**
     * calcule la norme sup, aussi appelée norme "infinie".
     * {@code ||M|| = sup(i in 0..nbrLig-1 ; j in 0..nbrCol-1 ; | M_i,j |}
     *
     * @return
     */
    public double normeSup() {
        double max = 0;
        for (int lig = 0; lig < this.getNbrLig(); lig++) {
            for (int col = 0; col < this.getNbrCol(); col++) {
                double cur = Math.abs(this.get(lig, col));
                if (cur > max) {
                    max = cur;
                }
            }
        }
        return max;
    }

    /**
     * distance entre deux matrice basée sur la norme sup : {@link #normeSup()
     * }.
     *
     * @param m2
     * @return distance(this,m2) suivant la norme {@link #normeSup() }
     */
    public double distSup(Matrice m2) {
        return this.moins(m2).normeSup();
    }

    public static void testTraceInverse() {
        Matrice m = Matrice.matTest2();
        System.out.println("M : \n" + m);
        Matrice toGauss = m.concatCol(Matrice.identite(m.getNbrLig()));
        System.out.println("toGauss : \n" + toGauss);
        ResGauss triSup = toGauss.descenteGauss();
        System.out.println("après descente : \n" + toGauss);
        System.out.println("res = " + triSup);
        if (triSup.rang == m.getNbrLig()) {
            toGauss.pivotsUnitaires(triSup.rang);
            System.out.println("après pivots unitaires : \n" + toGauss);
            toGauss.remonteeGauss(triSup.rang);
            System.out.println("après remontée : \n" + toGauss);
            Matrice inverse = toGauss.subCols(m.getNbrCol(), 2 * m.getNbrCol() - 1);
            System.out.println("inverse : \n" + inverse);
            Matrice idCalc = m.mult(inverse);
            System.out.println("M . M-1 : \n" + idCalc);
            System.out.println("dist(M . M-1 , Id) = "
                    + idCalc.distSup(Matrice.identite(m.getNbrLig())));
        } else {
            System.out.println("non inversible");
        }

    }

    /**
     * test le calcul d'inverse nbrTest fois sur des matrice alea 0-1-2 de
     * taille taille avec une proportion de 0 de 0.33 taille. affiche une erreur
     * si printErreurs est vrai, et M est inversible, et distSup(M.M-1,Id) >
     * epsilonDistAcceptable
     *
     * @param nbrTest
     * @param taille
     * @param epsilonDistAcceptable
     */
    public static void testInverse(int nbrTest, int taille, double epsilonDistAcceptable,
            boolean printErreurs) {
        System.out.println("test distance(M . M-1,Identite) < epsilon");
        System.out.println("nbr Tests : " + nbrTest);
        System.out.println("taille des matrices : " + taille);
        System.out.println("Matrices contenant 0,1, ou 2 avec probas ~ 1/3;1/3;1/3");
        System.out.println("epsilon : " + epsilonDistAcceptable);
        int nbrNonInversible = 0;
        int nbrErreur = 0;
        for (int i = 0; i < nbrTest; i++) {
            Matrice m = Matrice.matAleaZeroUnDeux(taille, taille, 0.33);
            Matrice id = Matrice.identite(taille);
            Matrice mc = m.copie().concatCol(id);
            ResGauss rg = mc.descenteGauss();
            if (rg.rang != taille) {
                nbrNonInversible++;
            } else {
                mc.pivotsUnitaires(taille);
                mc.remonteeGauss(taille);
                Matrice inverse = mc.subCols(taille, 2 * taille - 1);
                Matrice idCalc = m.mult(inverse);
                double dist = id.distSup(idCalc);
                if (dist > epsilonDistAcceptable) {
                    nbrErreur++;
                    if (printErreurs) {
                        System.out.println("--------- problème ------------");
                        System.out.println("M =");
                        System.out.println(m);
                        System.out.println("M-1 = ");
                        System.out.println(inverse);
                        System.out.println("M . M-1 =");
                        System.out.println(idCalc);
                        System.out.println("distance sup = " + dist);
                    }
                }
            }
        }
        System.out.println("non inversible : " + nbrNonInversible + "/" + nbrTest
                + " ~ " + (nbrNonInversible * 100.0 / nbrTest) + " %");
        System.out.println("nombre d'inverses avec dist > " + epsilonDistAcceptable + " : "
                + nbrErreur + " (devrait être 0)");
    }
    
    public static void main(String[] args) {
    //    test1();
    //    test2();
    //    test3();
    //    test4();
        testInverse(4,4,1e-6, true);
       

    }

}
