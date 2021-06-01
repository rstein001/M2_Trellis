/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.model;


import static fr.insa.stein.cours_s2.trellis.model.Treillis.angle3pt;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import recup.Lire;



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
        this(num, PT, Color.GREEN);
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

    public Color getCol() {
        return col;
    }
    
    @Override
    public String toString() {
        return  "Triangle Terrain {\n" + "id : "+getId()+" ; (" + this.getPTx(0) + "," + this.getPTy(0) + ") ; (" +
                this.getPTx(1) + "," + this.getPTy(1) + ") ; (" + this.getPTx(2) + "," + this.getPTy(2) + ") \n}";
    }
    
    public static double[][] demandePT(Treillis Z){
        double[][] pt = new double[3][2];
        
        System.out.println("entrez les coordonées de PT0:");
        System.out.println("abscisse : ");
        pt[0][0]= Lire.d();
        System.out.println("ordonnée : ");
        pt[0][1]= Lire.d();
        System.out.println("entrez les coordonées de PT1:");
        System.out.println("abscisse : ");
        pt[1][0]= Lire.d();
        System.out.println("ordonnée : ");
        pt[1][1]= Lire.d();
        System.out.println("entrez les coordonées de PT2:");
        System.out.println("abscisse : ");
        pt[2][0]= Lire.d();
        System.out.println("ordonnée : ");
        pt[2][1]= Lire.d();
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
   
    public double angleAvecPoint(int i1, int i2, double x, double y){
        return angle3pt(this.getPTx(i1),this.getPTy(i1), this.getPTx(i2),this.getPTy(i2), x, y);
    }

    
    public void dessine(GraphicsContext context) {
        double x1 = this.getPTx(0)*50+500;
        double y1 = -(this.getPTy(0)*50-400);
        double x2 = this.getPTx(1)*50+500;
        double y2 = -(this.getPTy(1)*50-400);
        double x3 = this.getPTx(2)*50+500;
        double y3 = -(this.getPTy(2)*50-400);
        context.setStroke(this.getCol());
        context.strokeLine(x1,y1, x2, y2);
        context.strokeLine(x2,y2, x3, y3);
        context.strokeLine(x3,y3, x1, y1);
        context.fillText ("PT"+Integer.toString(0), x1-25, y1+10);
        context.fillText ("PT"+Integer.toString(1), x2-25, y2+10);
        context.fillText ("PT"+Integer.toString(2), x3-25, y3+10);
    }
    
    public void save (Writer w) throws IOException{
        w.append("Triangle;"+this.getId()+";("+this.getPTx(0)+','+this.getPTy(0)+");("+this.getPTx(1)+','+this.getPTy(1)+
                ");("+this.getPTx(2)+','+this.getPTy(2)+")"+"\n");
    }
    
    
    
}
