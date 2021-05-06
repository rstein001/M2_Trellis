
import fr.insa.stein.cours_s2.trellis.dessin.Numeroteur;
import fr.insa.stein.cours_s2.trellis.dessin.Point;
import fr.insa.stein.cours_s2.trellis.dessin.Segment;
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
public class Barre extends Segment {
    
    private int id;
    private int idType;
    private int idNoeud1;
    private int idNoeud2;

    public Barre(Numeroteur<Barre> num, TypeBarre Type, Noeud Noeud1, Noeud Noeud2) {
        super((Point) Noeud1, (Point) Noeud2);
        this.id =  num.creeID(this);
        this.idType = Type.getId();
        this.idNoeud1 = Noeud1.getId();
        this.idNoeud2 = Noeud2.getId();
    }

    public int getId() {
        return id;
    }

    public int getIdType() {
        return idType;
    }

    public int getIdNoeud1() {
        return idNoeud1;
    }

    public int getIdNoeud2() {
        return idNoeud2;
    }
    
}
