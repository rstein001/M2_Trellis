
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
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
public class AppuisSimple extends Appuis {
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, Point P, Color col) {
        super(num, TT, P, col);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y, Color col) {
        super(num, TT, X, Y, col);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, Point P) {
        super(num, TT, P);
    }
    
    public AppuisSimple(Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y) {
        super(num, TT, X, Y);
    }
    
}
