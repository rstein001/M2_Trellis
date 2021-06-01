/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.stein.cours_s2.trellis.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class Numeroteur<TO> {
    
    private TreeMap<Integer,TO> idVersObjet;
    private Map<TO,Integer> objetVersId;
    
    private int prochainID;
    
    public Numeroteur() {
        this(1);
    }
    
    private Numeroteur(int prochainID) {
        this.prochainID = prochainID;
        this.idVersObjet = new TreeMap<>();
        this.objetVersId = new HashMap<>();
    }
    
    public int creeID(TO obj) {
        if(this.objetVersId.containsKey(obj)) {
            throw new Error("objet " + obj + " déjà dans le numéroteur");
        }
        this.idVersObjet.put(this.prochainID, obj);
        this.objetVersId.put(obj, this.prochainID);
        this.prochainID ++;
        return this.prochainID - 1;
    }
    
    public boolean objExist(TO obj) {
        return this.objetVersId.containsKey(obj);
    }
    
    public int getID(TO obj) {
        if (this.objExist(obj)) {
            return this.objetVersId.get(obj);
        } else {
            throw new Error("Objet" + obj + " inconnu dans numéroteur");
        }
    }
    
    public TO getObj(int id) {
        if (! this.idExist(id)) {
            throw new Error("identificateur non existant");
        }
        return this.idVersObjet.get(id);
    }
    
    public boolean idExist(int id) {
        return this.idVersObjet.containsKey(id);
    }
    
    public int getSize(){
        return this.objetVersId.size();
    }
    
}
