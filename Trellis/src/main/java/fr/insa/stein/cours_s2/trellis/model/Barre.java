package fr.insa.stein.cours_s2.trellis.model;


import javafx.scene.canvas.GraphicsContext;
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
public class Barre {
    
    private int id;
    private int idType;
    private int idNoeud1;
    private int idNoeud2;
    private Color col;

    
    public Barre(Numeroteur<Barre> num, TypeBarre Type, int idNoeud1, int idNoeud2, Color col) {
        this.id =  num.creeID(this);
        this.idType = Type.getId();
        this.idNoeud1 = idNoeud1;
        this.idNoeud2 = idNoeud2;
        this.col = col;
    }
    
    public Barre(Numeroteur<Barre> num, TypeBarre Type, int idNoeud1, int idNoeud2) {
        this(num, Type, idNoeud1, idNoeud2, Color.BLACK);
    }
    
    public Barre(Numeroteur<Barre> num, TypeBarre Type, Noeud Noeud1, Noeud Noeud2, Color col) {
        this(num, Type, Noeud1.getId(), Noeud2.getId(), col);
    }
    
    public Barre(Numeroteur<Barre> num, TypeBarre Type, Noeud Noeud1, Noeud Noeud2) {
        this(num, Type, Noeud1.getId(), Noeud2.getId(), Color.BLACK);
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

    public Color getCol() {
        return col;
    }

    public void setCol(Color col) {
        this.col = col;
    }
    
    @Override
    public String toString() {
        return  "Barre {\n" + "id : "+getId()+" ; "+ "idType : "+getIdType()+" ; "+
                "idNoued1 : "+getIdNoeud1()+" ; "+"idNoeud2 : "+getIdNoeud2()+" ; " + "\n}";
    }
    public void dessine(GraphicsContext context, Treillis treillis) {
        
        context.setStroke(this.getCol());
        context.strokeLine(treillis.getNumN().getObj(this.idNoeud1).getPx(),
        treillis.getNumN().getObj(this.idNoeud1).getPy(),treillis.getNumN().getObj(this.idNoeud2).getPx(), treillis.getNumN().getObj(this.idNoeud2).getPy());
    }
}
