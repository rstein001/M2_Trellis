
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renaud
 */
public class AppuisDouble extends Appuis {
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, Point P) {
        super(num, TT, P);
    }
    
    public AppuisDouble(Numeroteur<Noeud> num, TriangleTerrain TT, double X, double Y) {
        super(num, TT, X, Y);
    }
    
    
}
