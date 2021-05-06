
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
public abstract class Noeud extends Point{
    
    private int id;

    public Noeud(Numeroteur<Noeud> num, double x, double y) {
        super(x, y);
        this.id = num.creeID(this);
    }

    public int getId() {
        return id;
    }
    
}
